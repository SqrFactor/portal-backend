/**
 * 
 */
package com.sqrfactor.service.competition;

import com.sqrfactor.model.competition.CompetitionAward;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionAwardService {

	CompetitionAward findByCompetitionAwardId(long competitionAwardId);
	
	void saveCompetitionAward(CompetitionAward competitionAward);

	void updateCompetitionAward(CompetitionAward competitionAward);

	void deleteCompetitionAwardById(long competitionAwardId);

}
