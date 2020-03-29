package noah.memo.registrationcenter;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer
@EnableApolloConfig
public class MemoRegistrationCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoRegistrationCenterApplication.class, args);
    }

}
