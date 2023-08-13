package com.ShopCart.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShopCart.Repository.UserRepository;
import com.ShopCart.Repository.WishlistRepository;
import com.ShopCart.Service.CartService;
import com.ShopCart.Service.UserService;
import com.ShopCart.Service.WishlistService;
import com.ShopCart.beans.Cart;
import com.ShopCart.beans.User;
import com.ShopCart.beans.Wishlist;


@Controller
public class WishlistController {
	@Autowired
	private WishlistService wish_service;
	@Autowired
	private WishlistRepository wish_repo;
	@Autowired
	private UserRepository user_repo;
	@Autowired
	private UserService user_service;
	
	
	
	@GetMapping("/wish")
	public String showWishlist(Model model, Authentication authentication,HttpSession session, HttpServletRequest request
            ) {
	
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    User user =user_repo.getUserByUserName(userDetails.getUsername());
	    request.getSession().setAttribute("user", user);

	    Long userId = user.getId();

	    User u = user_service.getUserById(userId);

	    
	 List<Wishlist> wish =   wish_service.listWishItems(u);
	 
	 model.addAttribute("wishItems" ,wish );
		
	    return "Normal/wishlist";
	}
	
	
	
	
	@GetMapping("/wish/add/{id}")
	public String addProductToWishlist(@PathVariable("id") Long ProductId
			,Authentication authentication,HttpSession session, HttpServletRequest request ) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "you must login to add the product to cart";
		}
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    User user = user_repo.getUserByUserName(userDetails.getUsername());
	    request.getSession().setAttribute("user", user);

	    Long userId = user.getId();

	    User u = user_service.getUserById(userId);
	    if(u == null) {
	    	return "you must login to add the product to cart";
	    }
	    
	   wish_service.addProduct(ProductId, user);
	   
	    
	    return "redirect:/wish";
		
	}
	
	
	
	
	
	@GetMapping("/wish/remove/{id}")
	public String removeProductfromWish(@PathVariable("id") Long ProductId
			,Authentication authentication,HttpSession session, HttpServletRequest request ) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return  "redirect:/signin";
		}
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    User user = user_repo.getUserByUserName(userDetails.getUsername());
	    request.getSession().setAttribute("user", user);

	    Long userId = user.getId();

	    User u = user_service.getUserById(userId);
	    if(u == null) {
	    	return  "redirect:/signin";
	    }
	    
	    
	    wish_service.removeWishProduct(ProductId, user);
	    
	    return "redirect:/wish";
		
	}
	
}
