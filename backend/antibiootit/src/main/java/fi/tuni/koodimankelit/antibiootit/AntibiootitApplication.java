package fi.tuni.koodimankelit.antibiootit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AntibiootitApplication {

	public static void main(String[] args) {
		System.out.println(System.getenv("apikey"));
		SpringApplication.run(AntibiootitApplication.class, args);
	}

}
