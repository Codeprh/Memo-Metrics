package noah.memo.memoauthorityapi.fallback;

import noah.memo.memoauthorityapi.AuthorityApi;
import noah.memo.memoauthorityapi.bean.Account;

/**
 * 描述:
 * 权限暴露服务降级，处理控制器
 *
 * @author Noah
 * @create 2020-01-28 13:04
 */
public class DefaultAuthorityApiFallback implements AuthorityApi {

    @Override
    public Account getCurrentAccount(Integer id) {
        return null;
    }
}
