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
import com.sqrfactor.dao.EducationDao;
import com.sqrfactor.model.Education;

/**
 * @author Angad Gill
 *
 */
@Repository("educationDao")
public class EducationDaoImpl extends AbstractDao<Long, Education> implements EducationDao {

	public EducationDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sqrfactor.dao.EducationDao#findById(long)
	 */
	@Override
	public Education findById(long id) {
		return getByKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sqrfactor.dao.EducationDao#saveEducation(com.sqrfactor.model.
	 * Education)
	 */
	@Override
	public void saveEducation(Education education) {
		persist(education);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sqrfactor.dao.EducationDao#deleteEducationByUserId(long)
	 */
	@Override
	public void deleteEducationById(long id) {
		Query query = getSession().createSQLQuery("delete from education_details where id = :id");
		query.setLong("id", id);
		query.executeUpdate();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sqrfactor.dao.EducationDao#findAllEducations()
	 */
	@Override
	public List<Education> findAllEducations() {
		Criteria criteria = createEntityCriteria();
		return (List<Education>) criteria.list();
	}

	@Override
	public List<Education> findEducationsByUserId(long userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (List<Education>) criteria.list();
	}

}
