package org.sumit.spring.program;

import org.sumit.spring.Users;
import org.sumit.spring.config.AppConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class)) {
            Users objUser = (Users) applicationContext.getBean("objUser");
            System.out.println(objUser);
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}

