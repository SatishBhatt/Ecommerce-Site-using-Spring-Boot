package com.ShopCart.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopCart.beans.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
