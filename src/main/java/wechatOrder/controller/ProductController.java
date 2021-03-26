package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.SpecificationMapper;
import wechatOrder.exception.SomeParamIsNullException;
import wechatOrder.po.Product;
import wechatOrder.po.Specification;
import wechatOrder.po.Staff;
import wechatOrder.po.vo.ProductSpecificationVO;
import wechatOrder.po.vo.ProductSpecificationVO2;
import wechatOrder.po.vo.ProductVO;
import wechatOrder.po.vo.SpecificationVo;
import wechatOrder.service.ProductService;
import wechatOrder.service.SpecificationService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/11/26 - 15:06
 */
@Controller
@Validated
@RequestMapping("/product")
public class ProductController {

    @Resource(name = "productServiceImpl")
    private ProductService productService;
    @Autowired
    private SpecificationService specificationService;
    @Autowired
    private SpecificationMapper specificationMapper;

    /**
     * 通过此方法来上传商品相关信息以及该种商品的各种规格和相应的价格
     *
     * @param productSpecificationVo
     * @return
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addProduct(@Valid ProductSpecificationVO productSpecificationVo, BindingResult result, HttpServletRequest req) throws Exception {

        if (result.hasErrors()) {
            ArrayList<String> errList = new ArrayList<String>();
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors) {
                errList.add(allError.getDefaultMessage());
            }

            return new ResponseEntity(errList, HttpStatus.PRECONDITION_FAILED);
        }

//        if (productSpecificationVo.getWeight() == null) {
//            throw new SomeParamIsNullException("商品权重不能为空");
//        }

        /* 此处需修改,应当在员工登录后从session中获取id */
//        Integer userId = productSpecificationVo.getUserId();
//        if (userId == null) {
//
//            throw new UserIdIsNullException();
//        }


//        HttpSession session = req.getSession();
//        Staff user = (Staff) session.getAttribute("staff");//判断员工是否登录
//        if(user==null || !"管理员".equals(user.getRole())){
//            return new ResponseEntity("请先登录",HttpStatus.UNAUTHORIZED);
//        }

        productService.addProduct(productSpecificationVo);

