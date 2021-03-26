package wechatOrder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author JJ
 * @date 2019/12/4 - 13:28
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableException extends RuntimeException{
    public NotAcceptableException(){

    }

    public NotAcceptableException(String message){
        super(message);
    }
}
