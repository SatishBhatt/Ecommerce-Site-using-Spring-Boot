package com.ShopCart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AccessAdminController {
	
	@RequestMapping("/")
	public String dashboard()
	{
		return "Admin/adminhome";
	}
}