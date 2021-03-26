package wechatOrder.util;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author JJ
 * @date 2020/2/9 - 15:34
 */
public class SecurityUtils {

    public static Object getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }
}
