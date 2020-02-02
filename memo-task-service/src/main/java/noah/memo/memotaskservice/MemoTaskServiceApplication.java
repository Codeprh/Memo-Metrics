package noah.memo.memotaskservice;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableApolloConfig

@EntityScan("noah.memo")
@ComponentScan(basePackages = {"noah.memo"})

public class MemoTaskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoTaskServiceApplication.class, args);
    }

}
