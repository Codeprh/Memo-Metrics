package noah.memo.memotaskservice.feignclient;


import noah.memo.memoauthorityapi.AuthorityApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = AuthorityApi.SERVICE_NAME, url = "http://127.0.0.1:6000/authoritys")
public interface AuthorityApiFeignClient extends AuthorityApi {
    //todo:fallback = DefaultAuthorityApiFallback.class
}
