package org.sumituser.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    Connection connection;
    PreparedStatement psAuthenticate;
    PreparedStatement psCreateUser;
    PreparedStatement psUpdatePassword;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
        try {
            psAuthenticate = connection.prepareStatement("SELECT * FROM users_0016 WHERE userName=? AND password=?");
            psCreateUser = connection.prepareStatement("INSERT INTO users_0016 VALUES (?,?,?,?,?,?)");
            psUpdatePassword = connection.prepareStatement("UPDATE users_0016 SET password=? WHERE userName=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String userName, String password) {
        try {
            psAuthenticate.setString(1, userName);
            psAuthenticate.setString(2, password);
            try (ResultSet result = psAuthenticate.executeQuery()) {
                if (result.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registerUser(String userName, String password, String name, String email, String phone, String city) {
        try {
            psCreateUser.setString(1, userName);
            psCreateUser.setString(2, password);
            psCreateUser.setString(3, name);
            psCreateUser.setString(4, email);            
            psCreateUser.setString(5, phone);
            psCreateUser.setString(6, city);
            int rowsUpdated = psCreateUser.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changePassword(String userName, String password) {
        try {
            psUpdatePassword.setString(1, password);
            psUpdatePassword.setString(2, userName);
            int rowsUpdated = psUpdatePassword.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}