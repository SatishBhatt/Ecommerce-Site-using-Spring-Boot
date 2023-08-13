package com.ShopCart.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Wishlist {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	

	
	@ManyToOne
	@JoinColumn(name="product_id")
	private AddProduct product;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	

	public AddProduct getProduct() {
		return product;
	}


	public void setProduct(AddProduct product) {
		this.product = product;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	
	
}
