package noah.memo.authorityservice.controller;

import noah.memo.authorityservice.service.AccountService;
import noah.memo.framework.bean.request.ReqData;
import noah.memo.framework.bean.response.RspData;
import noah.memo.framework.controller.SecurityController;
import noah.memo.framework.controller.TokenUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制器
 *
 * @author chenyuhao
 * @date 2020-01-31 12:59 下午
 */
@RestController
@RequestMapping("login")
public class LoginController extends SecurityController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "loginByName")
    public RspData loginByName(@RequestBody ReqData data) {
        String loginName = data.getAsString("loginName");
        String loginPassword = data.getAsString("loginPassword");
        return new RspData(accountService.login(loginName, loginPassword));
    }

    @GetMapping("getLoginName")
    public RspData getLoginName() {
        TokenUser user = getTokenUser();
        return new RspData(user.getLoginName());
    }
}
