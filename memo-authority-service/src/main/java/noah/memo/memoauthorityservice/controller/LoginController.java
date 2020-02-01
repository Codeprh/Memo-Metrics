package noah.memo.memoauthorityservice.controller;

import noah.memo.memoauthorityservice.service.AccountService;
import noah.memo.memoframework.bean.request.ReqData;
import noah.memo.memoframework.bean.response.RspData;
import noah.memo.memoframework.controller.SecurityController;
import noah.memo.memoframework.controller.TokenUser;
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
