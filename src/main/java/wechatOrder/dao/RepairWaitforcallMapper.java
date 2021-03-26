package wechatOrder.dao;


import org.apache.ibatis.annotations.Param;
import wechatOrder.po.RepairWaitforcall;
import wechatOrder.po.RepairWaitforcallExample;

import java.util.List;

public interface RepairWaitforcallMapper {
    int countByExample(RepairWaitforcallExample example);

    int deleteByExample(RepairWaitforcallExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepairWaitforcall record);

    int insertSelective(RepairWaitforcall record);

    List<RepairWaitforcall> selectByExample(RepairWaitforcallExample example);

    RepairWaitforcall selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepairWaitforcall record, @Param("example") RepairWaitforcallExample example);

    int updateByExample(@Param("record") RepairWaitforcall record, @Param("example") RepairWaitforcallExample example);

    int updateByPrimaryKeySelective(RepairWaitforcall record);

    int updateByPrimaryKey(RepairWaitforcall record);
}