package noah.memo.memoauthorityservice.service;

import noah.memo.memoauthorityapi.bean.Account;
import noah.memo.memoauthorityservice.repository.AccountRepository;
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

        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }

        return null;
    }
}
