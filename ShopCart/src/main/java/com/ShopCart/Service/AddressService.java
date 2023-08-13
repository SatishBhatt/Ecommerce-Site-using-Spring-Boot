package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.AddressRepositery;
import com.ShopCart.beans.Address;

@Service
public class AddressService {
@Autowired
private AddressRepositery add_repo;
	
	public List<Address> getAllAddress(){
		return add_repo.findAll();
	}
	
	
	public Address saveAddress(Address address) {
		return add_repo.save(address);
	}
	
	
	
	//to get address by its id
	public Address getAddressById(Long id) {
		return add_repo.findById(id).get();

}
}
