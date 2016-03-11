package com.sqrfactor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.StringUtils;
import com.sqrfactor.email.EmailSender;
import com.sqrfactor.model.User;
import com.sqrfactor.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	public UserController() {

	}

	/**
	 * Get all the users
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 * Get a User by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * Create User
	 * 
	 * @param user
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		// Return error if user already exists
		if (userService.findByEmailId(user.getEmailId()) != null) {
			return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	/**
	 * Update User
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
		User currentUser = userService.findById(id);

		if (currentUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	/**
	 * Delete User by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete all users
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Verify User
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/verify", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestParam("emailId") String emailId) {

		User currentUser = userService.findByEmailId(emailId);

		if (currentUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		if (!currentUser.isVerified()) {
			// Update the verfied flag
			currentUser.setVerified(true);
			userService.updateUser(currentUser);
		}

		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	/**
	 * Signup User
	 * 
	 * @param user
	 */
	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public ResponseEntity<User> signupUser(@RequestParam("emailId") String emailId) {
		
		User user = userService.findByEmailId(emailId);
		// Return error if user already exists
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
		}
		
		//Create a new user
		user = new User();
		user.setEmailId(emailId);
		userService.saveUser(user);

		// Send Email
		EmailSender emailSender = new EmailSender(emailId);
		emailSender.send();

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
}
