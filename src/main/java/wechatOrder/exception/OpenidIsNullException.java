package wechatOrder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author JJ
 * @date 2019/12/1 - 14:04
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE,reason = "由传来的wxCode得不到openid")
public class OpenidIsNullException extends RuntimeException{

}
