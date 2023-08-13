package com.ShopCart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.Vendor;

@Repository
public interface VendorRepositery extends JpaRepository<Vendor, Long>{
	

}
