package org.sumit.mvcbootapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.sumit.mvcbootapp.beans.User;

@Controller
public class UserController 
{
	@RequestMapping("/login")
	public void prepareLogin(Model data) {
		data.addAttribute("objUser",new User());
	}
		
	@RequestMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute("objUser")User objUser) {
		if(objUser.getUserName().equals("sumit") && objUser.getPassword().equals("kumar"))
			return new ModelAndView("welcome","message","You are Authenticated.");		
		else
			return new ModelAndView("welcome","message","Authentication failure.");
					
	}
}