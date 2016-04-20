/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.UserDao;
import com.sqrfactor.model.User;
import com.sqrfactor.service.UserService;

/**
 * @author Angad Gill
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * Find All Users
	 */
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	/**
	 * Find User by id
	 */
	public User findById(long userId) {
		return userDao.findById(userId);
	}
	
	/**
	 * Find User by emaiId
	 */
	public User findByEmailId(String emailId) {
		return userDao.findByEmailId(emailId);
	}

	/**
	 * Save Users
	 */
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	/**
	 * Update User
	 */
	public void updateUser(User user) {
		User entity = userDao.findById(user.getUserId());
		
		if (entity != null) {
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setDateOfBirth(user.getDateOfBirth());
			entity.setContactNo(user.getContactNo());
			//entity.setEmailId(user.getEmailId());
			entity.setUserTypeId(user.getUserTypeId());
			entity.setProfilePicPath(user.getProfilePicPath());
			//entity.setVerified(user.isVerified());
		}
	}

	/**
	 * Delete user by id
	 */
	public void deleteUserById(long userId) {
		userDao.deleteUserById(userId);
	}

	/**
	 * Delete all users
	 */
	public void deleteAllUsers() {
		// TODO
		// profiles.clear();
	}
	
	public List<User> searchByEmailOrName(String searchQuery){
		return userDao.searchByEmailOrName(searchQuery);
	}
	
	public void verifyUser(long userId){
		User entity = userDao.findById(userId);
		
		if (entity != null) {
			entity.setVerified(true);
		}
	}
}
