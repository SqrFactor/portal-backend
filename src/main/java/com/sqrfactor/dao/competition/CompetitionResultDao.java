/**
 * 
 */
package com.sqrfactor.dao.competition;

import java.util.List;

import com.sqrfactor.model.competition.CompetitionResult;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionResultDao {

	CompetitionResult findByCompetitionResultId(long competitionResultId);
	
	void saveCompetitionResult(CompetitionResult competitionResult);

	void deleteCompetitionResultById(long competitionResultId);
	
	List<CompetitionResult> findAllByCompetitionId(long competitionId);
	
	List<CompetitionResult> findAllByCompIdAndTeamCode(long compId, String compTeamCode);

}
