/**
 * 
 */
package com.sqrfactor.controller.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.competition.CompetitionRegistration;
import com.sqrfactor.service.competition.CompetitionRegistrationService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class CompetitionRegistrationController {

	@Autowired
	private CompetitionRegistrationService competitionRegistrationService;

	public CompetitionRegistrationController() {
	}
	
	/**
	 * Get a Competition Registration by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionregistration/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<CompetitionRegistration> getCompetitionRegistrationById(@PathVariable int id) {
		CompetitionRegistration competitionRegistration = competitionRegistrationService.findByCompetitionRegistrationId(id);
		
		if (competitionRegistration == null) {
			return new ResponseEntity<CompetitionRegistration>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CompetitionRegistration>(competitionRegistration, HttpStatus.OK);
	}

	/**
	 * Create Competition Registration
	 * 
	 * @param competitionRegistration
	 */
	@RequestMapping(value = "/competitionregistration/", method = RequestMethod.POST)
	public ResponseEntity<CompetitionRegistration> createCompetitionRegistration(@RequestBody CompetitionRegistration competitionRegistration) {

		competitionRegistrationService.saveCompetitionRegistration(competitionRegistration);
		
		return new ResponseEntity<CompetitionRegistration>(competitionRegistration, HttpStatus.CREATED);
	}

	/**
	 * Update Competition Registration
	 * 
	 * @param id
	 * @param competitionRegistration
	 * @return
	 */
	@RequestMapping(value = "/competitionregistration/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CompetitionRegistration> updateCompetitionRegistration(@PathVariable("id") int id,
			@RequestBody CompetitionRegistration competitionRegistration) {
		CompetitionRegistration currentCompetitionRegistration= competitionRegistrationService.findByCompetitionRegistrationId(id);

		if (currentCompetitionRegistration == null) {
			return new ResponseEntity<CompetitionRegistration>(HttpStatus.NOT_FOUND);
		}
		competitionRegistration.setCompRegistrationId(id);
		competitionRegistrationService.updateCompetitionRegistration(competitionRegistration);
		return new ResponseEntity<CompetitionRegistration>(competitionRegistration, HttpStatus.OK);
	}

	/**
	 * Delete Competition Registration by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionregistration/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CompetitionRegistration> deleteCompetitionRegistration(@PathVariable("id") int id) {
		CompetitionRegistration competitionRegistration = competitionRegistrationService.findByCompetitionRegistrationId(id);
		if (competitionRegistration == null) {
			return new ResponseEntity<CompetitionRegistration>(HttpStatus.NOT_FOUND);
		}

		competitionRegistrationService.deleteCompetitionRegistrationById(id);
		return new ResponseEntity<CompetitionRegistration>(HttpStatus.NO_CONTENT);
	}


}
