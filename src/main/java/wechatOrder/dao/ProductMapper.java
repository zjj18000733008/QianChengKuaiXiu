package wechatOrder.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wechatOrder.po.Product;
import wechatOrder.po.ProductExample;
import wechatOrder.po.ProductWithBLOBs;
import wechatOrder.po.vo.ProductWithPriceRangeVO;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {
    void saveProduct(Product product);

    void updateProduct(Product product);

    Product getProductById(Integer id);

    int getCount(Product product);

    List<Product> queryProducts(Map map);

    List<ProductWithPriceRangeVO> queryProductsWithPriceRange(Map map);

    List<Product> queryProductsByName(String productName);

    List<Product> queryProductsByPriceRange(Map map);

    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductWithBLOBs record);

    int insertSelective(ProductWithBLOBs record);

    List<ProductWithBLOBs> selectByExampleWithBLOBs(ProductExample example);

    List<Product> selectByExample(ProductExample example);

    ProductWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(ProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProductWithBLOBs record);

    int updateByPrimaryKey(Product record);
}