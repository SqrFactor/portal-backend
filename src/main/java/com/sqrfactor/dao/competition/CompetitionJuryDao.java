/**
 * 
 */
package com.sqrfactor.dao.competition;

import com.sqrfactor.model.competition.CompetitionJury;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionJuryDao {
	
	CompetitionJury findByCompetitionJuryId(long competitionJuryId);
	
	void saveCompetitionJury(CompetitionJury competition);

	void deleteCompetitionJuryById(long competitionJuryId);

}
