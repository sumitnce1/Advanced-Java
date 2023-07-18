package org.sumit.pojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class User {
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String city;

    public void save() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaee_0016", "root", "Admin@123");
            String sql = "INSERT INTO users_0016 (username, password, name, email, phone, city) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, userName);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, email);
            statement.setString(5, phone);
            statement.setString(6, city);

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
