package com.ShopCart.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Color {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String colorName;
	
	

    @OneToMany(mappedBy =  "color",targetEntity = AddProduct.class,cascade=CascadeType.ALL)
	 private List<AddProduct> product;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getColorName() {
		return colorName;
	}



	public void setColorName(String colorName) {
		this.colorName = colorName;
	}



	public List<AddProduct> getProduct() {
		return product;
	}



	public void setProduct(List<AddProduct> product) {
		this.product = product;
	}
}
