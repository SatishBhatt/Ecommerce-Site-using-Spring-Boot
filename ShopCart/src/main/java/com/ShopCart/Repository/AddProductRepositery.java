package com.ShopCart.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.AddProduct;
@Repository
public interface AddProductRepositery extends JpaRepository<AddProduct, Long>{

	@Transactional
	@Modifying
	@Query("update AddProduct ap set ap.enable=false where ap.id=:id ")
	public void updateInActive(@Param("id") Long id);
	
	
	@Transactional
	@Modifying
	@Query("update AddProduct ap set ap.enable=true where ap.id=:id ")
	public void updateActive(@Param("id") Long id);
	
	
	 @Query("SELECT p FROM AddProduct p WHERE p.addCategory.id = :categoryId")
	    List<AddProduct> findAllByCategory_Id(Long categoryId);
}
