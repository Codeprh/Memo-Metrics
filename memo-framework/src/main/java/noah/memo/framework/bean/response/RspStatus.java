package noah.memo.framework.bean.response;

import javax.servlet.http.HttpServletResponse;

/**
 * 响应状态
 *
 * @author noah
 */
public enum RspStatus {
    /**
     * 成功
     */
    SUCCESS(HttpServletResponse.SC_OK, "Operation is Successful"),

    /**
     * 失败
     */
    FAIL(HttpServletResponse.SC_BAD_REQUEST, "Biz Exception"),

    /**
     * 权限不足
     */
    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Request Unauthorized"),

    /**
     * 404资源没找到
     */
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 Not Found"),

    /**
     * 请求不合法
     */
    MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "Message Can't be Read"),

    /**
     * 请求方法不支持
     */
    METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Supported"),

    /**
     * 请求media类型不支持
     */
    MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Media Type Not Supported"),

    /**
     * 请求拒绝
     */
    REQ_REJECT(HttpServletResponse.SC_FORBIDDEN, "Request Rejected"),

    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),

    /**
     * 缺少请求参数
     */
    PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST, "Missing Required Parameter"),

    /**
     * 请求参数类型不匹配
     */
    PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Type Mismatch"),

    /**
     * 请求参数绑定失败
     */
    PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Binding Error"),

    /**
     * 请求参数校验失败
     */
    PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Validation Error"),

    /**
     * 登录校验
     */
    LOGIN_AUTH_FAILED(HttpServletResponse.SC_UNAUTHORIZED, "Login Auth Fail");

    public int status;
    public String msg;

    RspStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
