package wechatOrder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author JJ
 * @date 2019/12/4 - 8:23
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST,reason = "购物车不存在或购物车中没有数据")
public class CartIsNotExistException extends RuntimeException {

}
