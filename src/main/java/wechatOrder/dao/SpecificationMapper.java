package wechatOrder.dao;

import org.apache.ibatis.annotations.Param;
import wechatOrder.po.Specification;
import wechatOrder.po.CartItem;
import wechatOrder.po.SpecificationExample;

import java.util.List;

public interface SpecificationMapper {

    void saveSpecification(Specification specification);

    CartItem getSpecificationToCartById(Integer id);

    Specification getSpecificationById(Integer id);

    void updateSpecificationById(Specification specification);

    List<Specification> queryByProductId(Integer id);

    void deleteSpecification(Integer id);

    int countByExample(SpecificationExample example);

    int deleteByExample(SpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Specification record);

    int insertSelective(Specification record);

    List<Specification> selectByExampleWithBLOBs(SpecificationExample example);

    List<Specification> selectByExample(SpecificationExample example);

    Specification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByExampleWithBLOBs(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByExample(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByPrimaryKeySelective(Specification record);

    int updateByPrimaryKeyWithBLOBs(Specification record);

    int updateByPrimaryKey(Specification record);
}