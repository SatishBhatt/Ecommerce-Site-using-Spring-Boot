package com.ShopCart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
public class AboutController {

	
	
	@RequestMapping("/goAbout")
	public String about(Model model) {
		
		
		
		return "Normal/about";
	}
}
