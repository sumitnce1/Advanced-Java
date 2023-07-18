package org.sumit.mvcuserapp.dao;

import org.sumit.mvcuserapp.beans.User;

public interface UserDAO {
	public boolean authenticate(String userName, String password);
	public boolean newUser(User objUser);
}
