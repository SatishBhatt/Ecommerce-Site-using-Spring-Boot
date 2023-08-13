package com.ShopCart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.PaymentMode;
@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long> {

}
