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

import com.sqrfactor.model.competition.Competition;
import com.sqrfactor.service.competition.CompetitionService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class CompetitionController {
	
	@Autowired
	private CompetitionService competitionService;

	public CompetitionController() {
	}
	
	/**
	 * Get a Competition by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competition/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Competition> getCompetitionById(@PathVariable int id) {
		Competition competition = competitionService.findByCompetitionId(id);
		
		if (competition == null) {
			return new ResponseEntity<Competition>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Competition>(competition, HttpStatus.OK);
	}

	/**
	 * Create Competition
	 * 
	 * @param message
	 */
	@RequestMapping(value = "/competition/", method = RequestMethod.POST)
	public ResponseEntity<Competition> createCompetition(@RequestBody Competition competition) {

		competitionService.saveCompetition(competition);
		
		return new ResponseEntity<Competition>(competition, HttpStatus.CREATED);
	}

	/**
	 * Update Competition
	 * 
	 * @param id
	 * @param competition
	 * @return
	 */
	@RequestMapping(value = "/competition/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Competition> updateCompetition(@PathVariable("id") int id,
			@RequestBody Competition competition) {
		Competition currentCompetition= competitionService.findByCompetitionId(id);

		if (currentCompetition == null) {
			return new ResponseEntity<Competition>(HttpStatus.NOT_FOUND);
		}
		competition.setCompId(id);
		competitionService.updateCompetition(competition);
		return new ResponseEntity<Competition>(competition, HttpStatus.OK);
	}

	/**
	 * Delete Competition by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competition/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Competition> deleteCompetition(@PathVariable("id") int id) {
		Competition competition = competitionService.findByCompetitionId(id);
		if (competition == null) {
			return new ResponseEntity<Competition>(HttpStatus.NOT_FOUND);
		}

		competitionService.deleteCompetitionById(id);
		return new ResponseEntity<Competition>(HttpStatus.NO_CONTENT);
	}

}
