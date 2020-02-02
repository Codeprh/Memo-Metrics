package noah.memo.memoauthorityapi.fallback;

import noah.memo.memoauthorityapi.AuthorityApi;
import noah.memo.memoauthorityapi.bean.Account;
import noah.memo.memoframework.log.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 描述:
 * fallback class
 *
 * @author Noah
 * @create 2020-02-02 16:32
 */
@Component
public class DefaultAuthorityApiFallback implements AuthorityApi {

    @Override
    public Account getCurrentAccount(@PathVariable(value = "id") Integer id) {
        Logger.info("获取用户信息发生错误");
        return null;
    }
}
