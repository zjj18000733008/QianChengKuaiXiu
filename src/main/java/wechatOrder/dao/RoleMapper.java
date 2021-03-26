package wechatOrder.dao;

import org.apache.ibatis.annotations.Param;
import wechatOrder.po.Role;
import wechatOrder.po.RoleExample;
import wechatOrder.po.vo.StaffRole;


import java.util.List;

public interface RoleMapper {

    int bindingRoles(StaffRole staffRole);

    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}