package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.BrandRepository;

import com.ShopCart.beans.Brand;


@Service
public class BrandService {

	
	@Autowired
	private BrandRepository b_repo;
	
	
	public List<Brand> getAllBrand(){
		return b_repo.findAll();
	}
	
	
	public Brand saveBrand(Brand brand) {
		return b_repo.save(brand);
	}
	
	
	
	//to get Brand by its id
	public Brand getBrandById(Long id) {
		return b_repo.findById(id).get();

}
}
