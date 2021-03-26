package wechatOrder.dao;

import org.apache.ibatis.annotations.Param;
import wechatOrder.po.RepairMalfunctionItem;
import wechatOrder.po.RepairMalfunctionItemExample;

import java.util.List;

public interface RepairMalfunctionItemMapper {
    int countByExample(RepairMalfunctionItemExample example);

    int deleteByExample(RepairMalfunctionItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepairMalfunctionItem record);

    int insertSelective(RepairMalfunctionItem record);

    List<RepairMalfunctionItem> selectByExample(RepairMalfunctionItemExample example);

    RepairMalfunctionItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepairMalfunctionItem record, @Param("example") RepairMalfunctionItemExample example);

    int updateByExample(@Param("record") RepairMalfunctionItem record, @Param("example") RepairMalfunctionItemExample example);

    int updateByPrimaryKeySelective(RepairMalfunctionItem record);

    int updateByPrimaryKey(RepairMalfunctionItem record);
}