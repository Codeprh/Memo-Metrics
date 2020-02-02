package noah.memo.memogateway;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import noah.memo.memogateway.gateway.filter.log.LogPostFilter;
import noah.memo.memogateway.gateway.filter.log.LogPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableApolloConfig
@EnableZuulProxy
public class MemoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoGatewayApplication.class, args);
    }

    @Bean
    public LogPreFilter logPreFilter() {
        return new LogPreFilter();
    }

    @Bean
    public LogPostFilter logPostFilter() {
        return new LogPostFilter();
    }


}
