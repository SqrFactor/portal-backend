/**
 * 
 */
package com.sqrfactor.service.impl.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.competition.CompetitionPartnerDao;
import com.sqrfactor.model.competition.CompetitionPartner;
import com.sqrfactor.service.competition.CompetitionPartnerService;

/**
 * @author Angad Gill
 *
 */
@Service("competitionPartnerService")
@Transactional
public class CompetitionPartnerServiceImpl implements CompetitionPartnerService {

	@Autowired
	private CompetitionPartnerDao competitionPartnerDao;

	/**
	 * Find CompetitionPartner by id
	 */
	public CompetitionPartner findByCompetitionPartnerId(long competitionPartnerId) {
		return competitionPartnerDao.findByCompetitionPartnerId(competitionPartnerId);
	}
	
	/**
	 * Save CompetitionPartner
	 */
	public void saveCompetitionPartner(CompetitionPartner competitionPartner) {
		competitionPartnerDao.saveCompetitionPartner(competitionPartner);
	}

	/**
	 * Update CompetitionPartner
	 */
	public void updateCompetitionPartner(CompetitionPartner competitionPartner) {
		CompetitionPartner entity = competitionPartnerDao.findByCompetitionPartnerId(competitionPartner.getCompPartnerId());
		if (entity != null) {
			entity.setCompId(competitionPartner.getCompId());
			entity.setPartnerName(competitionPartner.getPartnerName());
			entity.setPartnerWebsite(competitionPartner.getPartnerWebsite());
			entity.setPartnerEmail(competitionPartner.getPartnerEmail());
			entity.setPartnerContactNo(competitionPartner.getPartnerContactNo());
			entity.setPartnerPic(competitionPartner.getPartnerPic());
		}
	}

	@Override
	public void deleteCompetitionPartnerById(long competitionPartnerId) {
		competitionPartnerDao.deleteCompetitionPartnerById(competitionPartnerId);
	}

}
