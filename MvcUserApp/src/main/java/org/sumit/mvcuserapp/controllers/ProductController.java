package org.sumit.mvcuserapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sumit.mvcuserapp.beans.Product;
import org.sumit.mvcuserapp.dao.ProductDAO;

@Controller
public class ProductController {
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping("/newProduct")
    public void prepareNew(Model data) {
        data.addAttribute("objProduct", new Product());
    }
    
    @RequestMapping("/registerProduct")
    public String registerNewProduct(@ModelAttribute("objProduct") Product objProduct) {
        boolean status = productDAO.newProduct(objProduct);
        if (status) {
            return "catregistered";
        } else {
            return "catregistrationFailed";
        }
    }
}
