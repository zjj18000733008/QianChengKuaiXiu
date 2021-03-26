package wechatOrder.exception;

/**
 * @author JJ
 * @date 2020/1/15 - 20:42
 */
public class BussinessRuntimeException extends RuntimeException {
    private String msg;

    public BussinessRuntimeException(){
        super();
    }

    public BussinessRuntimeException(String msg){
        super(msg);
        this.msg=msg;
    }

    public String getMsg(){
        return msg;
    }
}
