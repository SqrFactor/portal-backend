/**
 * 
 */
package com.sqrfactor.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.AdditionalProfessionDao;
import com.sqrfactor.model.AdditionalProfession;

/**
 * @author Angad Gill
 *
 */
@Repository("additionalProfessionDao")
public class AdditionalProfessionDaoImpl extends AbstractDao<Long, AdditionalProfession> implements AdditionalProfessionDao {

	public AdditionalProfession findById(long id) {
		return getByKey(id);
	}

	public void saveAdditionalProfession(AdditionalProfession additionalProfession) {
		persist(additionalProfession);
	}

	public void deleteAdditionalProfessionById(long id) {
		Query query = getSession().createSQLQuery("delete from additional_profession_details where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}
	
	public AdditionalProfession findByUserId(long userId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (AdditionalProfession) criteria.uniqueResult();
	}

}
