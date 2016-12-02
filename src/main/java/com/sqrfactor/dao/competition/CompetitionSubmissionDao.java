/**
 * 
 */
package com.sqrfactor.dao.competition;

import java.util.List;

import com.sqrfactor.model.competition.CompetitionSubmission;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionSubmissionDao {
	
	CompetitionSubmission findByCompetitionSubmissionId(long competitionSubmissionId);
	
	void saveCompetitionSubmission(CompetitionSubmission competitionSubmission);

	void deleteCompetitionSubmissionById(long competitionSubmissionId);

	List<CompetitionSubmission> findAllByCompetitionId(long competitionId);
}
