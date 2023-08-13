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


import com.ShopCart.Repository.ColorRepository;

import com.ShopCart.Service.ColorService;

import com.ShopCart.beans.Color;

@Controller
public class ColorController {


	@Autowired
	private ColorService color_service;
	@Autowired
	private ColorRepository color_repo;

	//handler to handle list color and return model and view
	
		@RequestMapping("/goColor")
		public String listcolor(Model model) {
			model.addAttribute("colors", color_service.getAllColor());
			model.addAttribute("color",new Color());
			return "Admin/color";
		}
		
		
		//handler to add new color and view it on table
		@PostMapping("/addcolor")
		public String addnewcolor(Model model,@ModelAttribute Color color,BindingResult result) {
			color_service.saveColor(color);
			return "redirect:viewcolor";
		}
		@RequestMapping("/viewcolor")
		public String viewcolor(Model model) {
			
			model.addAttribute("colors", color_service.getAllColor());
			model.addAttribute("color",new Color());
			return "Admin/color";
		}
		
//		//handler to update the color
//		@GetMapping("/updatecolor")
//		public String updatecolor(Model model,@ModelAttribute  Brand color,@RequestParam ("id") Long id,BindingResult result) {
//			model.addAttribute("color",color_service.getColorById(id));
//			model.addAttribute("colors" ,color_service.getAllColor() );
//			
//			return "Admin/color";
//		}
		
		//handler to update the color
			@GetMapping("/deletecolor")
			public String deletecolor(Model model,@ModelAttribute  Color color,@RequestParam ("id") Long id,BindingResult result) {
				color_repo.delete(color);
				model.addAttribute("colors" ,color_service.getAllColor() );
				return "Admin/color";
			}
			
}
