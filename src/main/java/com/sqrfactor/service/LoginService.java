/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Login;

/**
 * @author Angad Gill
 *
 */
public interface LoginService {
	
	Login findById(long userId);
	
	Login findByUsername(String userName);

	void saveLogin(Login login);

	void updateLogin(Login login);

	void deleteLoginById(long userId);

	List<Login> findAllLogins();

	void deleteAllLogins();
}
