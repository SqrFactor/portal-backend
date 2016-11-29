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

import com.sqrfactor.model.competition.CompetitionAward;
import com.sqrfactor.service.competition.CompetitionAwardService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class CompetitionAwardController {

	@Autowired
	private CompetitionAwardService competitionAwardService;

	public CompetitionAwardController() {
	}
	
	/**
	 * Get a CompetitionAward by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionaward/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<CompetitionAward> getCompetitionById(@PathVariable int id) {
		CompetitionAward competitionAward = competitionAwardService.findByCompetitionAwardId(id);
		
		if (competitionAward == null) {
			return new ResponseEntity<CompetitionAward>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CompetitionAward>(competitionAward, HttpStatus.OK);
	}

	/**
	 * Create CompetitionAward
	 * 
	 * @param message
	 */
	@RequestMapping(value = "/competitionaward/", method = RequestMethod.POST)
	public ResponseEntity<CompetitionAward> createCompetitionAward(@RequestBody CompetitionAward competitionAward) {

		competitionAwardService.saveCompetitionAward(competitionAward);
		
		return new ResponseEntity<CompetitionAward>(competitionAward, HttpStatus.CREATED);
	}

	/**
	 * Update CompetitionAward
	 * 
	 * @param id
	 * @param competitionAward
	 * @return
	 */
	@RequestMapping(value = "/competitionaward/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CompetitionAward> updateCompetitionAward(@PathVariable("id") int id,
			@RequestBody CompetitionAward competitionAward) {
		CompetitionAward currentCompetitionAward= competitionAwardService.findByCompetitionAwardId(id);

		if (currentCompetitionAward == null) {
			return new ResponseEntity<CompetitionAward>(HttpStatus.NOT_FOUND);
		}
		competitionAward.setCompAwardId(id);
		competitionAwardService.updateCompetitionAward(competitionAward);
		return new ResponseEntity<CompetitionAward>(competitionAward, HttpStatus.OK);
	}

	/**
	 * Delete CompetitionAward by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionaward/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CompetitionAward> deleteCompetitionAward(@PathVariable("id") int id) {
		CompetitionAward competitionAward = competitionAwardService.findByCompetitionAwardId(id);
		if (competitionAward == null) {
			return new ResponseEntity<CompetitionAward>(HttpStatus.NOT_FOUND);
		}

		competitionAwardService.deleteCompetitionAwardById(id);
		return new ResponseEntity<CompetitionAward>(HttpStatus.NO_CONTENT);
	}

}
