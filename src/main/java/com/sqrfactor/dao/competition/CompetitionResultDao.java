/**
 * 
 */
package com.sqrfactor.dao.competition;

import com.sqrfactor.model.competition.CompetitionResult;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionResultDao {

	CompetitionResult findByCompetitionResultId(long competitionResultId);
	
	void saveCompetitionResult(CompetitionResult competitionResult);

	void deleteCompetitionResultById(long competitionResultId);

}
