package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ShopCart.Repository.AddProductRepositery;

import com.ShopCart.beans.AddProduct;

@Service
public class AddProductService {

	@Autowired
	private AddProductRepositery pro_repo;
	
	
	public List<AddProduct> getAllProduct(){
		return pro_repo.findAll();
	}
	
	
	public  AddProduct saveProduct( AddProduct product) {
		return pro_repo.save(product);
	}
	
	
	
	//to get products by its id
	public AddProduct getProductById(Long id) {
		return pro_repo.findById(id).get();
	}
	
	//to get all products by its id
		public List<AddProduct> getAllProductByCategoryId(Long id) {
			return pro_repo.findAllByCategory_Id(id);
		}
}
