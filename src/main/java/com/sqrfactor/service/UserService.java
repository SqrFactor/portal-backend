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

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long userId);

	List<User> findAllUsers();

	void deleteAllUsers();
}
