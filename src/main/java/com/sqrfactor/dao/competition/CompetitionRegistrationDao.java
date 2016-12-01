/**
 * 
 */
package com.sqrfactor.dao.competition;

import com.sqrfactor.model.competition.CompetitionRegistration;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionRegistrationDao {
	
	CompetitionRegistration findByCompetitionRegistrationId(long competitionRegistrationId);
	
	void saveCompetitionRegistration(CompetitionRegistration competitionRegistration);

	void deleteCompetitionRegistrationById(long competitionRegistrationId);


}
