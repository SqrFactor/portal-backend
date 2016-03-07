package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Profile;

public interface ProfileDao {

	 Profile findById(long id);
	 
	 void saveProfile(Profile profile);
	     
	 void deleteProfileById(long id);
	     
	 List<Profile> findAllProfiles();
	 
	 Profile findProfileById(long id);
}
