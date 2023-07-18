package org.sumit.mvcuserapp.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.hibernate.cfg.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.sumit.mvcuserapp.dao.CategoryDAO;
import org.sumit.mvcuserapp.dao.CategoryDAOImpl;
import org.sumit.mvcuserapp.dao.ProductDAO;
import org.sumit.mvcuserapp.dao.ProductDAOImpl;
import org.sumit.mvcuserapp.dao.UserDAO;
import org.sumit.mvcuserapp.dao.UserDAOImpl;

@org.springframework.context.annotation.Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.sumit.mvcuserapp.controllers"})
public class WebAppConfigurator 
{
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;		
	}

	@Bean
	public Configuration configuration() {
		Configuration hibernateConfiguration = new Configuration();
		hibernateConfiguration.configure("hibernate.cfg.xml");
		return hibernateConfiguration;
	}

	@Bean
	public SessionFactory hibernateFactory(Configuration configuration) {
		return configuration.buildSessionFactory();
	}

	@Bean
	public UserDAO userDAO() {
		return new UserDAOImpl();
	}
	@Bean
	public CategoryDAO categoryDAO() {
		return new CategoryDAOImpl();
	}
	
	@Bean
	public ProductDAO productDAO() {
		return new ProductDAOImpl();
	}
}
