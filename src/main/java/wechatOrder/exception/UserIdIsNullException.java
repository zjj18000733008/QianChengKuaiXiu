package wechatOrder.exception;

import org.omg.CORBA.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import wechatOrder.po.User;

/**
 * @author JJ
 * @date 2019/11/27 - 18:50
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "userId不能为空")
public class UserIdIsNullException extends RuntimeException {

}
