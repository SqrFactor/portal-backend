/**
 * 
 */
package com.sqrfactor.service.impl.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.competition.CompetitionSubmissionDao;
import com.sqrfactor.model.competition.CompetitionSubmission;
import com.sqrfactor.service.competition.CompetitionSubmissionService;

/**
 * @author Angad Gill
 *
 */
@Service("competitionSubmissionService")
@Transactional
public class CompetitionSubmissionServiceImpl implements CompetitionSubmissionService{

	@Autowired
	private CompetitionSubmissionDao competitionSubmissionDao;

	/**
	 * Find Competition Submission by id
	 */
	public CompetitionSubmission findByCompetitionSubmissionId(long competitionSubmissionId) {
		return competitionSubmissionDao.findByCompetitionSubmissionId(competitionSubmissionId);
	}
	
	/**
	 * Save Competition Submission
	 */
	public void saveCompetitionSubmission(CompetitionSubmission competitionSubmission) {
		competitionSubmissionDao.saveCompetitionSubmission(competitionSubmission);
	}

	/**
	 * Update Competition Submission
	 */
	public void updateCompetitionSubmission(CompetitionSubmission competitionSubmission) {
		CompetitionSubmission entity = competitionSubmissionDao.findByCompetitionSubmissionId(competitionSubmission.getCompSubmissionId());
		if (entity != null) {
			entity.setCompId(competitionSubmission.getCompId());
			entity.setCompTeamCode(competitionSubmission.getCompTeamCode());
			entity.setFilePath(competitionSubmission.getFilePath());
			entity.setSubmittedByUserId(competitionSubmission.getSubmittedByUserId());
			entity.setCreatedAt(competitionSubmission.getCreatedAt());
		}
	}

	@Override
	public void deleteCompetitionSubmissionById(long competitionSubmissionId) {
		competitionSubmissionDao.deleteCompetitionSubmissionById(competitionSubmissionId);
	}
	
	@Override
	public List<CompetitionSubmission> findAllByCompetitionId(long competitionId) {
		return competitionSubmissionDao.findAllByCompetitionId(competitionId);
	}
	
	@Override
	public List<CompetitionSubmission> findAllByCompIdAndTeamCode(long competitionId, String compTeamCode) {
		return competitionSubmissionDao.findAllByCompIdAndTeamCode(competitionId, compTeamCode);
	}
	
	@Override
	public CompetitionSubmission findByCompTeamCode(String compTeamCode){
		return competitionSubmissionDao.findByCompTeamCode(compTeamCode);
	}
}
