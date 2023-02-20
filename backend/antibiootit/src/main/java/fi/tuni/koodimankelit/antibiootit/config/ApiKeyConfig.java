package fi.tuni.koodimankelit.antibiootit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fi.tuni.koodimankelit.antibiootit.authentication.ApiKeyFilter;

@Configuration
public class ApiKeyConfig {

    @Value("${secret}")
    private String apiKeySecret;

    @Bean
    public FilterRegistrationBean<ApiKeyFilter> requestFilter() {
        FilterRegistrationBean<ApiKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiKeyFilter(apiKeySecret));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}