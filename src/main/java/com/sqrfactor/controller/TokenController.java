/**
 * 
 */
package com.sqrfactor.controller;

import java.util.Collections;
import java.util.List;
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
import com.sqrfactor.model.Invitation;
import com.sqrfactor.model.Login;
import com.sqrfactor.model.SocialLogin;
import com.sqrfactor.model.User;
import com.sqrfactor.model.Verification;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.InvitationService;
import com.sqrfactor.service.LoginService;
import com.sqrfactor.service.SocialLoginService;
import com.sqrfactor.service.UserService;
import com.sqrfactor.service.VerificationService;
import com.sqrfactor.upload.S3Upload;
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
	
	@Autowired
	private InvitationService invitationService;
	
	/**
	 * Verify if user is registered
	 * 
	 * @param inviteCode
	 * @return
	 */
	@RequestMapping(value = "/login/verify", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Boolean> verifyLogin(@RequestParam("emailId") String emailId) {
		
		User user = userService.findByEmailId(emailId);
		if(user == null){
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
				
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	
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
		} else {
			return new ResponseEntity<LoginResponse>(HttpStatus.UNAUTHORIZED);
		}

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
		String imageUrl = socialLoginMap.get("imageUrl");
		String name = socialLoginMap.get("name");
		
		
		if (StringUtils.isNullOrEmpty(socialUID) || StringUtils.isNullOrEmpty(emailId) || StringUtils.isNullOrEmpty(loginVia)) {
			return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
		}
		
		SocialLogin socialLogin = socialLoginService.findBySocialUID(socialUID, loginVia);
		
		if(socialLogin == null){
			//TODO Refactor
			User user = userService.findByEmailId(emailId);
			if(user == null){
				
				//Upload the image
				String profilePicPath = "";
				
				S3Upload s3Upload = new S3Upload();
				 
				String destinationFilePath = "/angad";
				String fileType = "jpg";
				
				boolean uploaded = false;
				String destinationFileName = "";
				if(imageUrl.lastIndexOf("jpg") > -1 ){
					fileType = "jpg";
					destinationFileName = System.currentTimeMillis() + ".JPG";
					uploaded = s3Upload.upload(imageUrl, destinationFilePath, destinationFileName, fileType);
				}else if(imageUrl.lastIndexOf("png") > -1){
					fileType = "png";
					destinationFileName = System.currentTimeMillis() + ".PNG";
					uploaded = s3Upload.upload(imageUrl, destinationFilePath, destinationFileName, fileType);
				}
				
				if(uploaded){
					profilePicPath = "https://d2v59i6n7k35i9.cloudfront.net" + destinationFilePath + "/" + destinationFileName;
				}
				
				//Get the first and last name
				String firstName = "";
				String lastName = "";
				
				if(!StringUtils.isNullOrEmpty(name) && name.contains(" ")){
					firstName = name.substring(0, name.indexOf(" "));
					lastName = name.substring(name.indexOf(" ") + 1);
				}else{
					firstName = name;
				}
				
				user = new User();
				user.setEmailId(emailId);
				user.setVerified(true);
				user.setProfilePicPath(profilePicPath);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setUserTypeId("S101");
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
			socialLogin = new SocialLogin();
			socialLogin.setUserId(user.getUserId());
			socialLogin.setSocialUID(socialUID);
			socialLogin.setLoginVia(loginVia);
			socialLoginService.saveSocialLogin(socialLogin);
			
			//Add Connection to admin account
			addConnectionToAdminAccount(user);
		}
		
		//TODO breaking on signup
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
	 * Signup User For Competition
	 * This API requires a user to be authorized
	 * 
	 * @param user
	 */
	@RequestMapping(value = "/user/signup/competition", method = RequestMethod.POST)
	public ResponseEntity<User> signupUserFromCompetition(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("emailId") String emailId) {
		
		User user = userService.findByEmailId(emailId);
		// Return error if user already exists
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		
		//Create a new user
		user = new User();
		user.setEmailId(emailId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
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
		
		Login login = loginService.findByUsername(username);
		
		if(login == null){
			//Save the Login Details
			Login loginToSave = new Login();
			loginToSave.setUserName(username);
			loginToSave.setUserPassword(password);
			loginToSave.setUserId(user.getUserId());
			loginService.saveLogin(loginToSave);
		}else{
			login.setUserPassword(password);
			loginService.updateLogin(login);
		}
		
		login = loginService.findByUsername(username);

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
			
			//Check if already exists
			Connection c1 = connectionService.findConBySrcAndDestId(connection.getSourceId(), connection.getDestinationId());
			
			if(c1 == null){
				connectionService.saveConnection(connection);
			}	
		}
	}
	
	/**
	 * Verify invitation code
	 * 
	 * @param inviteCode
	 * @return
	 */
	@RequestMapping(value = "/invitation/verify", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Boolean> verifyInvitationCode(@RequestParam("invitationCode") String invitationCode) {
		
		//Exception for sqrfactor email
		if(invitationCode.equals("create@sqrfactor.in") || invitationCode.equals("SQRDEC127") || invitationCode.equals("ARCHSQR")){
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		
		User user = userService.findByEmailId(invitationCode);
		if(user == null){
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		
		List<Invitation> invitations = invitationService.findByInvitedByUserId(user.getUserId());
		
		if(invitations.size() < 2){
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	/**
	 * Verify invitation code
	 * 
	 * @param inviteCode
	 * @return
	 */
	@RequestMapping(value = "/invitationj/verify", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Boolean>> verifyInvitationCodej(@RequestParam("invitationCode") String invitationCode) {
		
		//Exception for sqrfactor email
		if(invitationCode.equals("create@sqrfactor.in") || invitationCode.equals("SQRDEC127") || invitationCode.equals("ARCHSQR") 
				|| invitationCode.equals("ARCHMBL") || invitationCode.equals("archmbl")){
			return new ResponseEntity<Map<String, Boolean>>(Collections.singletonMap("success", true), HttpStatus.OK);
		}
		
		User user = userService.findByEmailId(invitationCode);
		if(user == null){
			return new ResponseEntity<Map<String, Boolean>>(Collections.singletonMap("success", false),HttpStatus.OK);
		}
		
		List<Invitation> invitations = invitationService.findByInvitedByUserId(user.getUserId());
		
		if(invitations.size() < 2){
			return new ResponseEntity<Map<String, Boolean>>(Collections.singletonMap("success", true),HttpStatus.OK);
		}
		
		return new ResponseEntity<Map<String, Boolean>>(Collections.singletonMap("success", false), HttpStatus.OK);
	}

	
	
	/**
	 * Save Invitation
	 * 
	 * @param inviteCode
	 * @return
	 */
	@RequestMapping(value = "/invitation", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Invitation> verifyInvitationCode(@RequestBody Map<String, String> invitationMap) {
		
		if (!invitationMap.containsKey("invitedBy") || !invitationMap.containsKey("invitedTo")) {
			return new ResponseEntity<Invitation>(HttpStatus.BAD_REQUEST);
		}
		
		String invitedBy = invitationMap.get("invitedBy");
		String invitedTo = invitationMap.get("invitedTo");
		
		if(invitedBy.equals("create@sqrfactor.in") || invitedBy.equals("SQRDEC127") || invitedBy.equals("ARCHSQR")){
			invitedBy = "create@sqrfactor.in";
		}
		
		if (StringUtils.isNullOrEmpty(invitedBy) || StringUtils.isNullOrEmpty(invitedTo)) {
			return new ResponseEntity<Invitation>(HttpStatus.BAD_REQUEST);
		}
		
		User invitedByUser = userService.findByEmailId(invitedBy);
		User invitedToUser = userService.findByEmailId(invitedTo);
		
		if(invitedByUser == null || invitedToUser == null){
			return new ResponseEntity<Invitation>(HttpStatus.NOT_FOUND);
		}
		
		Invitation invitation = invitationService.findByInvitedByAndTo(invitedByUser.getUserId(), invitedToUser.getUserId());
		
		if(invitation != null){
			return new ResponseEntity<Invitation>(invitation, HttpStatus.CREATED);
		}
		
		invitation = new Invitation();
		invitation.setInvitedByUserId(invitedByUser.getUserId());
		invitation.setInvitedToUserId(invitedToUser.getUserId());
		
		invitationService.saveInvitation(invitation);
		
		return new ResponseEntity<Invitation>(invitation, HttpStatus.CREATED);
	}

}
