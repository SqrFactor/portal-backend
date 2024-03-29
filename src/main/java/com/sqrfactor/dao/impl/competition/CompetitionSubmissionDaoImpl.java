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
import com.sqrfactor.dao.competition.CompetitionSubmissionDao;
import com.sqrfactor.model.competition.CompetitionSubmission;

/**
 * @author Angad Gill
 *
 */
@Repository("competitionSubmissionDao")
public class CompetitionSubmissionDaoImpl extends AbstractDao<Long, CompetitionSubmission> implements CompetitionSubmissionDao{

	public CompetitionSubmission findById(long id) {
		return getByKey(id);
	}
	
	public CompetitionSubmission findByCompetitionSubmissionId(long competitionSubmissionId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compSubmissionId", competitionSubmissionId));
		return (CompetitionSubmission) criteria.uniqueResult();
	}
	
	public void saveCompetitionSubmission(CompetitionSubmission competitionSubmission) {
		persist(competitionSubmission);
	}

	public void deleteCompetitionSubmissionById(long compSubmissionId) {
		Query query = getSession().createSQLQuery("delete from competition_submission where compSubmissionId = :compSubmissionId");
		query.setLong("compSubmissionId", compSubmissionId);
		query.executeUpdate();
	}
	
	public List<CompetitionSubmission> findAllByCompetitionId(long competitionId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compId", competitionId));
		return (List<CompetitionSubmission>) criteria.list();
	}
	
	public List<CompetitionSubmission> findAllByCompIdAndTeamCode(long competitionId, String compTeamCode){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compId", competitionId));
		criteria.add(Restrictions.eq("compTeamCode", compTeamCode));
		return (List<CompetitionSubmission>) criteria.list();
	}

	public CompetitionSubmission findByCompTeamCode(String compTeamCode){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compTeamCode", compTeamCode));
		return (CompetitionSubmission) criteria.uniqueResult();
	}
}
