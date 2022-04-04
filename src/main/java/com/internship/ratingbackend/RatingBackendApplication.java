package com.internship.ratingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RatingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingBackendApplication.class, args);
	}

}
