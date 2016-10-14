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
import com.sqrfactor.dao.ProfessionDao;
import com.sqrfactor.model.Profession;

/**
 * @author Angad Gill
 *
 */
@Repository("professionDao")
public class ProfessionDaoImpl extends AbstractDao<Long, Profession> implements ProfessionDao {

	public ProfessionDaoImpl() {
	}


	@Override
	public Profession findById(long id) {
		return getByKey(id);
	}

	@Override
	public void saveProfession(Profession profession) {
		persist(profession);

	}

	@Override
	public void deleteProfessionById(long id) {
		Query query = getSession().createSQLQuery("delete from profession_details where id = :id");
		query.setLong("id", id);
		query.executeUpdate();

	}

	@Override
	public List<Profession> findAllProfessions() {
		Criteria criteria = createEntityCriteria();
		return (List<Profession>) criteria.list();
	}

	@Override
	public List<Profession> findProfessionsByUserId(long userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (List<Profession>) criteria.list();
	}

}
