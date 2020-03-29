package noah.memo.authorityservice.controller;

import noah.memo.authorityapi.AuthorityApi;
import noah.memo.authorityapi.bean.Account;
import noah.memo.authorityservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 权限服务控制器
 *
 * @author Noah
 * @create 2020-01-28 13:20
 */
@RestController
public class AuthorityInnerController implements AuthorityApi {

    @Autowired
    AccountService accountService;

    /**
     * 通过用户id查找用户信息
     *
     * @param id
     * @return
     */
    @Override
    public Account getCurrentAccount(@PathVariable(value = "id") Integer id) {
        return accountService.getCurrentAccount(id);
    }

}
