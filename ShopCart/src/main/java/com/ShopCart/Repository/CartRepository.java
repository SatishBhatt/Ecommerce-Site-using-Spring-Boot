package com.ShopCart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.Cart;
import com.ShopCart.beans.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	public List<Cart> findByUser(User user);
	
	public Cart findByUserAndProduct(User user,AddProduct product);
	
	
}
