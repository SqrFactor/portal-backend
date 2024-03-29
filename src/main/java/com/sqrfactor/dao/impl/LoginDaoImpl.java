package com.sqrfactor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.LoginDao;
import com.sqrfactor.model.Login;
import com.sqrfactor.model.SocialLogin;

/**
 * @author Angad Gill
 *
 */
@Repository("loginDao")
public class LoginDaoImpl extends AbstractDao<Long, Login> implements LoginDao {

	public Login findById(long id) {
		return getByKey(id);
	}
	
	public Login findByUserId(long userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (Login) criteria.uniqueResult();
	}
	
	public void saveLogin(Login login) {
		persist(login);
	}

	public void deleteLoginById(long userId) {
		Query query = getSession().createSQLQuery("delete from login_details where userId = :userId");
		query.setLong("userId", userId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Login> findAllLogins() {
		Criteria criteria = createEntityCriteria();
		return (List<Login>) criteria.list();
	}

	public Login findLoginById(long userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (Login) criteria.uniqueResult();
	}

	public Login findLoginByUsername(String userName){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userName", userName));
		return (Login) criteria.uniqueResult();
	}
}
