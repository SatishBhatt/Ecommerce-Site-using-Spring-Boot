package com.ShopCart.Controllers;

import java.util.Random;

import javax.servlet.http.HttpSession;

import com.ShopCart.beans.Message;
import com.ShopCart.beans.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShopCart.Repository.UserRepository;
import com.ShopCart.Service.UserService;



@Controller

public class UserController {
	Random random = new Random(10000);
	
	@Autowired
	private UserService service;	
	@Autowired
	private UserRepository repo;
	@Autowired
	private BCryptPasswordEncoder  passwordEncoder;
	
	
	
	///controller for custom login
	
	@GetMapping("/signin")
	public String CustomLogin(Model model) {
		model.addAttribute("user",new User());
		return "login";
	}
	// controller for custom signup
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user",new User());
		return "signup";
	}
	
	// controller for myprofile
		@RequestMapping("/profile")
		public String myProfile(Model model) {
			//model.addAttribute("user",new User());
			return "Normal/myProfile";
		}
	
		// controller for backTo home
				@RequestMapping("/home")
				public String back(Model model) {
					//model.addAttribute("user",new User());
					return "Normal/home";
				}
	
	//controller for update userinfo
				@PostMapping("/user/update")
				public String updateUserProfile(@ModelAttribute("user") User updatedUser, HttpSession session) {
				    // Update the user information in the database or perform any necessary actions
				    // ...

				    // Update the user object in the session
				    session.setAttribute("user", updatedUser);

				    return "Normal/myProfile";
				}

	

	
	
//	
//	@RequestMapping(value="/forget")
//	public String openEmailForm() {
//		
//		
//		return "common/forget_email_form";
//	}
	
	
//	//controller to send-otp
	
//	@PostMapping("/send-otp")
//	public String sendOTP(@RequestParam ("email")   String email, HttpSession session) {
//		
//		Integer otp=random.nextInt(999999);
//		
//	
//	    String message="OTP : " + otp;
//	    
//	    
//	    
//      String subject="OTP for verification";
//      String to = email;
//      
//      
//      
//      Boolean flag = this.service.sendEmail(message,subject,to);
//		
//		if(flag) 
//		{
//			session.setAttribute("myotp", otp);
//			session.setAttribute("email", email);
//			return "common/verify_otp";
//		}
//		else 
//		{
//			
//			session.setAttribute("message", "check your email id !!!");
//			return "common/forget_email_form";
//		}
//		
//		
//		
//	}
	
//	//verify otp controller
//	@PostMapping("/verify-otp")
//	public String verifyotp(@RequestParam ("otp") int otp,HttpSession session) {
//	
//		Integer myOtp =(Integer) session.getAttribute("myotp");
//		String email=(String) session.getAttribute("email");
//		
//		if(myOtp == otp) {
//			//pass change form
//			
//			User a = this.repo.getEmailByAdminEmail(email);
//			
//			if(a==null) {
//				//send error msg
//				session.setAttribute("message", "Email does not Exsist");
//				return "common/forget_email_form";
//			}
//			else {
//				//send change password form
//				
//			}
//			
//			
//			return "common/password_change_form";
//		}
//		else
//		{
//			session.setAttribute("message", "You have Entered Wrong OTP");
//			return "common/verify_otp";
//		}
//	}
	
	
	
	
//	//change password
//@PostMapping("/change-password")
//	public String changePassword(@RequestParam("newpassword") String newpassword, HttpSession session) {
//	String email=(String) session.getAttribute("email");
//	User a =this.repo.getEmailByAdminEmail(email);
//		a.setPassword(newpassword);
//		this.repo.save(a);
//		
//		return "redirect:/admin";
//	}
	
	
	
	
	
	
	
	
	// controller for registering users
	
	@RequestMapping(value="/do-register" ,method = RequestMethod.POST)
	public String registerUser( @ModelAttribute("user") User user, 
			@RequestParam(value="agreement" ,defaultValue = "false") Boolean agreement,
			Model model,BindingResult result1,HttpSession session ) {
		
		try {
		
		if(!agreement) {
			System.out.println("You have not agreed to the terms and conditions");
			throw new Exception("You have not agreed to the terms and conditions");	
		}
		
		if(result1.hasErrors()) {
//			System.out.println("ERROR" + result1.toString());
			model.addAttribute("user", user);          
			return "signup";
		}
		
		user.setRole("ROLE_USER");
		user.setEnable(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		
		repo.save(user);
		model.addAttribute("user", new User());
	
		session.setAttribute("message",new Message("Successfully Registered 👍","success"));
		return "signup";
		}
		
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message",new Message("oops!! Something went wrong..","danger"));
			return "signup";
		}
	}
	

	
	
	
}
