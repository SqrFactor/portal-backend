/**
 * 
 */
package com.sqrfactor.dao.impl.competition;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.competition.CompetitionJuryDao;
import com.sqrfactor.model.competition.CompetitionJury;

/**
 * @author Angad Gill
 *
 */
@Repository("competitionJuryDao")
public class CompetitionJuryDaoImpl extends AbstractDao<Long, CompetitionJury> implements CompetitionJuryDao{

	public CompetitionJury findById(long id) {
		return getByKey(id);
	}
	
	public CompetitionJury findByCompetitionJuryId(long compJuryId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compJuryId", compJuryId));
		return (CompetitionJury) criteria.uniqueResult();
	}
	
	public void saveCompetitionJury(CompetitionJury competitionJury) {
		persist(competitionJury);
	}

	public void deleteCompetitionJuryById(long compJuryId) {
		Query query = getSession().createSQLQuery("delete from competition_jury where compJuryId = :compJuryId");
		query.setLong("compJuryId", compJuryId);
		query.executeUpdate();
	}

}
