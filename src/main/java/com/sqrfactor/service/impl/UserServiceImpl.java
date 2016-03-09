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
			entity.setDob(user.getDob());
			entity.setContactNo(user.getContactNo());
			entity.setEmailId(user.getEmailId());
			entity.setColCode(user.getColCode());
			entity.setHighGrad(user.getHighGrad());
			entity.setYearGrad(user.getYearGrad());
			entity.setUserTypeId(user.getUserTypeId());
			entity.setVerified(user.isVerified());
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
}
