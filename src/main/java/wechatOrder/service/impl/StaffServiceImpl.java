package wechatOrder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import wechatOrder.dao.RoleMapper;
import wechatOrder.dao.StaffMapper;
import wechatOrder.po.Staff;
import wechatOrder.po.StaffExample;
import wechatOrder.po.vo.StaffRole;
import wechatOrder.service.StaffService;
import wechatOrder.util.Md5Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JJ
 * @date 2019/12/6 - 20:16
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Override
//    public Staff login(Staff staff) {
////        staff.setPassword(Md5Utils.MD5Encode(password, "utf8"));
//        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
//        Staff staff2 = staffMapper.getByUsernameAndPassword(staff);
//        return staff2;
//    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Staff staff, Integer[] roles) {
//        staff.setPassword(Md5Utils.MD5Encode(staff.getPassword(),"utf8"));

        //将员工信息插入数据库
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staff.setRegistrationDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        staffMapper.save(staff);

        //给员工绑定角色
        for (Integer role : roles) {
            StaffRole staffRole = new StaffRole(staff.getId(), role);
            roleMapper.bindingRoles(staffRole);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询出staff对象
        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria().andUsernameEqualTo(username);
        List<Staff> staffList = staffMapper.selectByExample(staffExample);
        if (staffList.size() == 0) {
            return null;
        }
        Staff staff = staffList.get(0);

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();//创建一个list用于存放权限
        List<String> authorities = this.getAuthorities(staff.getId());//根据id得到其权限
        for (String authority : authorities) {//将权限存入list中
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }

        staff.setAuthorities(simpleGrantedAuthorities)
                .setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .setEnabled("1".equals(staff.getState()));

        return staff;
    }

    @Override
    public List<String> getAuthorities(Integer id) {
        return staffMapper.getAuthorities(id);
    }

    @Override
    public List<Staff> queryDeleverymansAndPickers() {
        return staffMapper.queryDeleverymansAndPickers();
    }

    @Override
    public Staff getStaffInfo(Integer staffId) {
        Staff staff = staffMapper.selectByPrimaryKey(staffId);
        List<String> authorities = this.getAuthorities(staffId);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();
        for (String authority : authorities) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
            authorityList.add(simpleGrantedAuthority);
        }
        staff.setAuthorities(authorityList);
        return staff;
    }

    @Override
    public List<Staff> findAll() {
        StaffExample staffExample = new StaffExample();
        List<Staff> staffs = staffMapper.selectByExample(staffExample);
        for (Staff staff : staffs) {
            List<String> authorities = this.getAuthorities(staff.getId());
            ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
            for (String authority : authorities) {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority));
            }
            staff.setAuthorities(simpleGrantedAuthorities);
        }
        return staffs;
    }
}
