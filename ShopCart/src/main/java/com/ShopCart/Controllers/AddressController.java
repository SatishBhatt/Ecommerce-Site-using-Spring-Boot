package com.ShopCart.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShopCart.Repository.AddressRepositery;
import com.ShopCart.Repository.UserRepository;
import com.ShopCart.Service.AddressService;
import com.ShopCart.Service.UserService;
import com.ShopCart.beans.Address;
import com.ShopCart.beans.User;

@Controller
public class AddressController {
	@Autowired
	private AddressRepositery add_repo;
	@Autowired
	private AddressService add_service;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	
	//handler to handle list address and return model and view
	
			@RequestMapping("/goAddress")
			public String listaddress(Model model,Authentication authentication,HttpSession session, HttpServletRequest request) {
				

				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			    User user = userRepository.getUserByUserName(userDetails.getUsername());
			    request.getSession().setAttribute("user", user);

			    Long userId = user.getId();

			    User u = userService.getUserById(userId);
			    
			    model.addAttribute("user", u);
				
				model.addAttribute("addresses", add_service.getAllAddress());
				model.addAttribute("address",new Address());
				return "Normal/address";
			}
			
			
			//handler to add new address and view it on table
			@PostMapping("/addaddress")
			public String addnewaddress(Model model,@ModelAttribute Address address,HttpSession session, HttpServletRequest request,
                    Authentication authentication) {
				
				
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			    User user = userRepository.getUserByUserName(userDetails.getUsername());
			    request.getSession().setAttribute("user", user);

			    Long userId = user.getId();

			    User u = userService.getUserById(userId);

				
				
				address.setUser(u);
				
				
				
				
				
				add_service.saveAddress(address);
				
				return "redirect:viewaddress";
			}
			@RequestMapping("/viewaddress")
			public String viewaddress(Model model,Authentication authentication,HttpSession session, HttpServletRequest request) {
				

				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			    User user = userRepository.getUserByUserName(userDetails.getUsername());
			    request.getSession().setAttribute("user", user);

			   Long userId = user.getId();

			    User u = userService.getUserById(userId);
			    
			    model.addAttribute("user", u);
				model.addAttribute("addresses", add_service.getAllAddress());
				model.addAttribute("address",new Address());
				return "Normal/address";
			}
			
			//handler to update the address
			@GetMapping("/updateaddress")
			public String updateaddress(Model model,@ModelAttribute  Address address,@RequestParam ("id") Long id,BindingResult result) {
				model.addAttribute("address",add_service.getAddressById(id));
				model.addAttribute("addresses" ,add_service.getAllAddress() );
				
				return "Normal/address";
			}
			
			//handler to update the address
				@GetMapping("/deleteaddress")
				public String deleteaddress(Model model,@ModelAttribute  Address address,@RequestParam ("id") Long id,BindingResult result) {
					add_repo.delete(address);
					model.addAttribute("addresses" ,add_service.getAllAddress() );
					return "Normal/address";
				}
				
}
