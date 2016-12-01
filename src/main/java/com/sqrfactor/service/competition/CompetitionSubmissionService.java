/**
 * 
 */
package com.sqrfactor.service.competition;

import com.sqrfactor.model.competition.CompetitionSubmission;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionSubmissionService {
	
	CompetitionSubmission findByCompetitionSubmissionId(long competitionSubmissionId);
	
	void saveCompetitionSubmission(CompetitionSubmission competitionSubmission);

	void updateCompetitionSubmission(CompetitionSubmission competitionSubmission);

	void deleteCompetitionSubmissionById(long competitionSubmissionId);

}
