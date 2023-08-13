package com.ShopCart.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.ShopCart.Repository.HomeRepositery;
import com.ShopCart.Service.AddCategoryService;
import com.ShopCart.Service.AddProductService;
import com.ShopCart.Service.HomeService;

import com.ShopCart.beans.Home;


@Controller
public class homeController {
	
	@Autowired
	private AddProductService pro_service;
	@Autowired
	private AddCategoryService cat_service;
	
	
	
	//handler to handle list category and return model and view
	
		@RequestMapping("/goHome")
		public String home(Model model) {
	
			return "Normal/home";
		}
		
		@RequestMapping("/goDashboard")
		public String dashboard(Model model) {
	
			return "Admin/adminhome";
		}
		
		
		
@GetMapping("/shop")
public String shop(Model model) {
	model.addAttribute("categories", cat_service.getAllCategory());
	model.addAttribute("products", pro_service.getAllProduct());
	
	return "Normal/shop";
}
		
	
@GetMapping("/shop/category/{id}")
public String shopByCategory(Model model,@PathVariable Long id) {
	model.addAttribute("categories", cat_service.getAllCategory());
	model.addAttribute("products", pro_service.getAllProductByCategoryId(id));

	return "Normal/shop";
}
			
@GetMapping("/shop/viewproduct/{id}")
public String viewProdu(Model model,@PathVariable Long id) {
	model.addAttribute("product", pro_service.getProductById(id));

	
	return "Normal/ViewProduct";
}		
			
			
			
			
			
			
			
			
			
			
			
}
