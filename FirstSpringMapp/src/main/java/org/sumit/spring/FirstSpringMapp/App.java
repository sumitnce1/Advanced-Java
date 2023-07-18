package org.sumit.spring.FirstSpringMapp;

import org.sumit.spring.firstspringapp.beans.Account;
import org.sumit.spring.firstspringapp.config.AppConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
    	System.out.println("Before Starting the container");
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class)) 
        {
        	System.out.println("Container Started.");
            Account objFacebook = (Account) applicationContext.getBean("facebookAccount");
            System.out.println(objFacebook);
            System.out.println(applicationContext.getBean("facebookAccount"));

            objFacebook.createAccount();
            System.out.println("______________________________________");

            Account amazonAccount = (Account) applicationContext.getBean("amazonAccount");
            amazonAccount.createAccount();
            System.out.println("______________________________________");

            System.out.println("Changing username for amazon account");
            amazonAccount.getUser().setUserName("admin");

            objFacebook.showDetails();
            amazonAccount.showDetails();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
