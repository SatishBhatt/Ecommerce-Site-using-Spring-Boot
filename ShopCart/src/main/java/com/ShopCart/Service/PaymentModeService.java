package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.PaymentModeRepository;

import com.ShopCart.beans.PaymentMode;

@Service
public class PaymentModeService {
	@Autowired
	private PaymentModeRepository pay_repo;
	
	
	public List<PaymentMode> getAllPaymentMode(){
		return pay_repo.findAll();
	}
	
	
	public PaymentMode savePaymentMode(PaymentMode color) {
		return pay_repo.save(color);
	}
	
	
	
	//to get mode by its id
	public PaymentMode getPaymentModeById(Long id) {
		return pay_repo.findById(id).get();

}
}
