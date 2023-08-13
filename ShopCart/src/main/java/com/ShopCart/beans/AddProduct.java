package com.ShopCart.beans;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class AddProduct {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String productName;
	@Column(length = 500000)
	private String description;
	private Double MRP;
	private Double cost;
	private Double discount;
	private Boolean enable;
	
	
	
	private String images;
	
//	private MultipartFile tempimg;
	
	
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@ManyToOne
	@JoinColumn(name="category_id")
	private AddCategory addCategory;
	
	
	
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;
	

	@OneToMany(mappedBy =  "addProduct",targetEntity = ProductDetail.class,cascade=CascadeType.ALL)
	 private List<ProductDetail> productDetail; 
	
	

	@OneToMany(mappedBy =  "product",targetEntity = Cart.class,cascade=CascadeType.ALL)
	 private List<Cart> cart; 
	
	@OneToMany(mappedBy =  "product",targetEntity = Order.class,cascade=CascadeType.ALL)
	 private List<Order> order; 
	
	@OneToMany(mappedBy =  "product",targetEntity = Wishlist.class,cascade=CascadeType.ALL)
	 private List<Wishlist> wishlist; 
	

	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public List<Wishlist> getWishlist() {
		return wishlist;
	}
	public void setWishlist(List<Wishlist> wishlist) {
		this.wishlist = wishlist;
	}
	public List<Cart> getCart() {
		return cart;
	}
	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}
	public List<ProductDetail> getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(List<ProductDetail> productDetail) {
		this.productDetail = productDetail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getMRP() {
		return MRP;
	}
	public void setMRP(Double mRP) {
		MRP = mRP;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
	
public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public AddCategory getAddCategory() {
		return addCategory;
	}
	public void setAddCategory(AddCategory addCategory) {
		this.addCategory = addCategory;
	}
	
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
