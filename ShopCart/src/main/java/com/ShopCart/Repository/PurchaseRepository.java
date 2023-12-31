package com.ShopCart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
