/**
 * 
 */
package com.sqrfactor.service.competition;

import com.sqrfactor.model.competition.Competition;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionService {
	
	Competition findByCompetitionId(long competitionId);
	
	void saveCompetition(Competition competition);

	void updateCompetition(Competition competition);

	void deleteCompetitionById(long competitionId);
}
