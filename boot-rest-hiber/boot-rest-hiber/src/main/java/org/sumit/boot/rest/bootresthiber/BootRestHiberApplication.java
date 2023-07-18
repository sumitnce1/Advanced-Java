package org.sumit.boot.rest.bootresthiber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.sumit.boot.rest.bootresthiber.dao",
		"org.sumit.boot.rest.bootresthiber.controller"})
@EntityScan(basePackages = {"org.sumit.boot.rest.bootresthiber.entity"})
@EnableJpaRepositories(basePackages = {"org.sumit.boot.rest.bootresthiber.repository"})
public class BootRestHiberApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRestHiberApplication.class, args);
	}
}
