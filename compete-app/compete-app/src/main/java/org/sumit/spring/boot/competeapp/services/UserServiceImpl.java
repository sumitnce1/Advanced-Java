package org.sumit.spring.boot.competeapp.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sumit.spring.boot.competeapp.dto.UserDTO;
import org.sumit.spring.boot.competeapp.entity.User;
import org.sumit.spring.boot.competeapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	UserRepository userRepository;

	@Autowired
	MailService mailService;

	@Override
	public boolean registerUser(UserDTO userDTO) {
		User userEntity = new User();
		BeanUtils.copyProperties(userDTO, userEntity);
		userRepository.save(userEntity);

		String contents = "<html><body><h3 style='color: green;'>Congratulation! Your account has been Created</h3><br/><br/>";
		contents += "Account details<br/><br/>";
		contents += "User name  : " + userDTO.getUserName();
		contents += "Password   : " + userDTO.getPassword();
		contents += "<br/><br/>Name       : " + userDTO.getName();
		contents += "<br/><br/>Email      : " + userDTO.getEmail();
		contents += "<br/><br/>Phone      : " + userDTO.getPhone();
		contents += "<br/><br/>City       : " + userDTO.getCity();
		mailService.sendMail("cdac-patna@frankelsinfotech.com", userDTO.getEmail(), "Your Account Created", contents);
		return true;
	}

	@Override
	public boolean changePassword(String userName, String password) {
		return userRepository.updatePassword(userName, password);
	}

	@Override
	public UserDTO getUserDetails(String userName) {
		Optional<User> optUser = userRepository.findById(userName);
		if (optUser.isPresent()) {
			User user = optUser.get();
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(user, dto);
			return dto;
		}
		return null;
	}
}