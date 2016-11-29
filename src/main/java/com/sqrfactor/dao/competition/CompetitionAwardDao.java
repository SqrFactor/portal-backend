/**
 * 
 */
package com.sqrfactor.dao.competition;

import com.sqrfactor.model.competition.CompetitionAward;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionAwardDao {

	CompetitionAward findByCompetitionAwardId(long competitionAwardId);
	
	void saveCompetitionAward(CompetitionAward competitionAward);

	void deleteCompetitionAwardById(long competitionAwardId);

}
