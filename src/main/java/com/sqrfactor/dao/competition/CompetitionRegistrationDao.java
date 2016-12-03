/**
 * 
 */
package com.sqrfactor.dao.competition;

import java.util.List;

import com.sqrfactor.model.competition.CompetitionRegistration;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionRegistrationDao {
	
	CompetitionRegistration findByCompetitionRegistrationId(long competitionRegistrationId);
	
	void saveCompetitionRegistration(CompetitionRegistration competitionRegistration);

	void deleteCompetitionRegistrationById(long competitionRegistrationId);
	
	List<CompetitionRegistration> findAllByCompetitionId(long competitionId);
	
	List<CompetitionRegistration> findByStartsWithTeamCode(String startingTeamCode);

	CompetitionRegistration findByCompIdAndUserId(long compId, long userId);
	
	List<CompetitionRegistration> findAllByCompetitionTeamCode(String compTeamCode);
}
