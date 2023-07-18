package org.sumit.mvcbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"org.sumit.mvcbootapp.beans","org.sumit.mvcbootapp.controllers" })
@EnableWebMvc
public class MvcBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcBootAppApplication.class, args);
	}

}
