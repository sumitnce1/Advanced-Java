package org.sumit.boot.rest.firstrestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.sumit.boot.rest.firstrestapp.controllers"})
public class FirstRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstRestAppApplication.class, args);
	}

}
