package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.AddCategoryRepositery;
import com.ShopCart.beans.AddCategory;
@Service
public class AddCategoryService {
@Autowired
	private AddCategoryRepositery cat_repo;
	
	
	public List<AddCategory> getAllCategory(){
		return cat_repo.findAll();
	}
	
	
	public AddCategory saveCategory(AddCategory category) {
		return cat_repo.save(category);
	}
	
	
	
	//to get category by its id
	public AddCategory getCategoryById(Long id) {
		return cat_repo.findById(id).get();
	}
}
