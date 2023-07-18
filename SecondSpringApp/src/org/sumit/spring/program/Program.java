package org.sumit.spring.program;

import org.sumit.spring.Users;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml")) {
            Users objUser = (Users) applicationContext.getBean("objUser");
            System.out.println(objUser);
        }
    }
}
