package fi.tuni.koodimankelit.antibiootit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Antibiotics API")
                .version("1.0.0")
                .description("The Antibiotics API provides accurate and up-to-date information on appropriate antibiotic recommendations for child patients.\n\n### Developers\n\nJuho Rantala, juho.a.rantala@tuni.fi\n\nNeera Kiviluoma, neera.kiviluoma@tuni.fi\n\nEveliina Sundberg, eveliina.sundberg@tuni.fi")
                );
    }    
}