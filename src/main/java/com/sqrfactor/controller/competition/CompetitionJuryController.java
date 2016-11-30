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

import com.sqrfactor.model.College;
import com.sqrfactor.model.competition.CompetitionJury;
import com.sqrfactor.service.competition.CompetitionJuryService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class CompetitionJuryController {

	@Autowired
	private CompetitionJuryService competitionJuryService;

	public CompetitionJuryController() {
	}
	
	/**
	 * Get a CompetitionJury by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionjury/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<CompetitionJury> getCompetitionJuryById(@PathVariable int id) {
		CompetitionJury competitionJury = competitionJuryService.findByCompetitionJuryId(id);
		
		if (competitionJury == null) {
			return new ResponseEntity<CompetitionJury>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CompetitionJury>(competitionJury, HttpStatus.OK);
	}
	
	/**
	 * Get all CompetitionJury by competitionId
	 * 
	 * @param competitionId
	 * @return
	 */
	@RequestMapping(value = "/competitionjury/compid/{competitionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<CompetitionJury>> getCompetitionJuryByCompetitionId(@PathVariable long competitionId) {
		List<CompetitionJury> competitionJuries = competitionJuryService.findAllByCompetitionId(competitionId);
		
		if (competitionJuries.isEmpty()) {
			return new ResponseEntity<List<CompetitionJury>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CompetitionJury>>(competitionJuries, HttpStatus.OK);
	}

	/**
	 * Create CompetitionJury
	 * 
	 * @param competitionJury
	 */
	@RequestMapping(value = "/competitionjury/", method = RequestMethod.POST)
	public ResponseEntity<CompetitionJury> createCompetitionJury(@RequestBody CompetitionJury competitionJury) {

		competitionJuryService.saveCompetitionJury(competitionJury);
		
		return new ResponseEntity<CompetitionJury>(competitionJury, HttpStatus.CREATED);
	}

	/**
	 * Update CompetitionJury
	 * 
	 * @param id
	 * @param competitionJury
	 * @return
	 */
	@RequestMapping(value = "/competitionjury/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CompetitionJury> updateCompetitionJury(@PathVariable("id") int id,
			@RequestBody CompetitionJury competitionJury) {
		CompetitionJury currentCompetitionJury= competitionJuryService.findByCompetitionJuryId(id);

		if (currentCompetitionJury == null) {
			return new ResponseEntity<CompetitionJury>(HttpStatus.NOT_FOUND);
		}
		competitionJury.setCompJuryId(id);
		competitionJuryService.updateCompetitionJury(competitionJury);
		return new ResponseEntity<CompetitionJury>(competitionJury, HttpStatus.OK);
	}

	/**
	 * Delete CompetitionJury by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionjury/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CompetitionJury> deleteCompetitionAward(@PathVariable("id") int id) {
		CompetitionJury competitionJury = competitionJuryService.findByCompetitionJuryId(id);
		if (competitionJury == null) {
			return new ResponseEntity<CompetitionJury>(HttpStatus.NOT_FOUND);
		}

		competitionJuryService.deleteCompetitionJuryById(id);
		return new ResponseEntity<CompetitionJury>(HttpStatus.NO_CONTENT);
	}
}
