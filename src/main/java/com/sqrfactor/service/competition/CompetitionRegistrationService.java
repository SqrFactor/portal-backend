/**
 * 
 */
package com.sqrfactor.service.competition;

import java.util.List;

import com.sqrfactor.model.competition.CompetitionRegistration;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionRegistrationService {
	
	CompetitionRegistration findByCompetitionRegistrationId(long competitionRegistrationId);
	
	void saveCompetitionRegistration(CompetitionRegistration competitionRegistration);

	void updateCompetitionRegistration(CompetitionRegistration competitionRegistration);

	void deleteCompetitionRegistrationById(long competitionRegistrationId);
	
	List<CompetitionRegistration> findAllByCompetitionId(long competitionId);
	
	List<CompetitionRegistration> findAllByUserId(long userId);
	
	List<CompetitionRegistration> findByStartsWithTeamCode(String startingTeamCode);

	CompetitionRegistration findByCompIdAndUserId(long compId, long userId);
	
	List<CompetitionRegistration> findAllByCompetitionTeamCode(String compTeamCode);
	
	CompetitionRegistration findByCompIdTeamCodeAndUserRole(long compId, String compTeamCode, String compUserRole);
}
