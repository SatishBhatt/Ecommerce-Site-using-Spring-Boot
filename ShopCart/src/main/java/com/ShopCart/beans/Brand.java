package com.ShopCart.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Brand {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String brandName;
	

    @OneToMany(mappedBy =  "brand",targetEntity = AddProduct.class,cascade=CascadeType.ALL)
	 private List<AddProduct> product;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public List<AddProduct> getProduct() {
		return product;
	}


	public void setProduct(List<AddProduct> product) {
		this.product = product;
	}
}
