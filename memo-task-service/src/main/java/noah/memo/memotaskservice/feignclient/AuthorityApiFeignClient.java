package noah.memo.memotaskservice.feignclient;


import noah.memo.memoauthorityapi.AuthorityApi;
import noah.memo.memoauthorityapi.fallback.DefaultAuthorityApiFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = AuthorityApi.SERVICE_NAME, fallback = DefaultAuthorityApiFallback.class)
public interface AuthorityApiFeignClient extends AuthorityApi {
}
