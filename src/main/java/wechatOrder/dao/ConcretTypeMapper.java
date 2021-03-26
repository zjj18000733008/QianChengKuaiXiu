package wechatOrder.dao;

import org.apache.ibatis.annotations.Param;
import wechatOrder.po.ConcretType;
import wechatOrder.po.ConcretTypeExample;

import java.util.List;


public interface ConcretTypeMapper {
    int countByExample(ConcretTypeExample example);

    int deleteByExample(ConcretTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConcretType record);

    int insertSelective(ConcretType record);

    List<ConcretType> selectByExample(ConcretTypeExample example);

    ConcretType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConcretType record, @Param("example") ConcretTypeExample example);

    int updateByExample(@Param("record") ConcretType record, @Param("example") ConcretTypeExample example);

    int updateByPrimaryKeySelective(ConcretType record);

    int updateByPrimaryKey(ConcretType record);
}