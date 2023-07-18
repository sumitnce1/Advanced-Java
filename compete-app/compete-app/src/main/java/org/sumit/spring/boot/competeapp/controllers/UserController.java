package org.sumit.spring.boot.competeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sumit.spring.boot.competeapp.dto.UserDTO;
import org.sumit.spring.boot.competeapp.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/new")
	public boolean newUser(@RequestBody UserDTO dto)
	{
		return userService.registerUser(dto);
	}
	
	@GetMapping("/{uName}")
	public UserDTO getUserDetails(@PathVariable("uName")String userName) 
	{
		return userService.getUserDetails(userName);
	}
	
	@PostMapping("/changePassword")
	public boolean changePassword(@RequestParam("userName")String userName, @RequestParam("password")String password)
	{
		return userService.changePassword(userName, password);
	}
	
}