/**
 * 
 */
package com.sqrfactor.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.StringUtils;
import com.sqrfactor.email.Email;
import com.sqrfactor.email.impl.BigRockEmailImpl;
import com.sqrfactor.model.Login;
import com.sqrfactor.model.User;
import com.sqrfactor.service.LoginService;
import com.sqrfactor.service.UserService;
import com.sqrfactor.utils.AuthUtils;

/**
 * @author Angad Gill
 *
 */
@RestController
public class TokenController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Authenticate login
	 * 
	 * @param login
	 */
	@RequestMapping(value = "/login/authenticate", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> authenticateLogin(@RequestBody Map<String, String> loginMap) {
		if (!loginMap.containsKey("username") || !loginMap.containsKey("password")) {
			return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
		}

		String username = loginMap.get("username");
		String password = loginMap.get("password");

		if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)) {
			return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
		}

		Login login = loginService.findByUsername(username);

		if (login != null && login.getUserPassword().equals(password)) {
			String token = AuthUtils.createToken(login.getUserName(), login.getUserId()); 
			LoginResponse response = new LoginResponse(login.getUserId(), login.getUserName(), token);
			
			return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
		}

		return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
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
		
		//Use encryptedKey in future which is user specific
		String verificationKey = "12345";
		// Send Email
		Email email = new BigRockEmailImpl();
		email.sendVerificationMail(emailId, verificationKey);

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	/**
	 * Verify User
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/verify", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestParam("emailId") String emailId, 
			@RequestParam("verificationKey")String verificationKey) {

		User currentUser = userService.findByEmailId(emailId);

		if (currentUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		if(verificationKey.equals("1234")){
			return new ResponseEntity<User>(currentUser, HttpStatus.BAD_REQUEST);
		}
		
		if (!currentUser.isVerified()) {
			userService.verifyUser(currentUser.getUserId());
		}
		
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	/**
	 * Authenticate login
	 * 
	 * @param login
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> registerUser(@RequestBody Map<String, String> loginMap) {
		
		if (!loginMap.containsKey("username") || !loginMap.containsKey("password")) {
			return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
		}

		String username = loginMap.get("username");
		String password = loginMap.get("password");

		if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)) {
			return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
		}
		
		User user = userService.findByEmailId(username);
		
		if (user == null) {
			return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
		}
		
		if(!user.isVerified()){
			return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
		}
		
		//Save the Login Details
		Login loginToSave = new Login();
		loginToSave.setUserName(username);
		loginToSave.setUserPassword(password);
		loginToSave.setUserId(user.getUserId());
		loginService.saveLogin(loginToSave);
		
		Login login = loginService.findByUsername(username);

		if (login != null && login.getUserPassword().equals(password)) {
			String token = AuthUtils.createToken(login.getUserName(), login.getUserId()); 
			LoginResponse response = new LoginResponse(login.getUserId(), login.getUserName(), token);
			
			return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
		}

		return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
	}

	
	@SuppressWarnings("unused")
    private class LoginResponse {
		public long userId;
		public String userName;
		public String token;

        public LoginResponse(final long userId, final String userName, final String token) {
            this.userId = userId;
            this.userName = userName;
        	this.token = token;
        }
    }
}
