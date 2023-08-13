package com.ShopCart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.Address;

@Repository
public interface AddressRepositery  extends JpaRepository<Address, Long>{

}
