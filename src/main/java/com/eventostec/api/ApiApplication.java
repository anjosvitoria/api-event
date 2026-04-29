package com.eventostec.api;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {

		System.out.println("ACCESS_KEY: " + System.getenv("AWS_ACCESS_KEY_ID"));
		System.out.println("SECRET_KEY: " + System.getenv("AWS_SECRET_ACCESS_KEY"));
		System.out.println(System.getProperty("user.home"));

		SpringApplication.run(ApiApplication.class, args);
	}

}
