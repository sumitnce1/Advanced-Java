package org.sumitshopuser.dao;

import org.hibernate.Session;

public interface UserDAO {
    public boolean authenticate(String userName, String password);
    boolean registerUser(String userName, String password, String name, String email, long phone, String city);
    public boolean changePassword(String userName, String password);
    public void setSession(Session session);
}
