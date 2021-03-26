package wechatOrder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author JJ
 * @date 2019/12/1 - 15:08
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "此操作未经授权")
public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(){

    }

    public UnAuthorizedException(String message){
        super(message);
    }
}
