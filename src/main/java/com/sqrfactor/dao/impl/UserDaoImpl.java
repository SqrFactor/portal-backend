/**
 * 
 */
package com.sqrfactor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.UserDao;
import com.sqrfactor.model.User;

/**
 * @author Angad Gill
 *
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	public User findById(long id) {
		return getByKey(id);
	}

	public void saveUser(User user) {
		persist(user);
	}

	public void deleteUserById(long userId) {
		Query query = getSession().createSQLQuery("delete from user_details where id = :id");
		query.setLong("userId", userId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

	public User findUserById(long userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (User) criteria.uniqueResult();
	}
	
	public User findByEmailId(String emailId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("emailId", emailId));
		return (User) criteria.uniqueResult();
	}
}
