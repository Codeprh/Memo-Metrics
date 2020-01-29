package noah.memo.memogateway.gateway.config.cat;

import noah.memo.memoframework.cat.CatRestInterceptor;
import noah.memo.memoframework.cat.CatServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Configuration
public class CatFilterConfigure {

    /**
     * cat 过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean catFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CatServletFilter filter = new CatServletFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-filter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * cat tracing
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new CatRestInterceptor()));
        return restTemplate;
    }
}
