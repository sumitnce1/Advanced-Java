package org.sumit.mvcuserapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sumit.mvcuserapp.beans.Category;
import org.sumit.mvcuserapp.dao.CategoryDAO;

@Controller
public class CategoryController {
	
	@Autowired
    CategoryDAO categoryDAO;
	
	@RequestMapping("/newCategory")
    public void prepareNewCategory(Model data) {
        data.addAttribute("objCategory", new Category());
    }
    
    @RequestMapping("/registerCategory")
    public String registerNewCategory(@ModelAttribute("objCategory") Category objCategory) {
        boolean status = categoryDAO.newCategory(objCategory);
        if (status) {
            return "catregistered";
        } else {
            return "catregistrationFailed";
        }
    }

}
