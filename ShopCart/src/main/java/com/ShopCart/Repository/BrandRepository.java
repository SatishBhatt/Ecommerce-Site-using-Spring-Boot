package com.ShopCart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
