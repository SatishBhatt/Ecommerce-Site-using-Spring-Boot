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

import com.ShopCart.Repository.PaymentModeRepository;

import com.ShopCart.Service.PaymentModeService;

import com.ShopCart.beans.PaymentMode;

@Controller
public class PaymentModeController {
	

	@Autowired
	private PaymentModeService pay_service;
	@Autowired
	private PaymentModeRepository pay_repo;

	//handler to handle list paymentMode and return model and view
	
		@RequestMapping("/goPaymentMode")
		public String listpaymentMode(Model model) {
			model.addAttribute("paymentModes", pay_service.getAllPaymentMode());
			model.addAttribute("paymentMode",new PaymentMode());
			return "Admin/paymentMode";
		}
		
		
		//handler to add new paymentMode and view it on table
		@PostMapping("/addpaymentMode")
		public String addnewpaymentMode(Model model,@ModelAttribute PaymentMode paymentMode,BindingResult result) {
			pay_service.savePaymentMode(paymentMode);
			return "redirect:viewpaymentMode";
		}
		@RequestMapping("/viewpaymentMode")
		public String viewpaymentMode(Model model) {
			
			model.addAttribute("paymentModes", pay_service.getAllPaymentMode());
			model.addAttribute("paymentMode",new PaymentMode());
			return "Admin/paymentMode";
		}
		
//		//handler to update the paymentMode
//		@GetMapping("/updatepaymentMode")
//		public String updatepaymentMode(Model model,@ModelAttribute  Brand paymentMode,@RequestParam ("id") Long id,BindingResult result) {
//			model.addAttribute("paymentMode",pay_service.getPaymentModeById(id));
//			model.addAttribute("paymentModes" ,pay_service.getAllPaymentMode() );
//			
//			return "Admin/paymentMode";
//		}
		
		//handler to update the paymentMode
			@GetMapping("/deletepaymentMode")
			public String deletepaymentMode(Model model,@ModelAttribute  PaymentMode paymentMode,@RequestParam ("id") Long id,BindingResult result) {
				pay_repo.delete(paymentMode);
				model.addAttribute("paymentModes" ,pay_service.getAllPaymentMode() );
				return "Admin/paymentMode";
			}

}
