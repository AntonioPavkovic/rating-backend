package com.internship.ratingbackend;
import com.internship.ratingbackend.service.SlackService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RatingBackendApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RatingBackendApplication.class, args);
	}
}
