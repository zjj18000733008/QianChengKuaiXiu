package wechatOrder.service;

import wechatOrder.po.Product;
import wechatOrder.po.ProductWithBLOBs;
import wechatOrder.po.Specification;
import wechatOrder.po.vo.ProductSpecificationVO;
import wechatOrder.po.vo.ProductVO;

import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/11/26 - 13:37
 */
public interface ProductService {

    void addProduct(ProductSpecificationVO productSpecificationVo) throws Exception;

    Map queryProducts(Map map);

    /**
     * 返回查询出的商品列表,同时将url包装成<img .../>用于页面回显
     * @param map
     * @return
     */
    Map queryProductsPackaged(Map map);

    Map queryProductsWithPriceRange(Map map);

    ProductVO getProductById(Integer id);

    ProductVO getProductByIdPackaged(Integer id);

    void updateProduct(Product product);

    /**
     * 将商品下架
     * @param id
     */
    void deleteProduct(Integer id);

    int updateProductAndSpecification(ProductWithBLOBs product, List<Specification> specifications);
}
