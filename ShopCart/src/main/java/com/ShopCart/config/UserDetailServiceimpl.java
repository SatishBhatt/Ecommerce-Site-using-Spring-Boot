package com.ShopCart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ShopCart.Repository.UserRepository;
import com.ShopCart.beans.User;

public class UserDetailServiceimpl implements UserDetailsService {
    
	@Autowired
	private UserRepository repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//fetching user from database
		User user = repo.getUserByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Could not found user");
		}
		
		
		CustomUserDetail customUserDetail = new CustomUserDetail(user);
		
		return customUserDetail;
	}

}
