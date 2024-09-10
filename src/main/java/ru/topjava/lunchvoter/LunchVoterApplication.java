package ru.topjava.lunchvoter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.topjava.lunchvoter.repository")
@EntityScan(basePackages = "ru.topjava.lunchvoter.model")
public class LunchVoterApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunchVoterApplication.class, args);
	}

}
