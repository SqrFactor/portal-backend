/**
 * 
 */
package com.sqrfactor.dao.impl.competition;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.competition.CompetitionAwardDao;
import com.sqrfactor.model.competition.CompetitionAward;

/**
 * @author Angad Gill
 *
 */
@Repository("competitionAwardDao")
public class CompetitionAwardDaoImpl extends AbstractDao<Long, CompetitionAward> implements CompetitionAwardDao{

	public CompetitionAward findById(long id) {
		return getByKey(id);
	}
	
	public CompetitionAward findByCompetitionAwardId(long compAwardId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compAwardId", compAwardId));
		return (CompetitionAward) criteria.uniqueResult();
	}
	
	public void saveCompetitionAward(CompetitionAward competitionAward) {
		persist(competitionAward);
	}

	public void deleteCompetitionAwardById(long compAwardId) {
		Query query = getSession().createSQLQuery("delete from competition_awards where compAwardId = :compAwardId");
		query.setLong("compAwardId", compAwardId);
		query.executeUpdate();
	}
}
