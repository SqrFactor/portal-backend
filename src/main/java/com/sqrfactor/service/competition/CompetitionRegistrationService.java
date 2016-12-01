/**
 * 
 */
package com.sqrfactor.service.competition;

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

}
