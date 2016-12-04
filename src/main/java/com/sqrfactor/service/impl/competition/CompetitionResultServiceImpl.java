/**
 * 
 */
package com.sqrfactor.service.impl.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.competition.CompetitionResultDao;
import com.sqrfactor.model.competition.CompetitionResult;
import com.sqrfactor.service.competition.CompetitionResultService;

/**
 * @author Angad Gill
 *
 */
@Service("competitionResultService")
@Transactional
public class CompetitionResultServiceImpl implements CompetitionResultService{

	@Autowired
	private CompetitionResultDao competitionResultDao;

	/**
	 * Find Competition Result by id
	 */
	public CompetitionResult findByCompetitionResultId(long competitionResultId) {
		return competitionResultDao.findByCompetitionResultId(competitionResultId);
	}
	
	/**
	 * Save Competition Result
	 */
	public void saveCompetitionResult(CompetitionResult competitionResult) {
		competitionResultDao.saveCompetitionResult(competitionResult);
	}

	/**
	 * Update Competition Result
	 */
	public void updateCompetitionResult(CompetitionResult competitionResult) {
		CompetitionResult entity = competitionResultDao.findByCompetitionResultId(competitionResult.getCompResultId());
		if (entity != null) {
			entity.setCompId(competitionResult.getCompId());
			entity.setCompTeamCode(competitionResult.getCompTeamCode());
			entity.setCompAwardId(competitionResult.getCompAwardId());
			entity.setSubmittedByUserId(competitionResult.getSubmittedByUserId());
			entity.setCreatedAt(competitionResult.getCreatedAt());
		}
	}

	@Override
	public void deleteCompetitionResultById(long competitionResultId) {
		competitionResultDao.deleteCompetitionResultById(competitionResultId);
	}
	
	@Override
	public List<CompetitionResult> findAllByCompetitionId(long competitionId){
		return competitionResultDao.findAllByCompetitionId(competitionId);
	}

}
