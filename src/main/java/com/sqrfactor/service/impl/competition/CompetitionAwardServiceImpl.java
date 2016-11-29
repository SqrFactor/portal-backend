/**
 * 
 */
package com.sqrfactor.service.impl.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.competition.CompetitionAwardDao;
import com.sqrfactor.model.competition.CompetitionAward;
import com.sqrfactor.service.competition.CompetitionAwardService;

/**
 * @author Angad Gill
 *
 */
@Service("competitionAwardService")
@Transactional
public class CompetitionAwardServiceImpl implements CompetitionAwardService{

	@Autowired
	private CompetitionAwardDao competitionAwardDao;

	/**
	 * Find CompetitionAward by id
	 */
	public CompetitionAward findByCompetitionAwardId(long competitionAwardId) {
		return competitionAwardDao.findByCompetitionAwardId(competitionAwardId);
	}
	
	/**
	 * Save CompetitionAward
	 */
	public void saveCompetitionAward(CompetitionAward competitionAward) {
		competitionAwardDao.saveCompetitionAward(competitionAward);
	}

	/**
	 * Update CompetitionAward
	 */
	public void updateCompetitionAward(CompetitionAward competitionAward) {
		CompetitionAward entity = competitionAwardDao.findByCompetitionAwardId(competitionAward.getCompAwardId());
		if (entity != null) {
			entity.setCompId(competitionAward.getCompId());
			entity.setAwardType(competitionAward.getAwardType());
			entity.setAwardPrice(competitionAward.getAwardPrice());
			entity.setAwardDetails(competitionAward.getAwardDetails());
		}
	}

	@Override
	public void deleteCompetitionAwardById(long competitionAwardId) {
		competitionAwardDao.deleteCompetitionAwardById(competitionAwardId);
	}

}
