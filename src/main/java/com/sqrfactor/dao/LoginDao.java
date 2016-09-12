package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Login;

/**
 * @author Angad Gill
 *
 */
public interface LoginDao {

	Login findById(long userId);
	
	Login findByUserId(long userId);
	
	void saveLogin(Login login);

	void deleteLoginById(long id);

	List<Login> findAllLogins();

	Login findLoginById(long id);

	Login findLoginByUsername(String userName);
}
