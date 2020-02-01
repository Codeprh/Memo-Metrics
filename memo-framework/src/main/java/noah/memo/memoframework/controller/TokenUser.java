package noah.memo.memoframework.controller;

import java.io.Serializable;

/**
 * @author chenyuhao
 * @date 2020-01-31 1:09 下午
 * <p>
 * Token对应用户
 */
public class TokenUser implements Serializable {

    private static final long serialVersionUID = -3380240494403861683L;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 登录名
     */
    private String loginName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
