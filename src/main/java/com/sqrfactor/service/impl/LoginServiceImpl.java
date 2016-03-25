/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.LoginDao;
import com.sqrfactor.model.Login;
import com.sqrfactor.service.LoginService;

/**
 * @author Angad Gill
 *
 */
@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	/**
	 * Find All Logins
	 */
	public List<Login> findAllLogins() {
		return loginDao.findAllLogins();
	}

	/**
	 * Find Login by id
	 */
	public Login findById(long userId) {
		return loginDao.findById(userId);
	}
	
	/**
	 * Find login by username
	 */
	public Login findByUsername(String userName){
		return loginDao.findLoginByUsername(userName);
	}

	/**
	 * Save Logins
	 */
	public void saveLogin(Login login) {
		//String userId = // generate a uuid  
		  //       login.setUserId(userId);           
		         if(login.getUserPassword() != null) {  
		             // We'll use a Random Number Generator to generate salts. This is much more secure  
		             // than using a username as a salt or not having a salt at all.  
		             RandomNumberGenerator rng = new SecureRandomNumberGenerator();  
		             Object salt = rng.nextBytes();  
		             //hash the plain-text password with the random salt and multiple  
		             //iterations and then Base64-encode the value (requires less space than Hex)  
		             final int SHIRO_CREDENTIAL_HASH_INTERATION = 1024;  
		             String hashedPasswordBase64 = new Sha512Hash(login.getUserPassword(), salt,  
		                     SHIRO_CREDENTIAL_HASH_INTERATION).toBase64();  
		             login.setUserPassword(hashedPasswordBase64);  
		             login.setPasswordSalt(salt.toString());              
		         } else {  
		        	 System.out.println("password is null");
		             //throw new Exception("password is null");  
		         }  
		         //userDAO.createUser(userBean);  
		loginDao.saveLogin(login);
	}

	/**
	 * Update Login
	 */
	public void updateLogin(Login login) {
		Login entity = loginDao.findById(login.getUserId());
		if (entity != null) {
			entity.setUserName(login.getUserName());
			entity.setUserPassword(login.getUserPassword());
		}
	}

	/**
	 * Delete login by id
	 */
	public void deleteLoginById(long loginId) {
		loginDao.deleteLoginById(loginId);
	}

	/**
	 * Delete all logins
	 */
	public void deleteAllLogins() {
		// TODO
		// profiles.clear();
	}

}
