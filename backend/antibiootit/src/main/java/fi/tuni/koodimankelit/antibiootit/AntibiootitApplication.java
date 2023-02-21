package fi.tuni.koodimankelit.antibiootit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import fi.tuni.koodimankelit.antibiootit.database.DiagnoseRepository;


@SpringBootApplication
@EnableMongoRepositories
public class AntibiootitApplication {

	@Autowired
    DiagnoseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AntibiootitApplication.class, args);
	}

}
