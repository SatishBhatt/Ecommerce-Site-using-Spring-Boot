package com.ShopCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.ShopCart.Repository.ProfileRepository;
import com.ShopCart.beans.Profile;


@Service
public class ProfileService {

	@Autowired
	private ProfileRepository p_repo;
	
	public List<Profile> getAllProfile(){
		return p_repo.findAll();
	}
	
	
	public Profile saveProfile(Profile profile) {
		return p_repo.save(profile);
	}
	
	
	
	//to get Profile by its id
	public Profile getProfileById(Long id) {
		return p_repo.findById(id).get();

}
	
	
}
