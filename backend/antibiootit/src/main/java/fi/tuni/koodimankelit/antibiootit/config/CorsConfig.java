package fi.tuni.koodimankelit.antibiootit.config;

import org.junit.jupiter.api.Order;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fi.tuni.koodimankelit.antibiootit.filter.CorsFilter;

@Configuration
@Order(1)
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("*");
        return registrationBean;
    }
    
}
