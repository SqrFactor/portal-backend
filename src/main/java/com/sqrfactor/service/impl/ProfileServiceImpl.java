package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.ProfileDao;
import com.sqrfactor.model.Profile;
import com.sqrfactor.service.ProfileService;

/**
 * 
 * @author Angad Gill
 *
 */
@Service("profileService")
@Transactional
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileDao profileDao;
	

	/**
	 * Find All Profiles
	 */
	public List<Profile> findAllProfiles() {
		return profileDao.findAllProfiles();
	}

	/**
	 * Find Profile by id
	 */
	public Profile findById(long id) {
		return profileDao.findById(id);
	}

	/**
	 * Save Profiles
	 */
	public void saveProfile(Profile profile) {
		profileDao.saveProfile(profile);
	}

	/**
	 * Update Profile
	 */
	public void updateProfile(Profile profile) {
		Profile entity = profileDao.findById(profile.getId());
		if(entity!=null){
			entity.setFirstName(profile.getFirstName());
			entity.setLastName(profile.getLastName());
			entity.setCollegeName(profile.getCollegeName());
			entity.setDateOfBirth(profile.getDateOfBirth());
		}
	}

	/**
	 * Delete profile by id
	 */
	public void deleteProfileById(long id) {
		profileDao.deleteProfileById(id);
	}

	/**
	 * Delete all profiles
	 */
	public void deleteAllProfiles() {
		//TODO
		//profiles.clear();
	}

}
