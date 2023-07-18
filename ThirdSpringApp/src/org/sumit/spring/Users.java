package org.sumit.spring;

import org.springframework.stereotype.Component;

@Component("objUser")
public class Users {
    String userName = "default";
    String password = "default#1234";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users [userName=" + userName + ", password=" + password + "]";
    }
}
