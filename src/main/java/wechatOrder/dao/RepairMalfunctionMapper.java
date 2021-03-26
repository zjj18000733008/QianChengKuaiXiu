package wechatOrder.dao;


import org.apache.ibatis.annotations.Param;
import wechatOrder.po.RepairMalfunction;
import wechatOrder.po.RepairMalfunctionExample;

import java.util.List;

public interface RepairMalfunctionMapper {

    void saveMalfunction(RepairMalfunction repairMalfunction);

    int countByExample(RepairMalfunctionExample example);

    int deleteByExample(RepairMalfunctionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepairMalfunction record);

    int insertSelective(RepairMalfunction record);

    List<RepairMalfunction> selectByExample(RepairMalfunctionExample example);

    RepairMalfunction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepairMalfunction record, @Param("example") RepairMalfunctionExample example);

    int updateByExample(@Param("record") RepairMalfunction record, @Param("example") RepairMalfunctionExample example);

    int updateByPrimaryKeySelective(RepairMalfunction record);

    int updateByPrimaryKey(RepairMalfunction record);
}