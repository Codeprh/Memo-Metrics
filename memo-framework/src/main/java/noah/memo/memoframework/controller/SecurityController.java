package noah.memo.memoframework.controller;

import noah.memo.memoframework.bean.response.RspStatus;
import noah.memo.memoframework.exception.BusinessException;
import noah.memo.memoframework.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenyuhao
 * @date 2020-01-31 1:03 下午
 * 登录控制
 */
public class SecurityController {

    private ThreadLocal<TokenUser> localUser = new ThreadLocal<>();

    @ModelAttribute
    public void initUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            // 主动设null
            localUser.set(null);
            return;
        }
        TokenUser user = JwtUtil.parseJwt(token);
        localUser.set(user);
    }

    public TokenUser getTokenUser() {
        TokenUser user = localUser.get();
        if (user == null) {
            throw new BusinessException(RspStatus.LOGIN_AUTH_FAILED);
        }
        return user;
    }
}
