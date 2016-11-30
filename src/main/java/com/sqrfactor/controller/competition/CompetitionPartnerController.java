/**
 * 
 */
package com.sqrfactor.controller.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.competition.CompetitionPartner;
import com.sqrfactor.service.competition.CompetitionPartnerService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class CompetitionPartnerController {

	@Autowired
	private CompetitionPartnerService competitionPartnerService;

	public CompetitionPartnerController() {
	}
	
	/**
	 * Get a CompetitionPartner by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionpartner/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<CompetitionPartner> getCompetitionPartnerById(@PathVariable int id) {
		CompetitionPartner competitionPartner = competitionPartnerService.findByCompetitionPartnerId(id);
		
		if (competitionPartner == null) {
			return new ResponseEntity<CompetitionPartner>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CompetitionPartner>(competitionPartner, HttpStatus.OK);
	}
	
	/**
	 * Get all CompetitionPartner by competitionId
	 * 
	 * @param competitionId
	 * @return
	 */
	@RequestMapping(value = "/competitionpartner/compid/{competitionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<CompetitionPartner>> getCompetitionPartnerByCompetitionId(@PathVariable long competitionId) {
		List<CompetitionPartner> competitionPartners = competitionPartnerService.findAllByCompetitionId(competitionId);
		
		if (competitionPartners.isEmpty()) {
			return new ResponseEntity<List<CompetitionPartner>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CompetitionPartner>>(competitionPartners, HttpStatus.OK);
	}

	/**
	 * Create CompetitionPartner
	 * 
	 * @param competitionJury
	 */
	@RequestMapping(value = "/competitionpartner/", method = RequestMethod.POST)
	public ResponseEntity<CompetitionPartner> createCompetitionPartner(@RequestBody CompetitionPartner competitionPartner) {

		competitionPartnerService.saveCompetitionPartner(competitionPartner);
		
		return new ResponseEntity<CompetitionPartner>(competitionPartner, HttpStatus.CREATED);
	}

	/**
	 * Update CompetitionPartner
	 * 
	 * @param id
	 * @param competitionJury
	 * @return
	 */
	@RequestMapping(value = "/competitionpartner/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CompetitionPartner> updateCompetitionPartner(@PathVariable("id") int id,
			@RequestBody CompetitionPartner competitionPartner) {
		CompetitionPartner currentCompetitionPartner= competitionPartnerService.findByCompetitionPartnerId(id);

		if (currentCompetitionPartner == null) {
			return new ResponseEntity<CompetitionPartner>(HttpStatus.NOT_FOUND);
		}
		competitionPartner.setCompPartnerId(id);
		competitionPartnerService.updateCompetitionPartner(competitionPartner);
		return new ResponseEntity<CompetitionPartner>(competitionPartner, HttpStatus.OK);
	}

	/**
	 * Delete CompetitionPartner by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionpartner/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CompetitionPartner> deleteCompetitionPartner(@PathVariable("id") int id) {
		CompetitionPartner competitionPartner = competitionPartnerService.findByCompetitionPartnerId(id);
		if (competitionPartner == null) {
			return new ResponseEntity<CompetitionPartner>(HttpStatus.NOT_FOUND);
		}

		competitionPartnerService.deleteCompetitionPartnerById(id);
		return new ResponseEntity<CompetitionPartner>(HttpStatus.NO_CONTENT);
	}

}
