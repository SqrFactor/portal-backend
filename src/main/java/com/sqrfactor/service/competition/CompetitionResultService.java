/**
 * 
 */
package com.sqrfactor.service.competition;

import java.util.List;

import com.sqrfactor.model.competition.CompetitionResult;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionResultService {

	CompetitionResult findByCompetitionResultId(long competitionResultId);
	
	void saveCompetitionResult(CompetitionResult competitionResult);

	void updateCompetitionResult(CompetitionResult competitionResult);

	void deleteCompetitionResultById(long competitionResultId);
	
	public List<CompetitionResult> findAllByCompetitionId(long competitionId);
	
	public List<CompetitionResult> findAllByCompIdAndTeamCode(long compId, String compTeamCode);

}
