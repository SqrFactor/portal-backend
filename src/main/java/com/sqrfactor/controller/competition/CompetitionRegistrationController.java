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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.EnrichedEventFeed;
import com.sqrfactor.model.competition.CompetitionRegistration;
import com.sqrfactor.model.competition.CompetitionSubmission;
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
	 * Get all CompetitionSubmission by competitionId
	 * 
	 * @param competitionId
	 * @return
	 */
	@RequestMapping(value = "/competitionregistration/compid/{competitionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<CompetitionRegistration>> getCompetitionRegistrationByCompetitionId(@PathVariable long competitionId) {
		List<CompetitionRegistration> competitionRegistrations = competitionRegistrationService.findAllByCompetitionId(competitionId);
		
		if (competitionRegistrations.isEmpty()) {
			return new ResponseEntity<List<CompetitionRegistration>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CompetitionRegistration>>(competitionRegistrations, HttpStatus.OK);
	}
	
	/**
	 * Get all Competition by userId
	 * 
	 * @param competitionId
	 * @return
	 */
	@RequestMapping(value = "/competitionregistration/userid/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<CompetitionRegistration>> getCompetitionRegistrationByUserId(@PathVariable long userId) {
		List<CompetitionRegistration> competitionRegistrations = competitionRegistrationService.findAllByUserId(userId);
		
		if (competitionRegistrations.isEmpty()) {
			return new ResponseEntity<List<CompetitionRegistration>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CompetitionRegistration>>(competitionRegistrations, HttpStatus.OK);
	}
	
	/**
	 * Get a Competition Registration by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/competitionregistration/compteamcode", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getCompTeamCodeByCompIdAndUserId(@RequestParam("compId") long compId, @RequestParam("userId") long userId) {
		CompetitionRegistration competitionRegistration = competitionRegistrationService.findByCompIdAndUserId(compId, userId);
		
		if (competitionRegistration == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(competitionRegistration.getCompTeamCode(), HttpStatus.OK);
	}
	
	/**
	 * Create Competition Registration
	 * 
	 * @param competitionRegistration
	 */
	@RequestMapping(value = "/competitionregistration/", method = RequestMethod.POST)
	public ResponseEntity<CompetitionRegistration> createCompetitionRegistration(@RequestBody CompetitionRegistration competitionRegistration) {
		
		CompetitionRegistration alreadySaved = competitionRegistrationService.findByCompIdAndUserId(competitionRegistration.getCompId(), competitionRegistration.getUserId());
		if(alreadySaved != null){
			return new ResponseEntity<CompetitionRegistration>(alreadySaved, HttpStatus.CREATED);
		}
		
		//If user role is Leader generate the team code
		if(competitionRegistration.getCompUserRole().toLowerCase().equals("leader")){
			
			//REMOVE THIS CONDITION
			if(competitionRegistration.getCompId() != 1){
				String startingTeamCode = competitionRegistration.getCompId() + "SQRFACTOR";
				List<CompetitionRegistration> competitionRegistrations = competitionRegistrationService.findByStartsWithTeamCode(startingTeamCode);
				
				String compTeamCode = "";
				if(competitionRegistrations.isEmpty()){
					Integer newIncrement = 1;
					compTeamCode = startingTeamCode + newIncrement;
				}else{
					String currentIncrement = competitionRegistrations.get(competitionRegistrations.size() - 1).getCompTeamCode().substring(startingTeamCode.length());
					Integer newIncrement = Integer.parseInt(currentIncrement) + 1;
					compTeamCode = startingTeamCode + newIncrement;
				}

				//Set the team code
				competitionRegistration.setCompTeamCode(compTeamCode);
			}else{

				//Set the team code
				competitionRegistration.setCompTeamCode(competitionRegistration.getCompTeamCode());
			}
		}
		
		if(competitionRegistration.getCompUserRole().toLowerCase().equals("mentor")){
			CompetitionRegistration mentorAlreadySaved = competitionRegistrationService.findByCompIdTeamCodeAndUserRole(competitionRegistration.getCompId()
					, competitionRegistration.getCompTeamCode(), competitionRegistration.getCompUserRole());
			if(mentorAlreadySaved != null){
				mentorAlreadySaved.setUserId(competitionRegistration.getUserId());
				competitionRegistrationService.updateCompetitionRegistration(mentorAlreadySaved);
				return new ResponseEntity<CompetitionRegistration>(mentorAlreadySaved, HttpStatus.CREATED);
			}
		}
		
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
