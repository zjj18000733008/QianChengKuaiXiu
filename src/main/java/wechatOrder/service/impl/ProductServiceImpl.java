package wechatOrder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wechatOrder.dao.ProductMapper;
import wechatOrder.dao.SpecificationMapper;
import wechatOrder.po.Product;
import wechatOrder.po.ProductWithBLOBs;
import wechatOrder.po.Specification;
import wechatOrder.po.SpecificationExample;
import wechatOrder.po.vo.ProductSpecificationVO;
import wechatOrder.po.vo.ProductVO;
import wechatOrder.po.vo.ProductWithPriceRangeVO;
import wechatOrder.service.ProductService;
import wechatOrder.service.SpecificationService;
import wechatOrder.util.FatherToChildUtils;
import wechatOrder.util.ImgUrlUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/11/26 - 13:37
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Resource(name = "productMapper")
    private ProductMapper productMapper;
    @Autowired
    private SpecificationService specificationService;
    @Autowired
    private SpecificationMapper specificationMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addProduct(ProductSpecificationVO productSpecificationVo) throws Exception {

        //得到系统当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());

        //存入添加商品的时间
        productSpecificationVo.setAddTime(date);
        productSpecificationVo.setModifyTime(date);

        //将<img src="..."/>中的url提取成'url,url'串
        productSpecificationVo.setSurfaceImg(ImgUrlUtils.parse(productSpecificationVo.getSurfaceImg()));
        productSpecificationVo.setSlideImg(ImgUrlUtils.parse(productSpecificationVo.getSlideImg()));
        productSpecificationVo.setIntroImg(ImgUrlUtils.parse(productSpecificationVo.getIntroImg()));


        productMapper.saveProduct(productSpecificationVo);//将product信息存入数据库
        Integer id = productSpecificationVo.getId();//得到刚刚插入数据库的product的id
        List<Specification> specifications = productSpecificationVo.getSpecifications();//得到商品的规格
        for (int i = 0; i < specifications.size(); i++) {
            Specification specification = specifications.get(i);
            if("0".equals(specification.getName())){//如果规格名称为0,则该规格不存入数据库
                continue;
            }
            specification.setProductId(id);//设置所关联的主表的productId
            Integer inventory = specification.getInventory();//得到此规格商品的库存数
            String state = (inventory > 0) ? "1" : "0";//如果此规格商品的库存数大于0,则状态为正在销售;如果库存数不大于零,则代表已售罄
            specification.setState(state);

            specificationService.saveSpecification(specification);//将商品的相关规格存入数据库中

        }
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = new Product();
        product.setId(id);
        product.setState("0");
        this.updateProduct(product);

        SpecificationExample specificationExample = new SpecificationExample();
        specificationExample.createCriteria().andProductIdEqualTo(id);
        List<Specification> specifications = specificationMapper.selectByExample(specificationExample);
        for (Specification specification : specifications) {
            specification.setState("-1");
            specificationService.updateSpecificationById(specification);
        }
    }

    /**
     * 查询出所有符合条件的商品的信息并分页,但是不包括商品的规格
     *
     * @param mapTransfer
     * @return
     */
    @Override
    public Map queryProducts(Map mapTransfer) {

        Map map = new HashMap();

        Product product = (Product) mapTransfer.get("product");
        Integer currentPage = (Integer) mapTransfer.get("currentPage");
        Integer pageSize = (Integer) mapTransfer.get("pageSize");

        //得到符合条件的数据的总数
        int count = productMapper.getCount(product);
        //得到符合条件的数据的总页数
        int totalPage = (count + pageSize - 1) / pageSize;
        mapTransfer.put("index", pageSize * (currentPage - 1));//当前页码第一条数据的下标

        //查询出符合条件的商品的全部信息(不包括商品的规格)
        List<Product> products = productMapper.queryProducts(mapTransfer);
        map.put("products", products);
        map.put("totalPage", totalPage);
        map.put("count",count);
        map.put("currentPage",currentPage);
        map.put("pageSize",pageSize);
        return map;
    }

    @Override
    public Map queryProductsPackaged(Map mapTransfer) {
        Map map = this.queryProducts(mapTransfer);
        List<Product> products = (List<Product>) map.get("products");
        for (Product product : products) {
            product.setSurfaceImg(ImgUrlUtils.packaging(product.getSurfaceImg()));
            product.setSlideImg(ImgUrlUtils.packaging(product.getSlideImg()));
            product.setIntroImg(ImgUrlUtils.packaging(product.getIntroImg()));
        }
        return map;
    }

    @Override
    public Map queryProductsWithPriceRange(Map mapTransfer) {

        Map map = new HashMap();

        Product product = (Product) mapTransfer.get("product");
        Integer currentPage = (Integer) mapTransfer.get("currentPage");
        Integer pageSize = (Integer) mapTransfer.get("pageSize");

        //得到符合条件的数据的总数
        int count = productMapper.getCount(product);
        //得到符合条件的数据的总页数
        int totalPage = (count + pageSize - 1) / pageSize;
        mapTransfer.put("index", pageSize * (currentPage - 1));//当前页码第一条数据的下标

        //查询出符合条件的商品的全部信息(包括商品的规格的最大,最小价格)
        List<ProductWithPriceRangeVO> products = productMapper.queryProductsWithPriceRange(mapTransfer);
        map.put("products", products);
        map.put("totalPage", totalPage);
        return map;

    }

    @Override
    public ProductVO getProductById(Integer id) {
        ProductVO productVO = new ProductVO();

        Product product = productMapper.getProductById(id);

        //将存在String中的多个url地址存入list中
        String slideImg = product.getSlideImg();
        String[] slideImgUrls = slideImg.split(",");

        String introImg = product.getIntroImg();
        String[] introImgUrls = introImg.split(",");

        productVO.setSlideImgs(slideImgUrls);
        productVO.setIntroImgs(introImgUrls);
        productVO.setSlideImg(slideImg);
        productVO.setIntroImg(introImg);

        //利用反射,将父类Product中的属性值存入子类ProductVO中
        try {
            FatherToChildUtils.fatherToChild(product, productVO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productVO;
    }

    @Override
    public ProductVO getProductByIdPackaged(Integer id) {
        ProductVO productVO = this.getProductById(id);
        productVO.setSurfaceImg(ImgUrlUtils.packaging(productVO.getSurfaceImg()));
        productVO.setSlideImg(ImgUrlUtils.packaging(productVO.getSlideImg()));
        productVO.setIntroImg(ImgUrlUtils.packaging(productVO.getIntroImg()));
        return productVO;
    }

    @Override
    public void updateProduct(Product product) {
        product.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//更新商品的最近一次修改时间
        productMapper.updateProduct(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateProductAndSpecification(ProductWithBLOBs product, List<Specification> specifications) {

        product.setSurfaceImg(ImgUrlUtils.parse(product.getSurfaceImg()));
        product.setSlideImg(ImgUrlUtils.parse(product.getSlideImg()));
        product.setIntroImg(ImgUrlUtils.parse(product.getIntroImg()));
        productMapper.updateByPrimaryKeySelective(product);
        for (Specification specification : specifications) {
            //规格名称如果为0代表在jsp页面中,改规格商品已被选择删除
            if("0".equals(specification.getName())){
                Specification sp = new Specification();
                sp.setState("-1");
                sp.setId(specification.getId());
                specificationMapper.updateByPrimaryKeySelective(sp);
                continue;
            }
            int i = specificationMapper.updateByPrimaryKeySelective(specification);
        }
        return 1;
    }
}