        return new ResponseEntity("添加成功", HttpStatus.OK);
    }

    /**
     * 查询符合条件的所有商品信息
     * 可选参数: typeId,userId,state,currentPage(当前页码,从1开始,默认为1),pageSize(每页查询数量,默认为10)
     *
     * @return
     */

    @RequestMapping(value = "/query")
    @ResponseBody
    @Deprecated
    public Map queryProducts(Product product, Integer currentPage, Integer pageSize) {
        Map mapTransfer = new HashMap();

        if (currentPage == null) {
            //如果不传当前页数,则默认为第一页
            currentPage = 1;
        }
        if (pageSize == null) {
            //设置每页默认查询10条数据
            pageSize = 10;
        }

        mapTransfer.put("product", product);
        mapTransfer.put("currentPage", currentPage);
        mapTransfer.put("pageSize", pageSize);

        Map map = productService.queryProducts(mapTransfer);
        return map;
    }

    /**
     * 此方法用于后台商品展示页面的回显
     *
     * @param product
     * @param currentPage
     * @param pageSize
     * @param mv
     * @return
     */
    @RequestMapping("/query/page")
    public ModelAndView queryProductsToPage(Product product, Integer currentPage, Integer pageSize, ModelAndView mv) {
        Map mapTransfer = new HashMap();

        if (currentPage == null) {
            //如果不传当前页数,则默认为第一页
            currentPage = 1;
        }
        if (pageSize == null) {
            //设置每页默认查询10条数据
            pageSize = 10;
        }

        mapTransfer.put("product", product);
        mapTransfer.put("currentPage", currentPage);
        mapTransfer.put("pageSize", pageSize);

        Map map = productService.queryProducts(mapTransfer);
        mv.addObject("map", map);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 此方法用于修改商品页面的回显
     *
     * @param mv
     * @param productId
     * @return
     */
    @RequestMapping("/query/pageDetail")
    public ModelAndView queryProductToPageDetail(ModelAndView mv, Integer productId) {
        ProductVO productVO = productService.getProductByIdPackaged(productId);
        List<Specification> specifications = specificationService.queryByProductId(productId);
        mv.addObject("productVO", productVO);
        mv.addObject("specifications", specifications);
        mv.setViewName("product-update");
        return mv;
    }

    /**
     * 查询符合条件的所有商品信息包括该商品的价格区间
     * 可选参数: typeId,userId,state,currentPage(当前页码,从1开始,默认为1),pageSize(每页查询数量,默认为10)
     *
     * @param product
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/queryWithPriceRange")
    @ResponseBody
    public Map queryProductsWithPriceRange(Product product, Integer currentPage, Integer pageSize) {
        Map mapTransfer = new HashMap();

        if (currentPage == null) {
            //如果不传当前页数,则默认为第一页
            currentPage = 1;
        }
        if (pageSize == null) {
            //设置每页默认查询10条数据
            pageSize = 10;
        }

        mapTransfer.put("product", product);
        mapTransfer.put("currentPage", currentPage);
        mapTransfer.put("pageSize", pageSize);

        Map map = productService.queryProductsWithPriceRange(mapTransfer);
        return map;
    }

    /**
     * 根据商品的id来查询商品的全部信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public Map getProductById(Integer id) {
        Map map = new HashMap();

        if (id == null) {
            throw new SomeParamIsNullException("商品的Id不能为空");
        }

        ProductVO product = productService.getProductById(id);//根据id查询出对应的商品
        List<Specification> specifications = specificationService.queryByProductId(id);//根据商品id查询出对应的规格商品的信息

        map.put("product", product);
        map.put("specifications", specifications);

        System.out.println("==============================");
        return map;
    }

    /**
     * 修改商品的信息
     * 必传参数: 商品的id
     * 可选参数: productName,overview,typeId,weight,state
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateProduct(Product product, HttpServletRequest req) {

        if (product.getId() == null) {
            throw new SomeParamIsNullException("商品的Id不能为空");
        }

        productService.updateProduct(product);


        return new ResponseEntity("修改成功", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/updateProductAndSpecification", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateProductAndSpecification(ProductSpecificationVO2 productSpecificationVO) {

        Integer productId = productSpecificationVO.getId();
        if (productId == null) {
            return new ResponseEntity("productId为空", HttpStatus.BAD_REQUEST);
        }
        List<Specification> specifications = productSpecificationVO.getSpecifications();
        for (Specification specification : specifications) {
            Integer product_id = specification.getProductId();
            if (product_id == null || product_id != productId) {
                return new ResponseEntity("规格商品与商品不对应", HttpStatus.BAD_REQUEST);
            }
        }

        productService.updateProductAndSpecification(productSpecificationVO, specifications);

        return new ResponseEntity("修改成功", HttpStatus.OK);
    }

    /**
     * 管理员能"删除"商品,这里的删除指的是将商品的状态修改为不可用,并非从数据库中删除
     *
     * @param id
     * @param req
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity deleteProduct(Integer id, HttpServletRequest req) {

        productService.deleteProduct(id);

        return new ResponseEntity("下架成功", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/deleteProductInBatch")
    @ResponseBody
    public ResponseEntity deleteProductInBatch(Integer[] ids) {
        for (Integer id : ids) {
            productService.deleteProduct(id);
        }
        return new ResponseEntity("下架成功", HttpStatus.OK);
    }

    /**
     * 可选参数: name,originalPrice,currentPrice,inventory,img,state
     * 必填参数: id
     *
     * @param specification
     * @param req
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/updateSpecification")
    @ResponseBody
    public ResponseEntity updateSpecification(Specification specification, HttpServletRequest req) {
        if (specification == null || specification.getId() == null) {
            return new ResponseEntity("参数为空", HttpStatus.PRECONDITION_REQUIRED);
        }


        specificationService.updateSpecificationById(specification);

        return new ResponseEntity("修改成功", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/addSpecification")
    @ResponseBody
    public ResponseEntity addSpecification(@Valid SpecificationVo specificationVo, BindingResult result) {

        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            ArrayList<String> errList = new ArrayList<String>();
            for (ObjectError allError : allErrors) {
                errList.add(allError.getDefaultMessage());
            }
            return new ResponseEntity(errList, HttpStatus.PRECONDITION_REQUIRED);
        }


        List<Specification> specifications = specificationVo.getSpecifications();

        for (Specification specification : specifications) {
            if (specification.getProductId() == null) {
                return new ResponseEntity("未指明productId", HttpStatus.BAD_REQUEST);
            }
        }

        for (Specification specification : specifications) {
            if ("0".equals(specification.getName())) {
                continue;
            }
            specification.setState(specification.getInventory() > 0 ? "1" : "0");
            specificationMapper.insert(specification);
        }

        return new ResponseEntity("操作成功", HttpStatus.OK);
    }

    /**
     * 管理员能调用此接口
     * 并不是真的删除该规格商品,不能直接删除,因为用户的历史订单需要用到该规格商品
     * 所以只是把该规格商品的状态修改
     *
     * @param id
     * @param req
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/deleteSpecificatoin")
    @ResponseBody
    public ResponseEntity deleteSpecification(Integer id, HttpServletRequest req) {
        if (id == null) {
            return new ResponseEntity("参数为空", HttpStatus.PRECONDITION_REQUIRED);
        }
//        HttpSession session = req.getSession();
//        Staff staff;
//        if(session==null || (staff= (Staff) session.getAttribute("staff"))==null || staff.getRole()!="管理员"){
//            return new ResponseEntity("未授权操作",HttpStatus.UNAUTHORIZED);
//        }

        Specification specification = new Specification();
        specification.setId(id);
        specification.setState("-1");
        specificationService.updateSpecificationById(specification);

        return new ResponseEntity("删除成功", HttpStatus.OK);
    }


}
