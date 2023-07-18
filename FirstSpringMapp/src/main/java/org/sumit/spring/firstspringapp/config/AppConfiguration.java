package org.sumit.spring.firstspringapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.sumit.spring.firstspringapp.beans.Account;
import org.sumit.spring.firstspringapp.beans.AmazonAccount;
import org.sumit.spring.firstspringapp.beans.FacebookAccount;
import org.sumit.spring.firstspringapp.beans.User;

@Configuration
@PropertySource("classpath:user-info.properties")
public class AppConfiguration {
	// Scope
	// 1. singleton [default]
	// 2. prototype [each call to getBean will create a new instance of this bean]
	// 3. request [web application]
	// 4. session [web application]
	// 5. application [web application]
    @Bean
    public User objUser(
            @Value("${default.username}") String userName,
            @Value("${default.password}") String password,
            @Value("${default.name}") String name,
            @Value("${default.email}") String email
    ) {
        System.out.println("AppConfiguration objUser method called");
        // Setter Dependency Injection
        return new User(userName, password, name, email);
    }

    @Bean
    public Account facebookAccount() {
        return new FacebookAccount();
    }

    @Bean
    public Account amazonAccount() {
        return new AmazonAccount();
    }
}