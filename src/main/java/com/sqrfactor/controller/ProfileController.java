package com.sqrfactor.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.bean.Profile;

@RestController
public class ProfileController {

	public ProfileController() {
		
	}

	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Profile getProfileById(@PathVariable int id){
		List<Profile> listOfProfiles = createProfileList();
		for (Profile profile : listOfProfiles){
			if(profile.getId() == id){
				return profile;
			}
		}
		return null;
	}
	
	// Utiliy method to create profile list.  
	 public List<Profile> createProfileList()  
	 {  
		 Profile firstProfile = new Profile(1, "Angad", "Gill", "CEC", new Date());
		 Profile secondProfile = new Profile(2, "Agnim", "Gupta", "SIT", new Date());
	  
		 List<Profile> listOfProfiles = new ArrayList<Profile>();  
		 listOfProfiles.add(firstProfile);
		 listOfProfiles.add(secondProfile);
		   
		 return listOfProfiles;  
	 }  
}
