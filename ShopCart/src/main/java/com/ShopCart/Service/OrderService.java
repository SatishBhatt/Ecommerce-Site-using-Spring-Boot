package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.AddProductRepositery;
import com.ShopCart.Repository.CartRepository;
import com.ShopCart.Repository.OrderRepository;
import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.Cart;
import com.ShopCart.beans.Order;
import com.ShopCart.beans.User;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository order_repo;
	@Autowired
	private AddProductRepositery pro_repo;

	
	
	
	public List<Order> listOrderItems(User user){
		return order_repo.findByUser(user);
	}
	
	
	
	public Integer addOrder(Long ProductId,Integer quantity,User user) {
		Integer addedQuantity = quantity;
		
		AddProduct product =pro_repo.findById(ProductId).get();
		
	Order orderItem =	order_repo.findByUserAndProduct(user, product);
	
	if(orderItem != null) {
		addedQuantity = orderItem.getQuantity() + quantity;
		orderItem.setQuantity(addedQuantity);
	}
	else {
		orderItem = new Order();
		orderItem.setQuantity(quantity);
		orderItem.setUser(user);
		orderItem.setProduct(product);
	
	}
	order_repo.save(orderItem);
		
		return addedQuantity;
	}

}
