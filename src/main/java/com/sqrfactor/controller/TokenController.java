/**
 * 
 */
package com.sqrfactor.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.StringUtils;
import com.sqrfactor.model.Login;
import com.sqrfactor.service.LoginService;
import com.sqrfactor.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Angad Gill
 *
 */
@RestController
public class TokenController {
	
	private String secretKey = "SQRFACTOR@Bhive";
	
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
			String token = Jwts.builder().setSubject(login.getUserName())
					//Return Role in future
					.claim("userId", login.getUserId())
					.setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, secretKey).compact();
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
