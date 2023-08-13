package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.AddProductRepositery;
import com.ShopCart.Repository.CartRepository;
import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.Cart;
import com.ShopCart.beans.User;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cart_repo;
	@Autowired
	private AddProductRepositery pro_repo;


	public List<Cart> listCartItems(User user){
		return cart_repo.findByUser(user);
	}
	
	
	
	public Integer addProduct(Long ProductId,Integer quantity,User user) {
		Integer addedQuantity = quantity;
		
		AddProduct product =pro_repo.findById(ProductId).get();
		
	Cart cartItem =	cart_repo.findByUserAndProduct(user, product);
	
	if(cartItem != null) {
		addedQuantity = cartItem.getQuantity() + quantity;
		cartItem.setQuantity(addedQuantity);
	}
	else {
		cartItem = new Cart();
		cartItem.setQuantity(quantity);
		cartItem.setUser(user);
        cartItem.setProduct(product);
	
	}
	
	cart_repo.save(cartItem);
		
		return addedQuantity;
	}
	
	
	public void removeCartProduct(Long ProductId,User user) {
		AddProduct product =pro_repo.findById(ProductId).get();
		Cart cartItem =	cart_repo.findByUserAndProduct(user, product);
		
		if(cartItem != null) {
			cart_repo.delete(cartItem);
		}
	}
	
	
	
	
	
	
	
	
//	public void removeProduct(Long productId,User user) {
//		cart_repo.deleteByCustomerAndProduct(user.getId(), productId);
//	}
//	
	
	
	
	
	

	
	
}
