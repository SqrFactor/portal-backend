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

import com.sqrfactor.model.competition.CompetitionSubmission;
import com.sqrfactor.service.competition.CompetitionSubmissionService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class CompetitionSubmissionController {

	@Autowired
	private CompetitionSubmissionService competitionSubmissionService;

	public CompetitionSubmissionController() {
	}
	
	/**
	 * Get a Competition Submission by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionsubmission/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<CompetitionSubmission> getCompetitionSubmissionById(@PathVariable int id) {
		CompetitionSubmission competitionSubmission = competitionSubmissionService.findByCompetitionSubmissionId(id);
		
		if (competitionSubmission == null) {
			return new ResponseEntity<CompetitionSubmission>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CompetitionSubmission>(competitionSubmission, HttpStatus.OK);
	}

	/**
	 * Create Competition Submission
	 * 
	 * @param message
	 */
	@RequestMapping(value = "/competitionsubmission/", method = RequestMethod.POST)
	public ResponseEntity<CompetitionSubmission> createCompetitionSubmission(@RequestBody CompetitionSubmission competitionSubmission) {

		competitionSubmissionService.saveCompetitionSubmission(competitionSubmission);
		
		return new ResponseEntity<CompetitionSubmission>(competitionSubmission, HttpStatus.CREATED);
	}

	/**
	 * Update Competition Submission
	 * 
	 * @param id
	 * @param competition
	 * @return
	 */
	@RequestMapping(value = "/competitionsubmission/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CompetitionSubmission> updateCompetitionSubmission(@PathVariable("id") int id,
			@RequestBody CompetitionSubmission competitionSubmission) {
		CompetitionSubmission currentCompetitionSubmission= competitionSubmissionService.findByCompetitionSubmissionId(id);

		if (currentCompetitionSubmission == null) {
			return new ResponseEntity<CompetitionSubmission>(HttpStatus.NOT_FOUND);
		}
		competitionSubmission.setCompSubmissionId(id);
		competitionSubmissionService.updateCompetitionSubmission(competitionSubmission);
		return new ResponseEntity<CompetitionSubmission>(competitionSubmission, HttpStatus.OK);
	}

	/**
	 * Delete Competition Submission by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionsubmission/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CompetitionSubmission> deleteCompetitionSubmission(@PathVariable("id") int id) {
		CompetitionSubmission competitionSubmission = competitionSubmissionService.findByCompetitionSubmissionId(id);
		if (competitionSubmission == null) {
			return new ResponseEntity<CompetitionSubmission>(HttpStatus.NOT_FOUND);
		}

		competitionSubmissionService.deleteCompetitionSubmissionById(id);
		return new ResponseEntity<CompetitionSubmission>(HttpStatus.NO_CONTENT);
	}


}
