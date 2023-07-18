package org.sumitexam.app.SumitFinalExam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.sumitexam.app.SumitFinalExam.controllers",
"org.sumitexam.app.SumitFinalExam.services"})
@EntityScan(basePackages = {"org.sumitexam.app.SumitFinalExam.entity"})
@EnableJpaRepositories(basePackages = {"org.sumitexam.app.SumitFinalExam.repository"})
public class SumitFinalExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SumitFinalExamApplication.class, args);
	}

}
