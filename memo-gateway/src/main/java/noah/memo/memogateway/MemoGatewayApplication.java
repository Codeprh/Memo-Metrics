package noah.memo.memogateway;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableApolloConfig
@EnableZuulProxy
public class MemoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoGatewayApplication.class, args);
    }

}
