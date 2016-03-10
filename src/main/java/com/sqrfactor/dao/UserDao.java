package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.User;

/**
 * @author Angad Gill
 *
 */
public interface UserDao {

	User findById(long id);
	
	User findByEmailId(String emailId);

	void saveUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	User findUserById(long id);
}
