package noah.memo.memoauthorityapi;

import feign.hystrix.FallbackFactory;
import noah.memo.memoauthorityapi.bean.Account;
import noah.memo.memoauthorityapi.fallback.DefaultAuthorityApiFallback;
import noah.memo.memoframework.log.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 权限暴露服务api
 *
 * @author noah
 */
@FeignClient(name = AuthorityApi.SERVICE_NAME, fallback = DefaultAuthorityApiFallback.class, path = "authoritys")
public interface AuthorityApi {

    String SERVICE_NAME = "authority-service";

    /**
     * 通过用户id，获取用户相关的信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/inner/authority/getCurrentAccount/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Account getCurrentAccount(@PathVariable(value = "id") Integer id);


    /**
     * fallback 与fallbackFactory不能同时使用
     */
    //    @Component
    class DefaultAuthorFallbackFactory implements FallbackFactory<AuthorityApi> {

        @Override
        public AuthorityApi create(Throwable throwable) {
            Logger.error("Fallback Error", throwable);
            return null;
        }
    }

}
