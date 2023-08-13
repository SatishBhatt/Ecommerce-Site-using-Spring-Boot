package com.ShopCart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.Cart;
import com.ShopCart.beans.User;
import com.ShopCart.beans.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

public List<Wishlist> findByUser(User user);
	
	public Wishlist findByUserAndProduct(User user,AddProduct product);
}
