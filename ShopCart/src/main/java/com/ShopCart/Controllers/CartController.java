package com.ShopCart.Controllers;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShopCart.Repository.AddProductRepositery;
import com.ShopCart.Repository.CartRepository;
import com.ShopCart.Repository.UserRepository;
import com.ShopCart.Repository.WishlistRepository;
import com.ShopCart.Repository.CartRepository;
import com.ShopCart.Service.AddProductService;
import com.ShopCart.Service.CartService;
import com.ShopCart.Service.UserService;
import com.ShopCart.Service.WishlistService;
import com.ShopCart.Service.CartService;
import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.Cart;
import com.ShopCart.beans.User;
import com.ShopCart.beans.Wishlist;


@Controller
public class CartController {

	@Autowired
	private CartService cart_service;
	@Autowired
	private AddProductService prod_service;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private WishlistRepository wish_repo;
	@Autowired
	private AddProductRepositery pro_repo;
	@Autowired
	private WishlistService wish_service;
	
	

	
	
		
	@GetMapping("/cart")
	public String showCart(Model model, Authentication authentication,HttpSession session, HttpServletRequest request
            ) {
	
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    User user = userRepository.getUserByUserName(userDetails.getUsername());
	    request.getSession().setAttribute("user", user);

	    Long userId = user.getId();

	    User u = userService.getUserById(userId);

	    
	 List<Cart> cart =   cart_service.listCartItems(u);
	 
	 model.addAttribute("cartItems" ,cart );
		
	    return "Normal/cart";
	}
	
	
	
	@GetMapping("/cart/add/{id}")
	public String addProductToCart(@PathVariable("id") Long ProductId
			,Authentication authentication,HttpSession session, HttpServletRequest request ) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return  "redirect:/signin";
		}
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    User user = userRepository.getUserByUserName(userDetails.getUsername());
	    request.getSession().setAttribute("user", user);

	    Long userId = user.getId();

	    User u = userService.getUserById(userId);
	    if(u == null) {
	    	return  "redirect:/signin";
	    }
	    
	    Integer quantity = 1;
	    Integer addedQuantity = cart_service.addProduct(ProductId, quantity, u);
	    
	    
	    
	    AddProduct product =pro_repo.findById(ProductId).get();
		
	    wish_service.removeWishProduct(ProductId, user);
		
		
		
	    return "redirect:/cart";
		
	}
	
	@GetMapping("/cart/remove/{id}")
	public String removeProductToCart(@PathVariable("id") Long ProductId
			,Authentication authentication,HttpSession session, HttpServletRequest request ) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return  "redirect:/signin";
		}
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    User user = userRepository.getUserByUserName(userDetails.getUsername());
	    request.getSession().setAttribute("user", user);

	    Long userId = user.getId();

	    User u = userService.getUserById(userId);
	    if(u == null) {
	    	return  "redirect:/signin";
	    }
	    
	    
	    cart_service.removeCartProduct(ProductId, user);
	    
	    return "redirect:/cart";
		
	}

	
	
}
