package noah.memo.memoauthorityservice;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication(scanBasePackages = "noah.memo")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableApolloConfig

@EntityScan("noah.memo")
public class MemoAuthorityApplication {

    @GetMapping(name = "/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(MemoAuthorityApplication.class, args);
    }

}
