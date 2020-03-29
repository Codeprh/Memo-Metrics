package noah.memo.taskservice;

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
// 必须要声明包名，否则不会对引入的jar进行扫描
@EnableFeignClients(basePackages = {"noah.memo"})
@EnableCircuitBreaker
@EnableApolloConfig

@EntityScan("noah.memo")
@ComponentScan(basePackages = {"noah.memo"})
public class MemoTaskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoTaskServiceApplication.class, args);
    }

}
