package org.sumit.mvcuserapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.sumit.mvcuserapp.beans.User;
import org.sumit.mvcuserapp.dao.UserDAO;

@Controller
public class UserController {
    
    @Autowired
    UserDAO userDAO;
    
    @RequestMapping("/login")
    public void prepareLogin(Model data) {
        data.addAttribute("objUser", new User());
    }
    
    @RequestMapping("/authenticate")
    public ModelAndView authenticateUser(@ModelAttribute("objUser") User objUser) {
        boolean status = userDAO.authenticate(objUser.getUserName(), objUser.getPassword());
        if (status) {
            return new ModelAndView("welcome", "message", "Welcome " + objUser.getUserName() + " to the online shopping site");
        } else {
            return new ModelAndView("failure", "message", "Authentication Failure");
        }
    }
    
    @RequestMapping("/newUser")
    public void prepareNewUser(Model data) {
        data.addAttribute("objUser", new User());
    }
    
    @RequestMapping("/registerUser")
    public String registerNewUser(@ModelAttribute("objUser") User objUser) {
        boolean status = userDAO.newUser(objUser);
        if (status) {
            return "registered";
        } else {
            return "registrationFailed";
        }
    }
}