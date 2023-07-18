package org.sumit.spring.boot.competeapp.services;

import org.sumit.spring.boot.competeapp.dto.UserDTO;

public interface UserService {
	boolean registerUser(UserDTO userDTO);
	boolean changePassword(String userName, String password);
	UserDTO getUserDetails(String userName);
}
