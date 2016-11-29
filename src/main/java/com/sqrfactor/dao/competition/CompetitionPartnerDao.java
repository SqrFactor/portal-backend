/**
 * 
 */
package com.sqrfactor.dao.competition;

import com.sqrfactor.model.competition.CompetitionPartner;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionPartnerDao {
	
	CompetitionPartner findByCompetitionPartnerId(long competitionPartnerId);
	
	void saveCompetitionPartner(CompetitionPartner competitionPartner);

	void deleteCompetitionPartnerById(long competitionPartnerId);

}
