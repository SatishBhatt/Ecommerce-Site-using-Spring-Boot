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
import com.ShopCart.Repository.AddCategoryRepositery;
import com.ShopCart.Service.AddCategoryService;
import com.ShopCart.beans.AddCategory;

@Controller
public class AddCategoryController {
	
	
	@Autowired
	private AddCategoryRepositery cat_repo;
	@Autowired
	private AddCategoryService cat_service;
	
	
	

	//handler to handle list category and return model and view
	
		@RequestMapping("/gotoaddCat")
		public String listCategory(Model model) {
			model.addAttribute("categories", cat_service.getAllCategory());
			model.addAttribute("addCategory",new AddCategory());
			return "Admin/categories";
		}
		
		
		//handler to add new category and view it on table
		@PostMapping("/add")
		public String addnewCategory(Model model,@ModelAttribute AddCategory addCategory,BindingResult result) {
			cat_service.saveCategory(addCategory);
			return "redirect:view";
		}
		@RequestMapping("/view")
		public String viewCategory(Model model) {
			
			model.addAttribute("categories", cat_service.getAllCategory());
			
			model.addAttribute("addCategory",new AddCategory());
			return  "Admin/categories" ; 
		}
		
		
//		//handler to update the category
//		@GetMapping("/update")
//		public String updateCategory(Model model,@ModelAttribute AddCategory addCategory,@RequestParam ("id") Long id,BindingResult result) {
//			model.addAttribute("addCategory", cat_service.getCategoryById(id));
//			model.addAttribute("categories" ,cat_service.getAllCategory() );
//			
//			return "Admin/categories";
//		}
		
		//handler to update the category
			@GetMapping("/delete")
			public String deleteCategory(Model model,@ModelAttribute AddCategory addCategory,@RequestParam ("id") Long id,BindingResult result) {
				cat_repo.delete(addCategory);
				model.addAttribute("categories" ,cat_service.getAllCategory() );
				return "Admin/categories";
			}
			
			
			
	
	}


