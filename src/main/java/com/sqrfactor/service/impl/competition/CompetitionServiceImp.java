/**
 * 
 */
package com.sqrfactor.service.impl.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.competition.CompetitionDao;
import com.sqrfactor.model.competition.Competition;
import com.sqrfactor.service.competition.CompetitionService;

/**
 * @author Angad Gill
 *
 */
@Service("competitionService")
@Transactional
public class CompetitionServiceImp implements CompetitionService{
	
	@Autowired
	private CompetitionDao competitionDao;

	/**
	 * Find Competition by id
	 */
	public Competition findByCompetitionId(long competitionId) {
		return competitionDao.findByCompetitionId(competitionId);
	}
	
	/**
	 * Save Competition
	 */
	public void saveCompetition(Competition competition) {
		competitionDao.saveCompetition(competition);
	}

	/**
	 * Update Competition
	 */
	public void updateCompetition(Competition competition) {
		Competition entity = competitionDao.findByCompetitionId(competition.getCompId());
		if (entity != null) {
			entity.setUserId(competition.getUserId());
			entity.setCompType(competition.getCompType());
			entity.setCompTitle(competition.getCompTitle());
			entity.setCompHeading(competition.getCompHeading());
			entity.setCompBrief(competition.getCompBrief());
			entity.setCompECriteria(competition.getCompECriteria());
			entity.setCompECriteriaOthers(competition.getCompECriteriaOthers());
			entity.setCoverPic(competition.getCoverPic());
			entity.setProfilePic(competition.getProfilePic());	
			entity.setCompSubRqrmts(competition.getCompSubRqrmts());
			entity.setCompFAQ(competition.getCompFAQ());
			entity.setCompStartDate(competition.getCompStartDate());
			entity.setCompEndDate(competition.getCompEndDate());
			entity.setCompSubEndDate(competition.getCompSubEndDate());
			entity.setCompResultDate(competition.getCompResultDate());
			entity.setCompDetails(competition.getCompDetails());
			entity.setCompBriefPath(competition.getCompBriefPath());
			entity.setCompFees(competition.getCompFees());
			entity.setCompCurrency(competition.getCompCurrency());
		}
	}

	@Override
	public void deleteCompetitionById(long competitionId) {
		competitionDao.deleteCompetitionById(competitionId);
	}
	
	@Override
	public List<Competition> findAllCompetitions(){
		return competitionDao.findAllCompetitions();
	}

}
