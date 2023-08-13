package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.PurchaseRepository;
import com.ShopCart.beans.Purchase;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchase_repo;
	
	
	public List<Purchase> getAllPurchase(){
		return purchase_repo.findAll();
	}
	
	
	public Purchase savePurchase(Purchase purchase) {
		return purchase_repo.save(purchase);
	}
	
	
	
	//to get Purchase by its id
	public Purchase getPurchaseById(Long id) {
		return purchase_repo.findById(id).get();

}
	
	
	
}
