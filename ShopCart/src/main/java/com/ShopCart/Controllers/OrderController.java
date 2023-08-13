package com.ShopCart.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ShopCart.Repository.AddProductRepositery;
import com.ShopCart.Repository.UserRepository;
import com.ShopCart.Service.CartService;
import com.ShopCart.Service.OrderService;
import com.ShopCart.Service.UserService;
import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.Cart;
import com.ShopCart.beans.Order;
import com.ShopCart.beans.User;

@Controller
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AddProductRepositery pro_repo;
	@Autowired
	private OrderService order_service;
	@Autowired
	private CartService cart_service;
	
	
	@GetMapping("/order")
	public String order(Model model,Authentication authentication,HttpSession session, HttpServletRequest request) {
		

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    User user = userRepository.getUserByUserName(userDetails.getUsername());
	    request.getSession().setAttribute("user", user);

	    Long userId = user.getId();

	    User u = userService.getUserById(userId);

	    
	 List<Order> order =   order_service.listOrderItems(u);
	 
	 model.addAttribute("orderItems" ,order );
		
		
		return "Normal/order";
	}
	
	
	@GetMapping("/order/{id}/{qty}")
	public String buyNow(Model model,@PathVariable("id") Long ProductId,@PathVariable("qty") Integer quantity,
			Authentication authentication,HttpSession session, HttpServletRequest request) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    User user = userRepository.getUserByUserName(userDetails.getUsername());
	    request.getSession().setAttribute("user", user);

	    Long userId = user.getId();

	    User u = userService.getUserById(userId);
	    AddProduct product =pro_repo.findById(ProductId).get();
	    
		order_service.addOrder(ProductId, quantity, u);
		cart_service.removeCartProduct(ProductId, u);
		
		
		
		
		return "Normal/payPage";
	}
	
	
	@GetMapping("/proceed")
	public String proceedToPay(Model model) {
		
		
		return "redirect:/order";
	}

}
