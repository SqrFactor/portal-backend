package com.sqrfactor.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.bean.Profile;

@RestController
public class ProfileController {

	private List<Profile> listOfProfiles = new ArrayList<Profile>();
	
	public ProfileController() {
		Profile firstProfile = new Profile(0, "Angad", "Gill", "CEC", new Date());
		Profile secondProfile = new Profile(1, "Agnim", "Gupta", "SIT", new Date());

		listOfProfiles.add(firstProfile);
		listOfProfiles.add(secondProfile);
	}
	
	/**
	 * Get all the profiles
	 * @return
	 */
	@RequestMapping(value = "/profile/", method = RequestMethod.GET)
	public ResponseEntity<List<Profile>> getAllProfiles(){
		if(listOfProfiles.isEmpty()){
			return new ResponseEntity<List<Profile>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Profile>>(listOfProfiles, HttpStatus.OK);
	}

	/**
	 * Get a Profile by Id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Profile> getProfileById(@PathVariable int id){
		System.out.println(id);
		Profile currentProfile = listOfProfiles.get(id);
		System.out.println(currentProfile.getId());
		if(currentProfile == null){
			return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Profile>(currentProfile, HttpStatus.OK);
	}
	
	/**
	 * Create Profile
	 * @param profile
	 */
	@RequestMapping(value = "/profile/", method = RequestMethod.POST)
	public ResponseEntity<Profile> createProfile(@RequestBody Profile profile){
		listOfProfiles.add(profile);
		return new ResponseEntity<Profile>(profile, HttpStatus.CREATED);
	}
	
	/**
	 * Update Profile
	 * @param id
	 * @param profile
	 * @return
	 */
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Profile> updateProfile(@PathVariable("id")int id, @RequestBody Profile profile){
		Profile currentProfile = listOfProfiles.get(id);
		if(currentProfile == null){
			return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
		}
		listOfProfiles.add(id, profile);
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}
	
	/**
	 * Delete Profile
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Profile> deleteProfile(@PathVariable("id") int id){
		Profile currentProfile = listOfProfiles.get(id);
		if(currentProfile == null){
			return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
		}
		listOfProfiles.remove(id);
		return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
	}
}
