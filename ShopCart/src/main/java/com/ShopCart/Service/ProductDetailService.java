package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.ProductDetailRepository;

import com.ShopCart.beans.ProductDetail;

@Service
public class ProductDetailService {

	@Autowired
	private ProductDetailRepository pd_repo;
	
	
	public List<ProductDetail> getAllProductDetail(){
		return pd_repo.findAll();
	}
	
	
	public ProductDetail saveProductDetail(ProductDetail productDetail) {
		return pd_repo.save(productDetail);
	}
	
	
	
	//to get product detail by its id
	public ProductDetail getProductDetailById(Long id) {
		return pd_repo.findById(id).get();

}
	
	
}
