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
import com.sqrfactor.dao.SocialLoginDao;
import com.sqrfactor.model.SocialLogin;

/**
 * @author Angad Gill
 *
 */
@Repository("socialLoginDao")
public class SocialLoginDaoImpl extends AbstractDao<Long, SocialLogin> implements SocialLoginDao{
	
	public SocialLogin findById(long id) {
		return getByKey(id);
	}
	
	public void saveSocialLogin(SocialLogin socialLogin) {
		persist(socialLogin);
	}

	public void deleteSocialLoginByUserId(long userId) {
		Query query = getSession().createSQLQuery("delete from social_login where userId = :userId");
		query.setLong("userId", userId);
		query.executeUpdate();
	}

	public List<SocialLogin> findAllSocialLogins() {
		Criteria criteria = createEntityCriteria();
		return (List<SocialLogin>) criteria.list();
	}

	public SocialLogin findByUserId(long userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (SocialLogin) criteria.uniqueResult();
	}

	public SocialLogin findBySocialUID(String socialUID, String loginVia) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("socialUID", socialUID));
		criteria.add(Restrictions.eq("loginVia", loginVia));
		return (SocialLogin) criteria.uniqueResult();
	}

}
