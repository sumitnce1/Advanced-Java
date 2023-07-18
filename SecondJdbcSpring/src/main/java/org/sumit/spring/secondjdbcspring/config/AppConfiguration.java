package org.sumit.spring.secondjdbcspring.config;

import javax.sql.DataSource;

import org.sumit.spring.secondjdbcspring.dao.CategoryDAO;
import org.sumit.spring.secondjdbcspring.dao.CategoryDAOImpl;
import org.sumit.spring.secondjdbcspring.dao.ProductDAO;
import org.sumit.spring.secondjdbcspring.dao.ProductDAOImpl;
import org.sumit.spring.secondjdbcspring.dao.UserDAO;
import org.sumit.spring.secondjdbcspring.dao.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource(
            @Value("${db.url}") String url,
            @Value("${db.username}") String userName,
            @Value("${db.password}") String password,
            @Value("${db.driver_class}") String driver) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
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