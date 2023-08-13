package com.ShopCart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ContactController {

	@RequestMapping("/goContact")
	public String contact(Model model) {
		
		
		
		return "Normal/contact";
	}
}
