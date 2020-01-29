package noah.memo.memoframework.exception;

import noah.memo.memoframework.bean.response.RspStatus;

/**
 * 业务异常
 *
 * @author noah
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1650804584531431672L;

    public static final BusinessException SERVICE_ERROR = new BusinessException(-1000, "系统异常");

    public int code;
    public String msg;

    public BusinessException(String msg) {
        super(msg);
        this.code = RspStatus.FAIL.status;
        this.msg = msg;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}