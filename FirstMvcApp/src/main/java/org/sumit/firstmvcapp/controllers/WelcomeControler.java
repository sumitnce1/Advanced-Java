package org.sumit.firstmvcapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeControler 
{
	@RequestMapping("/enter")
	public ModelAndView welcome() {
		return new ModelAndView("welcome", "message", "Welcome to the First Web MVC Application");
		// 
	}
}
