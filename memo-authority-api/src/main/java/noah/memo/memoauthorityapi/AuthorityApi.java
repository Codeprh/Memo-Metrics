package noah.memo.memoauthorityapi;

import noah.memo.memoauthorityapi.bean.Account;
import noah.memo.memoframework.annotation.CatAnnotation;
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
@RequestMapping(value = "/inner/authority")
public interface AuthorityApi {

    String SERVICE_NAME = "authority-service";

    /**
     * 通过用户id，获取用户相关的信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCurrentAccount/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Account getCurrentAccount(@PathVariable(value = "id") Integer id);

}
