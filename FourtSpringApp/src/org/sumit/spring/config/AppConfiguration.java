package org.sumit.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.sumit.spring.Users;

@Configuration
public class AppConfiguration {
	@Bean
	public Users objUser() 
	{
		return new Users();
	}
}
