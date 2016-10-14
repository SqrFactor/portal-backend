/**
 * 
 */
package com.sqrfactor.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Connection;
import com.sqrfactor.model.Education;
import com.sqrfactor.model.User;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.EducationService;
import com.sqrfactor.service.UserService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class SuggestionController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConnectionService connectionService;
	
	@Autowired
	private EducationService educationService;
	
	/**
	 * Get friend suggestions by user Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/suggest/connections/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<User>> getSuggestedConnectionsByUserId(@PathVariable long userId) {
		List<User> suggestedConnections = new ArrayList<>();
		
		User currentUser = userService.findById(userId);
		if(currentUser == null){
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		
		//Get the colleges user has studied in 
		List<String>currentUserColCodes =  getColCodes(userId);
		
		//Find all the people he/she is following
		List<Connection> currentUserConnections = connectionService.findConnectionsBySourceId(userId);
		List<Long> currentUserFollowing = new ArrayList<>();
		for(Connection connection : currentUserConnections){
			currentUserFollowing.add(connection.getDestinationId());
		}
		
		List<User> users = userService.findAllUsers();
		for(User u : users){
			//See if the platform user has studied in the same college and is not connected
			List<String> uColCodes = getColCodes(u.getUserId());
			if(currentUserColCodes.contains(uColCodes)){
				if(!currentUserFollowing.contains(u.getUserId())){
					suggestedConnections.add(u);
				}
			}
		}
		
		return new ResponseEntity<List<User>>(suggestedConnections, HttpStatus.OK);
	}


	private List<String> getColCodes(long userId){
		List<String> colCodes = new ArrayList<>();
		for(Education education : educationService.findEducationsByUserId(userId)){
			if(StringUtils.isNotBlank(education.getColCode())){
				colCodes.add(education.getColCode());
			}
		}
		return colCodes;
	}
}
