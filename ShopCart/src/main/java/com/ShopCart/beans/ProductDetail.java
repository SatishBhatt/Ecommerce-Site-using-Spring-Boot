package com.ShopCart.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ProductDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long quantity;
	private Double cost;
	private Double tax;
	private Double amount;
	
	
//	
//	 @ManyToMany(mappedBy = "productDetail",fetch = FetchType.LAZY)
//	    private List<Purchase> purchase;


	@ManyToOne
	@JoinColumn(name="purchase_id")
	private Purchase purchase;
	
	
	
	
	
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private AddProduct addProduct;
	
	
	public AddProduct getAddProduct() {
		return addProduct;
	}
	public void setAddProduct(AddProduct addProduct) {
		this.addProduct = addProduct;
	}
	
//	public List<Purchase> getPurchase() {
//		return purchase;
//	}
//	public void setPurchase(List<Purchase> purchase) {
//		this.purchase = purchase;
//	}
	
	
	public Long getId() {
		return id;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", quantity=" + quantity + ", cost=" + cost + ", tax=" + tax + ", amount="
				+ amount + ", purchase=" + purchase + ", addProduct=" + addProduct + "]";
	}

}


