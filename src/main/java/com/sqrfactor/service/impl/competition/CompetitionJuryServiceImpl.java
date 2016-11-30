/**
 * 
 */
package com.sqrfactor.service.impl.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.competition.CompetitionJuryDao;
import com.sqrfactor.model.competition.CompetitionJury;
import com.sqrfactor.service.competition.CompetitionJuryService;

/**
 * @author Angad Gill
 *
 */
@Service("competitionJuryService")
@Transactional
public class CompetitionJuryServiceImpl implements CompetitionJuryService{


	@Autowired
	private CompetitionJuryDao competitionJuryDao;

	/**
	 * Find CompetitionJury by id
	 */
	public CompetitionJury findByCompetitionJuryId(long competitionJuryId) {
		return competitionJuryDao.findByCompetitionJuryId(competitionJuryId);
	}
	
	/**
	 * Save CompetitionJury
	 */
	public void saveCompetitionJury(CompetitionJury competitionJury) {
		competitionJuryDao.saveCompetitionJury(competitionJury);
	}

	/**
	 * Update CompetitionJury
	 */
	public void updateCompetitionJury(CompetitionJury competitionJury) {
		CompetitionJury entity = competitionJuryDao.findByCompetitionJuryId(competitionJury.getCompJuryId());
		if (entity != null) {
			entity.setCompId(competitionJury.getCompId());
			entity.setJuryName(competitionJury.getJuryName());
			entity.setJuryFirmName(competitionJury.getJuryFirmName());
			entity.setJuryEmail(competitionJury.getJuryEmail());
			entity.setJuryContactNo(competitionJury.getJuryContactNo());
			entity.setJuryPic(competitionJury.getJuryPic());
			entity.setJuryDetails(competitionJury.getJuryDetails());
		}
	}

	@Override
	public void deleteCompetitionJuryById(long competitionJuryId) {
		competitionJuryDao.deleteCompetitionJuryById(competitionJuryId);
	}


}
