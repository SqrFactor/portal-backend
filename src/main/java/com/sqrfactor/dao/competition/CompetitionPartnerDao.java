/**
 * 
 */
package com.sqrfactor.dao.competition;

import java.util.List;

import com.sqrfactor.model.competition.CompetitionPartner;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionPartnerDao {
	
	CompetitionPartner findByCompetitionPartnerId(long competitionPartnerId);
	
	void saveCompetitionPartner(CompetitionPartner competitionPartner);

	void deleteCompetitionPartnerById(long competitionPartnerId);

	List<CompetitionPartner> findAllByCompetitionId(long competitionId);	
}
