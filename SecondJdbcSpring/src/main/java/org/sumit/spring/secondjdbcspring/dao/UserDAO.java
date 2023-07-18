package org.sumit.spring.secondjdbcspring.dao;

import java.util.List;

import org.sumit.spring.secondjdbcspring.entity.User;

public interface UserDAO {
    boolean addUser(String userName, String password, String name, String email, String phone, String city);
    List<User> allUsers();
    User getUserByUserName(String userName);
    boolean changePassword(String userName, String password);
    boolean deleteUser(String userName);
    boolean updateUser(String userName, String password, String name, String email, double phone, String city);
}
