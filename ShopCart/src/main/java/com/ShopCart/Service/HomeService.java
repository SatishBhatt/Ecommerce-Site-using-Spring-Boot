package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.HomeRepositery;


import com.ShopCart.beans.Home;


@Service
public class HomeService {
	
	@Autowired
	private HomeRepositery home_repo;
	
	
	public List<Home> getAllHome(){
		return home_repo.findAll();
	}
	
	
	public Home saveHome(Home home) {
		return home_repo.save(home);
	}
	
	
	
	//to get home by its id
	public Home getHomeById(Long id) {
		return home_repo.findById(id).get();

}

}
