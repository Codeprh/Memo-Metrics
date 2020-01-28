package noah.memo.memoauthorityapi.exception;

import noah.memo.memoframework.exception.BusinessException;

/**
 * 描述:
 * 权限全部异常
 *
 * @author Noah
 * @create 2020-01-28 17:42
 */
public class AuthorityException extends BusinessException {

    private static final long serialVersionUID = 3544620752559351387L;

    public static final AuthorityException ACCOUNT_NOT_FIND = new AuthorityException(1001, "账号不存在");

    public AuthorityException(String msg) {
        super(msg);
    }

    public AuthorityException(int code, String msg) {
        super(code, msg);
    }
}
