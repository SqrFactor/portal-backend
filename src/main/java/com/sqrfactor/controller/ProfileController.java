package com.sqrfactor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.bean.Profile;
import com.sqrfactor.service.ProfileService;

@RestController
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	public ProfileController() {

	}

	/**
	 * Get all the profiles
	 * 
	 * @return
	 */
	@RequestMapping(value = "/profile/", method = RequestMethod.GET)
	public ResponseEntity<List<Profile>> getAllProfiles() {
		List<Profile> profiles = profileService.findAllProfiles();
		if (profiles.isEmpty()) {
			return new ResponseEntity<List<Profile>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Profile>>(profiles, HttpStatus.OK);
	}

	/**
	 * Get a Profile by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Profile> getProfileById(@PathVariable int id) {
		Profile profile = profileService.findById(id);
		if (profile == null) {
			return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}

	/**
	 * Create Profile
	 * 
	 * @param profile
	 */
	@RequestMapping(value = "/profile/", method = RequestMethod.POST)
	public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
		profileService.saveProfile(profile);

		return new ResponseEntity<Profile>(profile, HttpStatus.CREATED);
	}

	/**
	 * Update Profile
	 * 
	 * @param id
	 * @param profile
	 * @return
	 */
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Profile> updateProfile(@PathVariable("id") int id, @RequestBody Profile profile) {
		Profile currentProfile = profileService.findById(id);

		if (currentProfile == null) {
			return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
		}

		currentProfile.setFirstName(profile.getFirstName());
		currentProfile.setLastName(profile.getLastName());
		currentProfile.setCollegeName(profile.getCollegeName());
		currentProfile.setDateOfBirth(profile.getDateOfBirth());

		profileService.updateProfile(currentProfile);
		return new ResponseEntity<Profile>(currentProfile, HttpStatus.OK);
	}

	/**
	 * Delete Profile by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Profile> deleteProfile(@PathVariable("id") int id) {
		Profile profile = profileService.findById(id);
		if (profile == null) {
			return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
		}

		profileService.deleteProfileById(id);
		return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete all profiles
	 * 
	 * @return
	 */
	@RequestMapping(value = "/profile/", method = RequestMethod.DELETE)
	public ResponseEntity<Profile> deleteAllProfiles() {
		profileService.deleteAllProfiles();
		return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
	}
}
