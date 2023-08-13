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

import com.ShopCart.Repository.VendorRepositery;
import com.ShopCart.Service.VendorService;
import com.ShopCart.beans.AddCategory;
import com.ShopCart.beans.Vendor;

@Controller
public class VendorController {
	
	
	@Autowired
	private VendorService v_service;
	@Autowired
	private VendorRepositery v_repo;

	//handler to handle list category and return model and view
	
		@RequestMapping("/goVendor")
		public String listVendor(Model model) {
			model.addAttribute("vendors", v_service.getAllVendor());
			model.addAttribute("vendor",new Vendor());
			return "Admin/vendor";
		}
		
		
		//handler to add new category and view it on table
		@PostMapping("/addvendor")
		public String addnewVendor(Model model,@ModelAttribute Vendor vendor,BindingResult result) {
			v_service.saveVendor(vendor);
			return "redirect:viewvendor";
		}
		@RequestMapping("/viewvendor")
		public String viewVendor(Model model) {
			
			model.addAttribute("vendors", v_service.getAllVendor());
			model.addAttribute("vendor",new Vendor());
			return "Admin/vendor";
		}
		
//		//handler to update the category
//		@GetMapping("/updateVendor")
//		public String updateVendor(Model model,@ModelAttribute  Vendor vendor,@RequestParam ("id") Long id,BindingResult result) {
//			model.addAttribute("vendor",v_service.getVendorById(id));
//			model.addAttribute("vendors" ,v_service.getAllVendor() );
//			
//			return "Admin/vendor";
//		}
		
		//handler to update the category
			@GetMapping("/deleteVendor")
			public String deleteVendor(Model model,@ModelAttribute  Vendor vendor,@RequestParam ("id") Long id,BindingResult result) {
				v_repo.delete(vendor);
				model.addAttribute("vendors" ,v_service.getAllVendor() );
				return "Admin/vendor";
			}
			
			
			

	
}
