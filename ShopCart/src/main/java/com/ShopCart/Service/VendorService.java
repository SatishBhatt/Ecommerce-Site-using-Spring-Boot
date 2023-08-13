package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ShopCart.Repository.VendorRepositery;

import com.ShopCart.beans.Vendor;

@Service
public class VendorService {
	@Autowired
	private VendorRepositery v_repo;
	
	
	public List<Vendor> getAllVendor(){
		return v_repo.findAll();
	}
	
	
	public Vendor saveVendor(Vendor vendor) {
		return v_repo.save(vendor);
	}
	
	
	
	//to get vendor by its id
	public Vendor getVendorById(Long id) {
		return v_repo.findById(id).get();

}
}
