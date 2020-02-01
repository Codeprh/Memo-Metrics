package noah.memo.memoauthorityservice.service;

import noah.memo.memoauthorityapi.bean.Account;
import noah.memo.memoauthorityservice.repository.AccountRepository;
import noah.memo.memoframework.controller.TokenUser;
import noah.memo.memoframework.exception.BusinessException;
import noah.memo.memoframework.log.Logger;
import noah.memo.memoframework.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 描述:
 * 用户业务处理类
 *
 * @author Noah
 * @create 2020-01-28 17:01
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * 通过用户id来获取当前用户
     *
     * @param id
     * @return
     */
    public Account getCurrentAccount(Integer id) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        return optionalAccount.orElse(null);

    }


    /**
     * 登录
     *
     * @param loginName     登录名
     * @param loginPassword 密码
     */
    public String login(String loginName, String loginPassword) {
        Account account = accountRepository.findByLoginNameAndLoginPassword(loginName, loginPassword);
        if (account == null) {
            throw new BusinessException("用户名或密码错误");
        }
        TokenUser user = new TokenUser();
        user.setUserId(account.getId());
        user.setLoginName(account.getLoginName());
        // 30分钟
        return JwtUtil.createJwt(1800000L, user);
    }
}
