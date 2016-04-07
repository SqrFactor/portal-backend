/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.User;

/**
 * @author Angad Gill
 *
 */
public interface UserService {

	User findById(long userId);
	
	User findByEmailId(String emailId);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long userId);

	List<User> findAllUsers();

	void deleteAllUsers();
	
	List<User> searchByEmailOrName(String searchQuery);

	void verifyUser(long userId);
}
