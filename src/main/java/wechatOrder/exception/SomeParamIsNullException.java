package wechatOrder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author JJ
 * @date 2019/11/27 - 20:33
 */
@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED,reason = "某个参数不能为空")
public class SomeParamIsNullException extends RuntimeException {
    public SomeParamIsNullException(String msg){
        super(msg);
    }
    public SomeParamIsNullException(){

    }
}
