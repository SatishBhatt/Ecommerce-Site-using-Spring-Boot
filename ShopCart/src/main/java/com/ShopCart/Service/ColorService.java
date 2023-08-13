package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ShopCart.Repository.ColorRepository;

import com.ShopCart.beans.Color;

@Service
public class ColorService {
	@Autowired
	private ColorRepository color_repo;
	
	
	public List<Color> getAllColor(){
		return color_repo.findAll();
	}
	
	
	public Color saveColor(Color color) {
		return color_repo.save(color);
	}
	
	
	
	//to get color by its id
	public Color getColorById(Long id) {
		return color_repo.findById(id).get();

}
}
