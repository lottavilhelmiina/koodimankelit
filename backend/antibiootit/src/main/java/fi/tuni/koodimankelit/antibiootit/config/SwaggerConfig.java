package fi.tuni.koodimankelit.antibiootit.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("Antibiootit API")
                .packagesToScan("fi.tuni.koodimankelit.antibiootit")
                .build();
    }
}