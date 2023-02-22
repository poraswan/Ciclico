package com.ciclico.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ciclico.models.Product;
import com.ciclico.models.User;
import com.ciclico.services.AppService;
import com.ciclico.services.SendMailService;


@Controller
public class AdminController {

	@Autowired
	private AppService service;
	
	@Autowired
	private SendMailService sendMailService;
	
	 @GetMapping("/admins")
    public String administradores(Principal principal, Model model) {
		if(principal == null) {
	    		return "index.jsp";
    	}
    	
        //Me regresa el username del usuario que inició sesión
        String username = principal.getName();             
        //Obtenemos el objeto de Usuario
        User currentUser = service.findUserByUsername(username);              
        //Mandamos el usuario a home.jsp
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("roles", currentUser.getRoles());
		 
		List<Product> products = service.findAllProducts();
	    model.addAttribute("products", products);
        return "administradores.jsp";
    }

}
