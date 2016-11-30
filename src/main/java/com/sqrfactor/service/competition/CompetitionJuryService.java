/**
 * 
 */
package com.sqrfactor.service.competition;

import java.util.List;

import com.sqrfactor.model.competition.CompetitionJury;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionJuryService {

	CompetitionJury findByCompetitionJuryId(long competitionJuryId);
	
	void saveCompetitionJury(CompetitionJury competitionJury);

	void updateCompetitionJury(CompetitionJury competitionJury);

	void deleteCompetitionJuryById(long competitionJuryId);
	
	List<CompetitionJury> findAllByCompetitionId(long competitionId);

}
