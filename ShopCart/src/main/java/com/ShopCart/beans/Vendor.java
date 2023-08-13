package com.ShopCart.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String vendortype;
    
    @OneToMany(mappedBy =  "vendor",targetEntity = Purchase.class,cascade=CascadeType.ALL)
	 private List<Purchase> purchase; 
   

    
    
	public List<Purchase> getPurchase() {
		return purchase;
	}


	public void setPurchase(List<Purchase> purchase) {
		this.purchase = purchase;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getVendortype() {
		return vendortype;
	}


	public void setVendortype(String vendortype) {
		this.vendortype = vendortype;
	}


	
}
