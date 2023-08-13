package com.ShopCart.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.Cart;
import com.ShopCart.beans.Order;
import com.ShopCart.beans.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	
public List<Order> findByUser(User user);
	
	public Order findByUserAndProduct(User user,AddProduct product);
	
}
