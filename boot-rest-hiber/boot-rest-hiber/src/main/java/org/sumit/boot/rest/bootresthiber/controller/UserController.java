package org.sumit.boot.rest.bootresthiber.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sumit.boot.rest.bootresthiber.dto.UserDTO;
import org.sumit.boot.rest.bootresthiber.entity.User;
import org.sumit.boot.rest.bootresthiber.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("/authenticate")
	public boolean authenticate( @RequestParam("userName") String userName, @RequestParam("password") String password) 
	{

		Optional<User> optional = userRepository.findById(userName);

		if (optional.isPresent()) {
			User objUser = optional.get();
			if (objUser.getPassword().equals(password))
				return true;
		}

		return false;
	}

	@PostMapping("/newUser")
	public String newUser(@RequestBody UserDTO userDTO) {

		User objUser = new User();
		BeanUtils.copyProperties(userDTO, objUser);
		userRepository.save(objUser);
		return "created";
	}
}