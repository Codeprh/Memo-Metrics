package noah.memo.memoauthorityapi;

import feign.hystrix.FallbackFactory;
import noah.memo.memoauthorityapi.bean.Account;
import noah.memo.memoframework.log.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 权限暴露服务api
 *
 * @author noah
 */
@FeignClient(name = AuthorityApi.SERVICE_NAME, fallback = AuthorityApi.DefaultAuthorityApiFallback.class, path = "authoritys")
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

    @PostMapping(value = "/hello")
    String hello();

    @Component
    class DefaultAuthorityApiFallback implements AuthorityApi {

        @Override
        public Account getCurrentAccount(@PathVariable(value = "id") Integer id) {
            System.out.println("获取用户信息发生错误");
            return null;
        }

        @Override
        public String hello() {
            System.out.println("hello调用失败");
            return null;
        }
    }


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
