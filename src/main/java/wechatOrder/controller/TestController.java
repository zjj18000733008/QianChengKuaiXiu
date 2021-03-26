package wechatOrder.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wechatOrder.po.Staff;
import wechatOrder.po.User;
import wechatOrder.util.SecurityUtils;

/**
 * @author JJ
 * @date 2020/2/3 - 22:57
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public void test(){
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        User user = (User) authentication.getPrincipal();
        User user = (User) SecurityUtils.getCurrentUser();
        System.out.println("user>>>"+user.getId());
    }
}
