package wechatOrder.resolver;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import wechatOrder.exception.*;

/**
 * @author JJ
 * @date 2020/1/15 - 20:47
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
