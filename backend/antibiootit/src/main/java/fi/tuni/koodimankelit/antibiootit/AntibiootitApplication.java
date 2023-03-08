package fi.tuni.koodimankelit.antibiootit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import fi.tuni.koodimankelit.antibiootit.config.SwaggerConfig;


@SpringBootApplication
@Import(SwaggerConfig.class)
public class AntibiootitApplication {

	public static void main(String[] args) {
		SpringApplication.run(AntibiootitApplication.class, args);
	}

}
