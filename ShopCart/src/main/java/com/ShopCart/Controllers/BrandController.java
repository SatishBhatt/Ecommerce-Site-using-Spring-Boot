package com.ShopCart.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShopCart.Repository.BrandRepository;

import com.ShopCart.Service.BrandService;

import com.ShopCart.beans.Brand;


@Controller
public class BrandController {


	@Autowired
	private BrandService b_service;
	@Autowired
	private BrandRepository b_repo;

	//handler to handle list brand and return model and view
	
		@RequestMapping("/goBrand")
		public String listbrand(Model model) {
			model.addAttribute("brands", b_service.getAllBrand());
			model.addAttribute("brand",new Brand());
			return "Admin/brand";
		}
		
		
		//handler to add new brand and view it on table
		@PostMapping("/addbrand")
		public String addnewbrand(Model model,@ModelAttribute Brand brand,BindingResult result) {
			b_service.saveBrand(brand);
			return "redirect:viewbrand";
		}
		@RequestMapping("/viewbrand")
		public String viewbrand(Model model) {
			
			model.addAttribute("brands", b_service.getAllBrand());
			model.addAttribute("brand",new Brand());
			return "Admin/brand";
		}
		
//		//handler to update the category
//		@GetMapping("/updatebrand")
//		public String updatebrand(Model model,@ModelAttribute  Brand brand,@RequestParam ("id") Long id,BindingResult result) {
//			model.addAttribute("brand",b_service.getBrandById(id));
//			model.addAttribute("brands" ,b_service.getAllbrand() );
//			
//			return "Admin/brand";
//		}
		
		//handler to update the category
			@GetMapping("/deletebrand")
			public String deletebrand(Model model,@ModelAttribute  Brand brand,@RequestParam ("id") Long id,BindingResult result) {
				b_repo.delete(brand);
				model.addAttribute("brands" ,b_service.getAllBrand() );
				return "Admin/brand";
			}
			
}
