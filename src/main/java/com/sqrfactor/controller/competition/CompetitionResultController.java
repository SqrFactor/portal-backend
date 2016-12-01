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

import com.sqrfactor.model.competition.CompetitionResult;
import com.sqrfactor.service.competition.CompetitionResultService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class CompetitionResultController {

	@Autowired
	private CompetitionResultService competitionResultService;

	public CompetitionResultController() {
	}
	
	/**
	 * Get a Competition Result by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionresult/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<CompetitionResult> getCompetitionResultById(@PathVariable int id) {
		CompetitionResult competitionResult = competitionResultService.findByCompetitionResultId(id);
		
		if (competitionResult == null) {
			return new ResponseEntity<CompetitionResult>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CompetitionResult>(competitionResult, HttpStatus.OK);
	}

	/**
	 * Create Competition Result
	 * 
	 * @param message
	 */
	@RequestMapping(value = "/competitionresult/", method = RequestMethod.POST)
	public ResponseEntity<CompetitionResult> createCompetitionResult(@RequestBody CompetitionResult competitionResult) {

		competitionResultService.saveCompetitionResult(competitionResult);
		
		return new ResponseEntity<CompetitionResult>(competitionResult, HttpStatus.CREATED);
	}

	/**
	 * Update Competition Result
	 * 
	 * @param id
	 * @param competition
	 * @return
	 */
	@RequestMapping(value = "/competitionresult/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CompetitionResult> updateCompetitionResult(@PathVariable("id") int id,
			@RequestBody CompetitionResult competitionResult) {
		CompetitionResult currentCompetitionResult= competitionResultService.findByCompetitionResultId(id);

		if (currentCompetitionResult == null) {
			return new ResponseEntity<CompetitionResult>(HttpStatus.NOT_FOUND);
		}
		competitionResult.setCompResultId(id);
		competitionResultService.updateCompetitionResult(competitionResult);
		return new ResponseEntity<CompetitionResult>(competitionResult, HttpStatus.OK);
	}

	/**
	 * Delete Competition Result by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionresult/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CompetitionResult> deleteCompetitionResult(@PathVariable("id") int id) {
		CompetitionResult competitionResult = competitionResultService.findByCompetitionResultId(id);
		if (competitionResult == null) {
			return new ResponseEntity<CompetitionResult>(HttpStatus.NOT_FOUND);
		}

		competitionResultService.deleteCompetitionResultById(id);
		return new ResponseEntity<CompetitionResult>(HttpStatus.NO_CONTENT);
	}


}
