/**
 * 
 */
package com.sqrfactor.dao.impl.competition;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.competition.CompetitionRegistrationDao;
import com.sqrfactor.model.competition.CompetitionRegistration;

/**
 * @author Angad Gill
 *
 */
@Repository("competitionRegistrationDao")
public class CompetitionRegistrationDaoImpl extends AbstractDao<Long, CompetitionRegistration> implements CompetitionRegistrationDao{

	public CompetitionRegistration findById(long id) {
		return getByKey(id);
	}
	
	public CompetitionRegistration findByCompetitionRegistrationId(long competitionRegistrationId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compRegistrationId", competitionRegistrationId));
		return (CompetitionRegistration) criteria.uniqueResult();
	}
	
	public void saveCompetitionRegistration(CompetitionRegistration competitionRegistration) {
		persist(competitionRegistration);
	}

	public void deleteCompetitionRegistrationById(long compRegistrationId) {
		Query query = getSession().createSQLQuery("delete from competition_registration where compRegistrationId = :compRegistrationId");
		query.setLong("compRegistrationId", compRegistrationId);
		query.executeUpdate();
	}

}
