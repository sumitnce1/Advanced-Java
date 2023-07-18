package org.sumituser.dao;

public interface UserDAO {
    public boolean authenticate(String userName, String password);
    public boolean registerUser(String userName, String password, String name, String email, String phone, String city);
    public boolean changePassword(String userName, String password);
}
