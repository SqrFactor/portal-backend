/**
 * 
 */
package com.sqrfactor.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.VerificationDao;
import com.sqrfactor.model.Connection;
import com.sqrfactor.model.Verification;

/**
 * @author Angad Gill
 *
 */
@Repository("verificationDao")
public class VerificationDaoImpl extends AbstractDao<Long, Verification> implements VerificationDao {

	public Verification findById(long verificationId) {
		return getByKey(verificationId);
	}

	public void saveVerification(Verification verification) {
		persist(verification);
	}

	public void deleteVerificationById(long verificationId) {
		Query query = getSession().createSQLQuery("delete from verification_details where verificationId = :verificationId");
		query.setLong("verificationId", verificationId);
		query.executeUpdate();
	}
	
	public Verification findByUserId(long verificationUserId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("verificationUserId", verificationUserId));
		return (Verification) criteria.uniqueResult();
	}
}
