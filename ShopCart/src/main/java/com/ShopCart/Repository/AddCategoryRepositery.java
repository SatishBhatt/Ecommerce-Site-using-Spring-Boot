package com.ShopCart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.AddCategory;

@Repository
public interface AddCategoryRepositery extends JpaRepository<AddCategory, Long>{

}
