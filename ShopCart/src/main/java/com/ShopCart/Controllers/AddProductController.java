package com.ShopCart.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ShopCart.Repository.AddProductRepositery;
import com.ShopCart.Service.AddCategoryService;
import com.ShopCart.Service.AddProductService;
import com.ShopCart.Service.BrandService;
import com.ShopCart.Service.ColorService;
import com.ShopCart.Service.VendorService;
import com.ShopCart.beans.AddCategory;
import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.Brand;
import com.ShopCart.beans.Color;
import com.ShopCart.beans.Profile;
import com.ShopCart.beans.Vendor;

@Controller
public class AddProductController {

	@Autowired
	private AddProductRepositery pro_repo;
	@Autowired
	private AddProductService pro_service;
	@Autowired
	private AddCategoryService cat_service;
	@Autowired
	private VendorService v_service;
	@Autowired
	private ColorService color_service;
	@Autowired
	private BrandService b_service;
	

	// handler to handle list product and return model and view

	@RequestMapping("/goProducts")
	public String listProduct(Model model) {
		System.out.println("get back-----------------");
		model.addAttribute("products", pro_service.getAllProduct());
		model.addAttribute("product", new AddProduct());
		
		List<AddCategory> cat = cat_service.getAllCategory();
		model.addAttribute("categories", cat);
		
		
		
		List<Color> col = color_service.getAllColor();
		model.addAttribute("colors", col);
		
		List<Brand> b = b_service.getAllBrand();
		model.addAttribute("brands", b);
		
		return "Admin/products";
	}

	// handler to add new category and view it on table
	@PostMapping("/addPro")
	public String addnewProduct(Model model,@ModelAttribute AddProduct product, BindingResult result,
			@RequestParam MultipartFile images){
		List<AddCategory> cat = cat_service.getAllCategory();
		model.addAttribute("categories", cat);
		
		
		
		List<Color> col = color_service.getAllColor();
		model.addAttribute("colors", col);
		
		List<Brand> b = b_service.getAllBrand();
		model.addAttribute("brands", b);
		
		
		
		 product.setImages(images.getOriginalFilename());

		  product.setEnable(true);
		    AddProduct uploadProfile = pro_service.saveProduct(product);
		  
		    
		    if (uploadProfile != null) {
		        try {
		            File saveFile = new ClassPathResource("static/images").getFile();
		            Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + images.getOriginalFilename());
		            Files.copy(images.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		           // session.setAttribute("msg", "Product Added Successfully");
		           
		        } catch (Exception e) {
		            e.printStackTrace();
		           // session.setAttribute("error", "Something Went Wrong");
		        }
		    }
			
		
		return "redirect:display";
	}
	

	
//	 @GetMapping("/images")
//	    public String showImages(Model model) {
//	        Image firstImage = pro_repo.findFirstByOrderByIdAsc();
//	        model.addAttribute("firstImage", firstImage);
//	        return "image-table";
//	    }
	
	
	
	
	
	
	
	
	

	@RequestMapping("/display")
	public String viewProduct(Model model) {

		model.addAttribute("products", pro_service.getAllProduct());

		model.addAttribute("product", new AddProduct());
		
		
		
		 List<AddProduct> images = pro_repo.findAll();
	        model.addAttribute("images", images);
		
	        List<AddCategory> cat = cat_service.getAllCategory();
			model.addAttribute("categories", cat);
			
			
			
			List<Color> col = color_service.getAllColor();
			model.addAttribute("colors", col);
			
			List<Brand> b = b_service.getAllBrand();
			model.addAttribute("brands", b);
		
		return "Admin/products";
	}

			//handler to update the product
	@GetMapping("/updatePro")
	public String updateProduct(Model model, @ModelAttribute AddProduct product, @RequestParam("id") Long id,
			BindingResult result) {
		model.addAttribute("product", pro_service.getProductById(id));
		model.addAttribute("products", pro_service.getAllProduct());


		List<AddCategory> cat = cat_service.getAllCategory();
		model.addAttribute("categories", cat);
		
		
		List<Color> col = color_service.getAllColor();
		model.addAttribute("colors", col);
		
		List<Brand> b = b_service.getAllBrand();
		model.addAttribute("brands", b);
	
		
		
		
		return "Admin/products";
	}
	

	// handler to delete the product
	@GetMapping("/deletePro")
	public String deleteProduct(Model model, @ModelAttribute AddProduct product, @RequestParam("id") Long id,
			BindingResult result) {
		pro_repo.delete(product);
		
		
		return  "redirect:goProducts";
	}
	
	
	// handler to inactive the product
		@GetMapping("/InActivePro")
		public String InActiveProduct(Model model, @ModelAttribute AddProduct product, @RequestParam("id") Long id,
				BindingResult result) {
			
			pro_repo.updateInActive(id);
			
			return  "redirect:goProducts";
		}
	
	
		// handler to active the product
				@GetMapping("/ActivePro")
				public String ActiveProduct(Model model, @ModelAttribute AddProduct product, @RequestParam("id") Long id,
						BindingResult result) {
					
					pro_repo.updateActive(id);
					
					return  "redirect:goProducts";
				}
	
	
	
	
	
	
	
	//handler to show view details  
	@GetMapping("/viewPro")
	public String viewProductDetail(Model model, @ModelAttribute AddProduct product, @RequestParam("id") Long id,
			BindingResult result) {
		
		model.addAttribute("product", pro_service.getProductById(id));
		
		model.addAttribute("products", pro_service.getAllProduct());
		
		return "Admin/viewProduct";
	}

}
