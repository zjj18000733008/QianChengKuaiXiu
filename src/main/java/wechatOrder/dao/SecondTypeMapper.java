package wechatOrder.dao;



import org.apache.ibatis.annotations.Param;
import wechatOrder.po.SecondType;
import wechatOrder.po.SecondTypeExample;

import java.util.List;

public interface SecondTypeMapper {
    int countByExample(SecondTypeExample example);

    int deleteByExample(SecondTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SecondType record);

    int insertSelective(SecondType record);

    List<SecondType> selectByExample(SecondTypeExample example);

    SecondType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SecondType record, @Param("example") SecondTypeExample example);

    int updateByExample(@Param("record") SecondType record, @Param("example") SecondTypeExample example);

    int updateByPrimaryKeySelective(SecondType record);

    int updateByPrimaryKey(SecondType record);
}