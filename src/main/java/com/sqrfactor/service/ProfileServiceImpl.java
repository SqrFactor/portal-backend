package com.sqrfactor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.sqrfactor.bean.Profile;

/**
 * 
 * @author Angad Gill
 *
 */
public class ProfileServiceImpl implements ProfileService {
	private static final AtomicLong counter = new AtomicLong();
	private static List<Profile> profiles;

	static {
		profiles = populateDummyProfiles();
	}

	/**
	 * Find All Profiles
	 */
	public List<Profile> findAllProfiles() {
		return profiles;
	}

	/**
	 * Find Profile by id
	 */
	public Profile findById(long id) {
		for (Profile profile : profiles) {
			if (profile.getId() == id) {
				return profile;
			}
		}
		return null;
	}

	/**
	 * Save Profiles
	 */
	public void saveProfile(Profile profile) {
		profile.setId(counter.incrementAndGet());
		profiles.add(profile);
	}

	/**
	 * Update Profile
	 */
	public void updateProfile(Profile profile) {
		int index = profiles.indexOf(profile);
		profiles.set(index, profile);
	}

	/**
	 * Delete profile by id
	 */
	public void deleteProfileById(long id) {

		for (Iterator<Profile> iterator = profiles.iterator(); iterator.hasNext();) {
			Profile user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	/**
	 * Populate Dummy Data
	 * @return
	 */
	private static List<Profile> populateDummyProfiles() {
		List<Profile> profiles = new ArrayList<Profile>();

		profiles.add(new Profile(counter.incrementAndGet(), "Angad", "Gill", "CEC", new Date()));
		profiles.add(new Profile(counter.incrementAndGet(), "Agnim", "Gupta", "SIT", new Date()));
		return profiles;
	}

	/**
	 * Delete all profiles
	 */
	public void deleteAllProfiles() {
		profiles.clear();
	}

}
