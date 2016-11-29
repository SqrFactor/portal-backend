/**
 * 
 */
package com.sqrfactor.service.competition;

import com.sqrfactor.model.competition.CompetitionPartner;

/**
 * @author Angad Gill
 *
 */
public interface CompetitionPartnerService {

	CompetitionPartner findByCompetitionPartnerId(long competitionPartnerId);
	
	void saveCompetitionPartner(CompetitionPartner competitionPartner);

	void updateCompetitionPartner(CompetitionPartner competitionPartner);

	void deleteCompetitionPartnerById(long competitionPartnerId);

}
