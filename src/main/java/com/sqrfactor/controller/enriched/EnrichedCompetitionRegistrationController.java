/**
 * 
 */
package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.User;
import com.sqrfactor.model.competition.CompetitionRegistration;
import com.sqrfactor.model.competition.EnrichedCompetitionRegistration;
import com.sqrfactor.service.UserService;
import com.sqrfactor.service.competition.CompetitionRegistrationService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedCompetitionRegistrationController {

	@Autowired
	private CompetitionRegistrationService competitionRegistrationService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Get all CompetitionRegistration by competitionId
	 * 
	 * @param competitionId
	 * @return
	 */
	@RequestMapping(value = "/competitionregistration/enriched/compteamcode/{compTeamCode}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EnrichedCompetitionRegistration>> getCompetitionRegistrationByCompetitionId(@PathVariable String compTeamCode) {
		List<EnrichedCompetitionRegistration> enrichedCompetitionRegistrations = new ArrayList<>(); 
		List<CompetitionRegistration> competitionRegistrations = competitionRegistrationService.findAllByCompetitionTeamCode(compTeamCode);
		
		if (competitionRegistrations.isEmpty()) {
			return new ResponseEntity<List<EnrichedCompetitionRegistration>>(HttpStatus.NOT_FOUND);
		}
		
		//Retrive names of each user
		for(CompetitionRegistration competitionRegistration : competitionRegistrations){
			User user = userService.findById(competitionRegistration.getUserId());
			
			if (user == null) {
				continue;
			}

			String name = getName(user);
			EnrichedCompetitionRegistration enrichedCompetitionRegistration = new EnrichedCompetitionRegistration(competitionRegistration, name);
			enrichedCompetitionRegistrations.add(enrichedCompetitionRegistration);
		}
		
		return new ResponseEntity<List<EnrichedCompetitionRegistration>>(enrichedCompetitionRegistrations, HttpStatus.OK);
	}
	
	private String getName(User user){
		String firstName = "";
		String lastName = "";
		if (user.getFirstName() != null) {
			firstName = user.getFirstName();
		}
		if (user.getLastName() != null) {
			lastName = user.getLastName();
		}
		String name = firstName + " " + lastName;
		
		return name;
	}
}
