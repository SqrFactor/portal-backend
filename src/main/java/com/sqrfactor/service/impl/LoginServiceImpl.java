/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

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
	
	public Login findByUserId(long userId){
		return loginDao.findByUserId(userId);
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
