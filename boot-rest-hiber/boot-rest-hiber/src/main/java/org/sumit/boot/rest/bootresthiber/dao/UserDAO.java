package org.sumit.boot.rest.bootresthiber.dao;

import org.sumit.boot.rest.bootresthiber.dto.UserDTO;

public interface UserDAO {
	public boolean authenticateUser(String userName, String Password);
	public void createUser(UserDTO objUser);
}
