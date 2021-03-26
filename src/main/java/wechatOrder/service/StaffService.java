package wechatOrder.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import wechatOrder.po.Staff;
import wechatOrder.po.StaffExample;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/6 - 20:15
 */
public interface StaffService extends UserDetailsService {

    void save(Staff staff,Integer[] roles);

    /**
     * 根据staff的id查询出其权限
     * @param id
     * @return
     */
    List<String> getAuthorities(Integer id);

    /**
     * 查询出所有的ROLE_DELEVERYMAN和ROLE_PICKER
     * @return
     */
    List<Staff> queryDeleverymansAndPickers();

    /**
     * 通过staffId查询出staff的信息
     * @param staffId
     * @return
     */
    Staff getStaffInfo(Integer staffId);

    List<Staff> findAll();
}
