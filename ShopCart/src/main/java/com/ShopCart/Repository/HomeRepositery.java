package com.ShopCart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.Home;

@Repository
public interface HomeRepositery extends JpaRepository<Home, Long> {

}
