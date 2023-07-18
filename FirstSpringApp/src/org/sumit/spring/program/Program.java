package org.sumit.spring.program;

import org.sumit.spring.Users;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("first-app-beans.xml")) {
            Users objUsers = (Users) applicationContext.getBean("objUser");
            objUsers.setUserName("rest");
            objUsers.setPassword("test");
            System.out.println(objUsers);
        } catch (BeansException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
