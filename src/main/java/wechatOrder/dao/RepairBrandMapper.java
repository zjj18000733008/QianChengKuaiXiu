package wechatOrder.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wechatOrder.po.RepairBrand;
import wechatOrder.po.RepairBrandExample;

public interface RepairBrandMapper {
    int countByExample(RepairBrandExample example);

    int deleteByExample(RepairBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepairBrand record);

    int insertSelective(RepairBrand record);

    List<RepairBrand> selectByExample(RepairBrandExample example);

    RepairBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepairBrand record, @Param("example") RepairBrandExample example);

    int updateByExample(@Param("record") RepairBrand record, @Param("example") RepairBrandExample example);

    int updateByPrimaryKeySelective(RepairBrand record);

    int updateByPrimaryKey(RepairBrand record);
}