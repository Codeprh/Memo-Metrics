package noah.memo.authorityservice.controller;

import noah.memo.authorityapi.bean.Account;
import noah.memo.authorityapi.exception.AuthorityException;
import noah.memo.authorityservice.service.AccountService;
import noah.memo.framework.bean.response.RspData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 账号控制器
 *
 * @author Noah
 * @create 2020-01-28 16:46
 */
@RestController
@RequestMapping(value = "/dc/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * 通过用户id获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCurrentAccount/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RspData getCurrentAccount(@PathVariable(value = "id") Integer id) {

        Account account = accountService.getCurrentAccount(id);

        if (account == null) {
            RspData.getFailRspData(AuthorityException.ACCOUNT_NOT_FIND.msg);
        }

        return RspData.getSuccessRspData(account);
    }
}
