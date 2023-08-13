package com.ShopCart.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShopCart.Repository.PurchaseRepository;
import com.ShopCart.Repository.VendorRepositery;
import com.ShopCart.Service.PaymentModeService;
import com.ShopCart.Service.ProductDetailService;
import com.ShopCart.Service.PurchaseService;
import com.ShopCart.Service.VendorService;
import com.ShopCart.beans.Brand;
import com.ShopCart.beans.Color;
import com.ShopCart.beans.PaymentMode;
import com.ShopCart.beans.ProductDetail;
import com.ShopCart.beans.Purchase;
import com.ShopCart.beans.Vendor;

@Controller
public class PurchaseController {

	@Autowired
	private PurchaseService purchase_service;
	@Autowired
	private PurchaseRepository purchase_repo;
	@Autowired
	private VendorService ven_service;
	
	@Autowired
	private PaymentModeService pm_service;
	
	@Autowired
	private ProductDetailService pd_service;
	
	
	

	//handler to handle list purchase and return model and view
	
		@RequestMapping("/goPurchase")
		public String listpurchase(Model model) {
			model.addAttribute("purchases", purchase_service.getAllPurchase());
			
			model.addAttribute("purchase",new Purchase());


			List<Vendor> b = ven_service.getAllVendor();
			model.addAttribute("vendors", b);
			
			List<PaymentMode> pm = pm_service.getAllPaymentMode();
			model.addAttribute("paymentModes", pm);
			
			return "Admin/purchase";
		}
		
		
		//handler to add new purchase and view it on table
		@PostMapping("/addpurchase")
		public String addnewpurchase(Model model,@ModelAttribute Purchase purchase,BindingResult result) {
			purchase_service.savePurchase(purchase);
			return "redirect:viewpurchase";
		}
		@RequestMapping("/viewpurchase")
		public String viewpurchase(Model model) {


			List<Vendor> b = ven_service.getAllVendor();
			model.addAttribute("vendors", b);
			
			List<PaymentMode> pm = pm_service.getAllPaymentMode();
			model.addAttribute("paymentModes", pm);
			
			model.addAttribute("purchases", purchase_service.getAllPurchase());
			model.addAttribute("purchase",new Purchase());
			return "Admin/purchase";
		}
		
//		//handler to update the purchase
//		@GetMapping("/updatepurchase")
//		public String updatepurchase(Model model,@ModelAttribute  Purchase purchase,@RequestParam ("id") Long id,BindingResult result) {
//			model.addAttribute("purchase",purchase_service.getPurchaseById(id));
//			model.addAttribute("purchases" ,purchase_service.getAllPurchase() );
//			
//		List<Vendor> b = ven_service.getAllVendor();
//		model.addAttribute("vendors", b);
//		
//
//		List<PaymentMode> pm = pm_service.getAllPaymentMode();
//		model.addAttribute("paymentModes", pm);
//		
//			return "Admin/purchase";
//		}
		
		//handler to update the purchase
			@GetMapping("/deletepurchase")
			public String deletepurchase(Model model,@ModelAttribute  Purchase purchase,@RequestParam ("id") Long id,BindingResult result) {
				purchase_repo.delete(purchase);
				model.addAttribute("purchases" ,purchase_service.getAllPurchase() );
				return "Admin/purchase";
			}
			
	
}
