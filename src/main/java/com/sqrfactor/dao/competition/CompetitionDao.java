/**
 * 
 */
package com.sqrfactor.dao.competition;

import com.sqrfactor.model.competition.Competition;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionDao {
	
	Competition findByCompetitionId(long competitionId);
	
	void saveCompetition(Competition competition);

	void deleteCompetitionById(long competitionId);
}
