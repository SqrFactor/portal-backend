/**
 * 
 */
package com.sqrfactor.dao.impl.competition;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.competition.CompetitionDao;
import com.sqrfactor.model.competition.Competition;

/**
 * @author Angad Gill
 *
 */
@Repository("competitionDao")
public class CompetitionDaoImpl extends AbstractDao<Long, Competition> implements CompetitionDao{

	public Competition findById(long id) {
		return getByKey(id);
	}
	
	public Competition findByCompetitionId(long competitionId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compId", competitionId));
		return (Competition) criteria.uniqueResult();
	}
	
	public void saveCompetition(Competition competition) {
		persist(competition);
	}

	public void deleteCompetitionById(long compId) {
		Query query = getSession().createSQLQuery("delete from competition_details where compId = :compId");
		query.setLong("compId", compId);
		query.executeUpdate();
	}
}
