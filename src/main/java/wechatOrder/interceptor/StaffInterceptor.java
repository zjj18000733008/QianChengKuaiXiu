package wechatOrder.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.exception.UnAuthorizedException;
import wechatOrder.po.Staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.handler.Handler;

/**
 * @author JJ
 * @date 2019/12/9 - 22:28
 */
public class StaffInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        HttpSession session = req.getSession();
//        Staff staff = (Staff) session.getAttribute("staff");
//        if(staff==null || !"管理员".equals(staff.getRole())){
//            System.out.println("操作未授权");
//            throw new UnAuthorizedException();
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
