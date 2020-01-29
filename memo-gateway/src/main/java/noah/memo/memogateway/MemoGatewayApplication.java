package noah.memo.memogateway;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import noah.memo.memoframework.cat.CatRestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableApolloConfig
@EnableZuulProxy
public class MemoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoGatewayApplication.class, args);
    }


}
