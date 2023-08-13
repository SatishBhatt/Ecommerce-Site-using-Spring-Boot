package com.ShopCart.Controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.multipart.MultipartFile;

import com.ShopCart.Repository.ProfileRepository;
import com.ShopCart.Repository.UserRepository;
import com.ShopCart.Service.ProfileService;
import com.ShopCart.Service.UserService;
import com.ShopCart.beans.Profile;
import com.ShopCart.beans.User;



@Controller
public class profileController {
    @Autowired
	private ProfileRepository profile_repo;
    @Autowired
	private ProfileService profile_service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
	
	//handler to handle list profile and return model and view
	
			@RequestMapping("/goProfile")
			public String listprofile(Model model,@RequestParam("id") Long id) {
				
				model.addAttribute("profiles", profile_service.getAllProfile());
				model.addAttribute("profile",new Profile());
				User u=userService.getUserById(id);
			
				
				model.addAttribute("data",u);
			
				
				
				
				
				return "Normal/profile";
			}
			
			
			//handler to add new profile and view it on table
			@PostMapping("/addprofile")
			public String addnewprofile(Model model,@ModelAttribute Profile profile,BindingResult result,HttpSession session, HttpServletRequest request,
                    Authentication authentication, @RequestParam MultipartFile image) {
				
				
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			    User user = userRepository.getUserByUserName(userDetails.getUsername());
			    request.getSession().setAttribute("user", user);

			    Long userId = user.getId();

			    User u = userService.getUserById(userId);

			    profile.setUser(u);

			    profile.setImage(image.getOriginalFilename());

			  
			    Profile uploadProfile = profile_service.saveProfile(profile);
			  
			    
			    if (uploadProfile != null) {
			        try {
			            File saveFile = new ClassPathResource("static/images").getFile();
			            Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + image.getOriginalFilename());
			            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			            profile_service.saveProfile(profile);
			            session.setAttribute("msg", "Profile Complete Successfully");
			           
			        } catch (Exception e) {
			            e.printStackTrace();
			            session.setAttribute("error", "Something Went Wrong");
			        }
			    }
				
				
				return "redirect:viewprofile";
			}
			@RequestMapping("/viewprofile")
			public String viewprofile(Model model) {
				
				model.addAttribute("profiles", profile_service.getAllProfile());
				model.addAttribute("profile",new Profile());
				return "Normal/profile";
			}
			
			//handler to update the profile
			@GetMapping("/updateprofile")
			public String updateprofile(Model model,@ModelAttribute  Profile profile,@RequestParam ("id") Long id,BindingResult result) {
				model.addAttribute("profile",profile_service.getProfileById(id));
				model.addAttribute("profiles" ,profile_service.getAllProfile() );
				
				return "Normal/profile";
			}
			
			//handler to update the profile
				@GetMapping("/deleteprofile")
				public String deleteprofile(Model model,@ModelAttribute  Profile profile,@RequestParam ("id") Long id,BindingResult result) {
					profile_repo.delete(profile);
					model.addAttribute("profiles" ,profile_service.getAllProfile() );
					return "Normal/profile";
				}
}
