package wechatOrder.dao;

import org.apache.ibatis.annotations.Param;
import wechatOrder.po.Staff;
import wechatOrder.po.StaffExample;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/6 - 19:53
 */
public interface StaffMapper {

    Integer save(Staff staff);

    List<Staff> queryDeleverymansAndPickers();

    List<String> getAuthorities(Integer staffId);

    Staff getStaffById(Integer id);

//    Staff getByUsernameAndPassword(Staff staff);

//    void saveStaff(Staff staff);

    int countByExample(StaffExample example);

    int deleteByExample(StaffExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Staff record);

    int insertSelective(Staff record);

    List<Staff> selectByExample(StaffExample example);

    Staff selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Staff record, @Param("example") StaffExample example);

    int updateByExample(@Param("record") Staff record, @Param("example") StaffExample example);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);
}
