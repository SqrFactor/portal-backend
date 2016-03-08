package com.sqrfactor.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.model.Profile;

/**
 * 
 * @author Angad Gill
 *
 */
@Repository("profileDao")
public class ProfileDaoImpl extends AbstractDao<Long, Profile> implements ProfileDao {

	public Profile findById(long id) {
		return getByKey(id);
	}

	public void saveProfile(Profile profile) {
		persist(profile);
	}

	public void deleteProfileById(long id) {
		Query query = getSession().createSQLQuery("delete from Profile where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Profile> findAllProfiles() {
		Criteria criteria = createEntityCriteria();
		return (List<Profile>) criteria.list();
	}

	public Profile findProfileById(long id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Profile) criteria.uniqueResult();
	}
}
