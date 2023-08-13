package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.AddProductRepositery;
import com.ShopCart.Repository.WishlistRepository;
import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.Cart;
import com.ShopCart.beans.User;
import com.ShopCart.beans.Wishlist;

@Service
public class WishlistService {

	@Autowired
	private WishlistRepository wish_repo;
	@Autowired
	private AddProductRepositery pro_repo;
	
	
	public List<Wishlist> listWishItems(User user){
		return wish_repo.findByUser(user);
	}
	
	
	public void addProduct(Long ProductId,User user) {
		
		
		AddProduct product =pro_repo.findById(ProductId).get();
		
	Wishlist wish =	wish_repo.findByUserAndProduct(user, product);
	
	if(wish != null) {
		//
	}
	else {
		wish = new Wishlist();
		
		wish.setUser(user);
        wish.setProduct(product);
	
	}
	
	wish_repo.save(wish);
		
		
	}
	
	
	
	public void removeWishProduct(Long ProductId,User user) {
		AddProduct product =pro_repo.findById(ProductId).get();
		Wishlist wishItem =	wish_repo.findByUserAndProduct(user, product);
		
		if(wishItem != null) {
			wish_repo.delete(wishItem);
		}
	}
}
