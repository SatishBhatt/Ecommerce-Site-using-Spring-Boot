package com.ShopCart.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ShopCart.Repository.UserRepository;
import com.ShopCart.beans.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;



@Configuration
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
private UserRepository u_repo;	
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		 UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        User user = u_repo.getUserByUserName(userDetails.getUsername());
	        request.getSession().setAttribute("user", user);

		
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		if(roles.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/");
		}
		else if(roles.contains("ROLE_USER")) {
			
			
			Long userId = user.getId();
			
			
            response.sendRedirect("/user/?id=" + userId); 
		}
	}

}
