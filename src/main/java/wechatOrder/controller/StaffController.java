package wechatOrder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.StaffMapper;
import wechatOrder.po.Order;
import wechatOrder.po.Staff;
import wechatOrder.po.StaffExample;
import wechatOrder.po.vo.QueryOrdersForStaffVo;
import wechatOrder.service.OrderService;
import wechatOrder.service.StaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 象牙塔员工
 *
 * @author JJ
 * @date 2019/12/6 - 20:01
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StaffMapper staffMapper;
    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    /**
     * 只有管理员才能添加成员
     *
     * @param staff
     * @param result
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/add")
    public ResponseEntity add(Integer[] roles,@Validated Staff staff, BindingResult result, HttpServletRequest req) {
        ArrayList<String> errorList = new ArrayList<String>();
        //校验参数
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors) {
                logger.warn(allError.getDefaultMessage());
                errorList.add(allError.getDefaultMessage());
            }
            return new ResponseEntity(errorList, HttpStatus.PRECONDITION_REQUIRED);
        }
        staffService.save(staff,roles);
        return new ResponseEntity("添加成功", HttpStatus.OK);
    }

    /**
     * @param state
     * @return
     */
    @RequestMapping("/queryDeleverymansAndPickers")
    public ResponseEntity queryDeleverymansAndPickers(String state, String[] roles) {

        List<Staff> staff = staffService.queryDeleverymansAndPickers();

        return new ResponseEntity(staff, HttpStatus.OK);
    }

    @RequestMapping("/myProfile")
    public ModelAndView myProfile(ModelAndView mv) {
        SecurityContext context = SecurityContextHolder.getContext();
        Staff staff = (Staff) context.getAuthentication().getPrincipal();
        Staff myself = staffService.getStaffInfo(staff.getId());
        mv.addObject("myself", myself);
        mv.setViewName("myProfile");
        return mv;
    }

    /**
     * 员工修改自己的信息
     *
     * @param staff
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_STAFF')")
    @RequestMapping("/update")
    public ResponseEntity update(Staff staff) {
        Staff myself = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        staff.setAuthorities(null);
        staff.setUsername(null);
        staff.setId(myself.getId());
        int result = staffMapper.updateByPrimaryKeySelective(staff);
        if (result == 0) {
            return new ResponseEntity("修改失败", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("修改成功", HttpStatus.OK);
        }
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv){
        List<Staff> all = staffService.findAll();
        mv.addObject("staffs",all);
        mv.setViewName("staff-list");
        return mv;
    }

}
