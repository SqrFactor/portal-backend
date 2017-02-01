/**
 * 
 */
package com.sqrfactor.service.impl.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.competition.CompetitionRegistrationDao;
import com.sqrfactor.model.competition.CompetitionRegistration;
import com.sqrfactor.service.competition.CompetitionRegistrationService;

/**
 * @author Angad Gill
 *
 */
@Service("competitionRegistrationService")
@Transactional
public class CompetitionRegistrationServiceImpl implements CompetitionRegistrationService{

	@Autowired
	private CompetitionRegistrationDao competitionRegistrationDao;

	/**
	 * Find Competition Registration by id
	 */
	public CompetitionRegistration findByCompetitionRegistrationId(long competitionRegistrationId) {
		return competitionRegistrationDao.findByCompetitionRegistrationId(competitionRegistrationId);
	}
	
	/**
	 * Save Competition Registration
	 */
	public void saveCompetitionRegistration(CompetitionRegistration competitionRegistration) {
		competitionRegistrationDao.saveCompetitionRegistration(competitionRegistration);
	}

	/**
	 * Update Competition Registration 
	 */
	public void updateCompetitionRegistration(CompetitionRegistration competitionRegistration) {
		CompetitionRegistration entity = competitionRegistrationDao.findByCompetitionRegistrationId(competitionRegistration.getCompRegistrationId());
		if (entity != null) {
			entity.setCompId(competitionRegistration.getCompId());
			entity.setUserId(competitionRegistration.getUserId());
			entity.setCompTeamCode(competitionRegistration.getCompTeamCode());
			entity.setCompUserRole(competitionRegistration.getCompUserRole());
		}
	}

	@Override
	public void deleteCompetitionRegistrationById(long competitionRegistrationId) {
		competitionRegistrationDao.deleteCompetitionRegistrationById(competitionRegistrationId);
	}
	
	@Override
	public List<CompetitionRegistration> findAllByCompetitionId(long competitionId){
		return competitionRegistrationDao.findAllByCompetitionId(competitionId);
	}
	
	@Override
	public List<CompetitionRegistration> findAllByUserId(long userId){
		return competitionRegistrationDao.findAllByUserId(userId);
	}
	
	@Override
	public List<CompetitionRegistration> findByStartsWithTeamCode(String startingTeamCode){
		return competitionRegistrationDao.findByStartsWithTeamCode(startingTeamCode);
	}
	
	@Override
	public CompetitionRegistration findByCompIdAndUserId(long compId, long userId){
		return competitionRegistrationDao.findByCompIdAndUserId(compId, userId);
	}
	
	@Override
	public List<CompetitionRegistration> findAllByCompetitionTeamCode(String compTeamCode){
		return competitionRegistrationDao.findAllByCompetitionTeamCode(compTeamCode);
	}
	
	@Override
	public CompetitionRegistration findByCompIdTeamCodeAndUserRole(long compId, String compTeamCode, String userRole){
		return competitionRegistrationDao.findByCompIdTeamCodeAndUserRole(compId, compTeamCode, userRole);
	}
}
