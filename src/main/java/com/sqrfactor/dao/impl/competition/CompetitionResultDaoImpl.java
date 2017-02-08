/**
 * 
 */
package com.sqrfactor.dao.impl.competition;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.competition.CompetitionResultDao;
import com.sqrfactor.model.competition.CompetitionResult;

/**
 * @author Angad Gill
 *
 */
@Repository("competitionResultDao")
public class CompetitionResultDaoImpl extends AbstractDao<Long, CompetitionResult> implements CompetitionResultDao{

	public CompetitionResult findById(long id) {
		return getByKey(id);
	}
	
	public CompetitionResult findByCompetitionResultId(long competitionResultId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compResultId", competitionResultId));
		return (CompetitionResult) criteria.uniqueResult();
	}
	
	public void saveCompetitionResult(CompetitionResult competitionResult) {
		persist(competitionResult);
	}

	public void deleteCompetitionResultById(long compResultId) {
		Query query = getSession().createSQLQuery("delete from competition_result where compResultId = :compResultId");
		query.setLong("compResultId", compResultId);
		query.executeUpdate();
	}
	
	public List<CompetitionResult> findAllByCompetitionId(long compId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compId", compId));
		return (List<CompetitionResult>) criteria.list();
	}
	
	public List<CompetitionResult> findAllByCompIdAndTeamCode(long compId, String compTeamCode){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compId", compId));
		criteria.add(Restrictions.eq("compTeamCode", compTeamCode));
		return (List<CompetitionResult>) criteria.list();
	}
}
