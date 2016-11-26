package com.sqrfactor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.User;
import com.sqrfactor.service.UserService;

/**
 * 
 * @author Angad Gill
 *
 */
@RestController
public class SearchController {

	@Autowired
	private UserService userService;

	/**
	 * Get a College by colCode
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<User>> searchByEmailOrUser(@RequestParam("q") String searchQuery) {

		List<User> users = userService.searchByEmailOrName(searchQuery);
				
		if (users == null || users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

}