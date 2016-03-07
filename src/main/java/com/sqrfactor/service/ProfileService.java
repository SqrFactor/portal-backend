package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Profile;

public interface ProfileService {

	Profile findById(long id);

	void saveProfile(Profile profile);

	void updateProfile(Profile profile);

	void deleteProfileById(long id);

	List<Profile> findAllProfiles();

	void deleteAllProfiles();
}
