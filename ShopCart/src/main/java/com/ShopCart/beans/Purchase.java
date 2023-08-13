package com.ShopCart.beans;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Purchase {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bill_id")
	private Long id;
	private Double basicAmount;
	private Double tax;
	private Double totalAmount;
	
	@Column(unique=true)
	private Long billNum;
	
	
	
	private Date billDate;
	
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor; 
	
	@ManyToOne
	@JoinColumn(name="mode_id")
	private PaymentMode paymentMode;
	
	
//	 @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//	    @JoinTable(name = "purchase_product_detail",
//	               joinColumns = @JoinColumn(name = "purchase_id"),
//	               inverseJoinColumns = @JoinColumn(name = "product_detail_id"))
//	    private List<ProductDetail> productDetail;
	
	
	
	@OneToMany(mappedBy =  "purchase",targetEntity = ProductDetail.class,cascade=CascadeType.ALL)
	 private List<ProductDetail> productDetail; 
	 
	 
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", basicAmount=" + basicAmount + ", tax=" + tax + ", totalAmount=" + totalAmount
				+ ", billNum=" + billNum + ", billDate=" + billDate + ", vendor=" + vendor + ", paymentMode="
				+ paymentMode + ", productDetail=" + productDetail + "]";
	}

	public Long getBillNum() {
		return billNum;
	}

	public void setBillNum(Long billNum) {
		this.billNum = billNum;
	}

	

	public List<ProductDetail> getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(List<ProductDetail> productDetail) {
		this.productDetail = productDetail;
	}

	public Double getBasicAmount() {
		return basicAmount;
	}

	public void setBasicAmount(Double basicAmount) {
		this.basicAmount = basicAmount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	
	
	
}
