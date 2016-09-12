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
import com.sqrfactor.model.Connection;
import com.sqrfactor.model.Login;
import com.sqrfactor.model.SocialLogin;
import com.sqrfactor.model.User;
import com.sqrfactor.model.Verification;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.LoginService;
import com.sqrfactor.service.SocialLoginService;
import com.sqrfactor.service.UserService;
import com.sqrfactor.service.VerificationService;
import com.sqrfactor.util.RandomGenerator;
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
	
	@Autowired
	private VerificationService verificationService;
	
	@Autowired
	private ConnectionService connectionService;
	
	@Autowired 
	private SocialLoginService socialLoginService;
	
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
	 * Authenticate Social login
	 * 
	 * @param login
	 */
	@RequestMapping(value = "/login/social/authenticate", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> authenticateSocialLogin(@RequestBody Map<String, String> socialLoginMap) {
		if (!socialLoginMap.containsKey("socialUID") || !socialLoginMap.containsKey("emailId") || !socialLoginMap.containsKey("loginVia")) {
			return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
		}

		String socialUID = socialLoginMap.get("socialUID");
		String emailId = socialLoginMap.get("emailId");
		String loginVia = socialLoginMap.get("loginVia");
		
		if (StringUtils.isNullOrEmpty(socialUID) || StringUtils.isNullOrEmpty(emailId) || StringUtils.isNullOrEmpty(loginVia)) {
			return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
		}
		
		SocialLogin socialLogin = socialLoginService.findBySocialUID(socialUID, loginVia);
		
		if(socialLogin == null){
			
			User user = userService.findByEmailId(emailId);
			if(user == null){
				user = new User();
				user.setEmailId(emailId);
				user.setVerified(true);
				userService.saveUser(user);
			}
			
			Login login = loginService.findById(user.getUserId());
			if(login == null){
				Login loginToSave = new Login();
				loginToSave.setUserId(user.getUserId());
				loginToSave.setUserName(emailId);
				loginToSave.setUserPassword("");
				loginService.saveLogin(loginToSave);
			}
		
			//Create Social Login
			SocialLogin socialLoginToSave = new SocialLogin();
			socialLoginToSave.setUserId(user.getUserId());
			socialLoginToSave.setSocialUID(socialUID);
			socialLoginToSave.setLoginVia(loginVia);
			socialLoginService.saveSocialLogin(socialLoginToSave);
		}
		
		Login login = loginService.findByUserId(socialLogin.getUserId());
		
		//TODO Change since username could be empty/null
		String token = AuthUtils.createToken(login.getUserName(), login.getUserId()); 
		LoginResponse response = new LoginResponse(login.getUserId(), login.getUserName(), token);
			
		return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
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
		
		Verification verification = verificationService.findByUserId(user.getUserId());
		if(verification == null){
			verification = new Verification();
			verification.setVerificationUserId(user.getUserId());
			verification.setEmailCode(RandomGenerator.nextRandom());
			verificationService.saveVerification(verification);	
		}else{
			verification.setEmailCode(RandomGenerator.nextRandom());
			verificationService.updateVerification(verification);
		}

		// Send Email
		Email email = new BigRockEmailImpl();		
		email.sendVerificationMail(emailId, verification.getEmailCode());
		
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
	public ResponseEntity<User> updateUser(@RequestParam("emailId") String emailId, @RequestParam("verificationCode") String verificationCode) {

		User currentUser = userService.findByEmailId(emailId);

		if (currentUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		if (!currentUser.isVerified()) {
			//Confirm the verification Code
			Verification verification = verificationService.findByUserId(currentUser.getUserId());
			
			if (verification == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			
			//verify user if code matches
			if(!StringUtils.isNullOrEmpty(verificationCode) && verification.getEmailCode().equals(verificationCode)){
				userService.verifyUser(currentUser.getUserId());
				addConnectionToAdminAccount(currentUser);
				
			}else{
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}
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
		
		String newPassword = RandomGenerator.nextRandom();
		
		//Save New Password
		login.setUserPassword(newPassword);
		loginService.updateLogin(login);
		
		//Send Email
		Email email = new BigRockEmailImpl();
		email.sendForgotPasswordMail(userName, newPassword);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
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
	
	/**
	 * 
	 * @param user
	 */
	private void addConnectionToAdminAccount(User user){
	
		//Add Connection to Admin Account
		Connection connection = new Connection();
		
		connection.setSourceId(user.getUserId());
		User adminUser = userService.findByEmailId("create@sqrfactor.in");
		if(adminUser!= null){
			connection.setDestinationId(adminUser.getUserId());
			connectionService.saveConnection(connection);	
		}
	}
}
