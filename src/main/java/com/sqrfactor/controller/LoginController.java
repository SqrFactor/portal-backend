/**
 * 
 */
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
import com.sqrfactor.email.Email;
import com.sqrfactor.email.impl.AWSEmailImpl;
import com.sqrfactor.email.impl.BigRockEmailImpl;
import com.sqrfactor.model.Login;
import com.sqrfactor.model.User;
import com.sqrfactor.service.LoginService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	public LoginController() {

	}

	/**
	 * Get all the logins
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public ResponseEntity<List<Login>> getAllLogins() {
		List<Login> logins = loginService.findAllLogins();
		if (logins.isEmpty()) {
			return new ResponseEntity<List<Login>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Login>>(logins, HttpStatus.OK);
	}

	/**
	 * Get a Login by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/login/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Login> getLoginById(@PathVariable int id) {
		Login login = loginService.findById(id);
		if (login == null) {
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Login>(login, HttpStatus.OK);
	}

	/**
	 * Create Login
	 * 
	 * @param login
	 */
	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<Login> createLogin(@RequestBody Login login) {
		loginService.saveLogin(login);

		return new ResponseEntity<Login>(login, HttpStatus.CREATED);
	}

	/**
	 * Update Login
	 * 
	 * @param id
	 * @param login
	 * @return
	 */
	@RequestMapping(value = "/login/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Login> updateLogin(@PathVariable("id") int id, @RequestBody Login login) {
		Login currentLogin = loginService.findById(id);

		if (currentLogin == null) {
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
		}
		login.setUserId(id);
		loginService.updateLogin(login);
		return new ResponseEntity<Login>(login, HttpStatus.OK);
	}

	/**
	 * Delete Login by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/login/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Login> deleteLogin(@PathVariable("id") int id) {
		Login login = loginService.findById(id);
		if (login == null) {
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
		}

		loginService.deleteLoginById(id);
		return new ResponseEntity<Login>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete all logins
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login/", method = RequestMethod.DELETE)
	public ResponseEntity<Login> deleteAllLogins() {
		loginService.deleteAllLogins();
		return new ResponseEntity<Login>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Authenticate login
	 * 
	 * @param login
	 */
	@RequestMapping(value = "/login/authenticate/", method = RequestMethod.POST)
	public ResponseEntity<Login> authenticateLogin(@RequestBody Map<String, String> loginMap) {
		if (!loginMap.containsKey("username") || !loginMap.containsKey("password")) {
			return new ResponseEntity<Login>(HttpStatus.BAD_REQUEST);
		}

		String username = loginMap.get("username");
		String password = loginMap.get("password");

		if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)) {
			return new ResponseEntity<Login>(HttpStatus.BAD_REQUEST);
		}

		Login login = loginService.findByUsername(username);

		if (login != null && login.getUserPassword().equals(password)) {
			return new ResponseEntity<Login>(login, HttpStatus.OK);
		}

		return new ResponseEntity<Login>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Forgot Password
	 * 
	 * @param userName
	 */
	@RequestMapping(value = "/login/forgotpassword", method = RequestMethod.POST)
	public ResponseEntity<Boolean> forgotPassword(@RequestParam("username") String userName) {
		
		Login login = loginService.findByUsername(userName);
		// Return error if login does not exists
		if (login == null) {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
		
		Email email = new AWSEmailImpl();
		email.sendForgotPasswordMail(userName);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
