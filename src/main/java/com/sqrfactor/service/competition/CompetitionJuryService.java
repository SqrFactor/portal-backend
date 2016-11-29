/**
 * 
 */
package com.sqrfactor.service.competition;

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

}
