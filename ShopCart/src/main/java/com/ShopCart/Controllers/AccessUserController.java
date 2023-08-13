package com.ShopCart.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ShopCart.Repository.UserRepository;
import com.ShopCart.Service.UserService;
import com.ShopCart.beans.User;


@Controller
@RequestMapping("/user")
public class AccessUserController {
	
	@Autowired
	private UserRepository user_repo;
	@Autowired
	private UserService user_service;
	
	@RequestMapping("/")
	public String dashboard(Model model, Principal principal) {
	    String username = principal.getName();
	    model.addAttribute("username", username);
	    
	    model.addAttribute("users", user_service.getAllUser());
	    return "Normal/home";
	}
}
