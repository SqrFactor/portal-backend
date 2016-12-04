/**
 * 
 */
package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.EnrichedEventFeed;
import com.sqrfactor.model.User;
import com.sqrfactor.model.competition.CompetitionAward;
import com.sqrfactor.model.competition.CompetitionRegistration;
import com.sqrfactor.model.competition.CompetitionResult;
import com.sqrfactor.model.competition.CompetitionSubmission;
import com.sqrfactor.model.competition.EnrichedCompetitionRegistration;
import com.sqrfactor.model.competition.EventFeed;
import com.sqrfactor.service.UserService;
import com.sqrfactor.service.competition.CompetitionAwardService;
import com.sqrfactor.service.competition.CompetitionRegistrationService;
import com.sqrfactor.service.competition.CompetitionResultService;
import com.sqrfactor.service.competition.CompetitionSubmissionService;
import com.sqrfactor.service.competition.EventFeedService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedEventFeedController {

	@Autowired
	private EventFeedService eventFeedService;
	
	@Autowired
	private CompetitionSubmissionService competitionSubmissionService;
	
	@Autowired
	private CompetitionResultService competitionResultService;
	
	@Autowired
	private CompetitionRegistrationService competitionRegistrationService;
	
	@Autowired
	private CompetitionAwardService competitionAwardService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Pull all the event event feeds for eventType and eventTypeId 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/eventfeed/enriched/eventtype", method = RequestMethod.GET)
	public ResponseEntity<List<EnrichedEventFeed>> getByEventTypeAndEventTypeId(@RequestParam("eventType") String eventType, @RequestParam("eventTypeId") long eventTypeId) {
		List<EnrichedEventFeed> enrichedEventFeeds = new ArrayList<>();
		List<EventFeed> eventFeeds = eventFeedService.findAllByEventTypeAndEventTypeId(eventType, eventTypeId);
		
		for(EventFeed eventFeed : eventFeeds){
			User user = userService.findById(eventFeed.getUserId());
			
			if (user == null) {
				continue;
			}

			String name = getName(user);
			String profilePicPath = getProfilePicPath(user);
			
			EnrichedEventFeed enrichedEventFeed = new EnrichedEventFeed(eventFeed, name, profilePicPath);
			enrichedEventFeeds.add(enrichedEventFeed);
		}
		
		if(eventFeeds.isEmpty()){
			return new ResponseEntity<List<EnrichedEventFeed>>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<EnrichedEventFeed>>(enrichedEventFeeds, HttpStatus.OK);
		}
	}
	
	/**
	 * Find all event feeds for submissions 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/eventfeed/enriched/submissions", method = RequestMethod.GET)
	public ResponseEntity<List<CompetitionSubmissionEventFeed>> getAllSubmissionsByCompId(@RequestParam("competitionId") long competitionId) {
		List<CompetitionSubmissionEventFeed> competitionSubmissionEventFeeds = new ArrayList<>();
		List<CompetitionSubmission> competitionSubmissions = competitionSubmissionService.findAllByCompetitionId(competitionId);
		
		for(CompetitionSubmission competitionSubmission : competitionSubmissions){
			String eventType = "Submission";
			long eventTypeId = competitionSubmission.getCompSubmissionId();
			List<EventFeed> eventFeeds = eventFeedService.findAllByEventTypeAndEventTypeId(eventType, eventTypeId);
			
			List<CompetitionRegistration> competitionRegistrations = competitionRegistrationService.findAllByCompetitionTeamCode(competitionSubmission.getCompTeamCode());
			List<EnrichedCompetitionRegistration> enrichedCompetitionRegistrations = new ArrayList<>();
			
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
			 
			CompetitionSubmissionEventFeed competitionSubmissionEventFeed = new CompetitionSubmissionEventFeed(competitionSubmission, eventFeeds, enrichedCompetitionRegistrations);
			competitionSubmissionEventFeeds.add(competitionSubmissionEventFeed);
		}
		return new ResponseEntity<List<CompetitionSubmissionEventFeed>>(competitionSubmissionEventFeeds, HttpStatus.OK);
	}
	
	/**
	 * Find all event feeds for submissions 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/eventfeed/enriched/results", method = RequestMethod.GET)
	public ResponseEntity<List<CompetitionResultEventFeed>> getAllResultsByCompId(@RequestParam("competitionId") long competitionId) {
		List<CompetitionResultEventFeed> competitionResultEventFeeds = new ArrayList<>();
		List<CompetitionResult> competitionResults = competitionResultService.findAllByCompetitionId(competitionId);
		
		for(CompetitionResult competitionResult : competitionResults){
			String eventType = "Result";
			long eventTypeId = competitionResult.getCompResultId();
			List<EventFeed> eventFeeds = eventFeedService.findAllByEventTypeAndEventTypeId(eventType, eventTypeId);
			
			List<CompetitionRegistration> competitionRegistrations = competitionRegistrationService.findAllByCompetitionTeamCode(competitionResult.getCompTeamCode());
			List<EnrichedCompetitionRegistration> enrichedCompetitionRegistrations = new ArrayList<>();
			
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
			
			CompetitionAward competitionAward = competitionAwardService.findByCompetitionAwardId(competitionResult.getCompAwardId());
			
			CompetitionSubmission competitionSubmission = competitionSubmissionService.findByCompTeamCode(competitionResult.getCompTeamCode());
			
			CompetitionResultEventFeed competitionResultEventFeed = new CompetitionResultEventFeed(competitionResult, competitionAward.getAwardType(), competitionSubmission.getFilePath(), eventFeeds, enrichedCompetitionRegistrations);
			competitionResultEventFeeds.add(competitionResultEventFeed);
		}
		return new ResponseEntity<List<CompetitionResultEventFeed>>(competitionResultEventFeeds, HttpStatus.OK);
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
	
	private String getProfilePicPath(User user){
		String profilePicPath = "";
		
		if(user.getProfilePicPath() != null){
			profilePicPath = user.getProfilePicPath();
		}
		return profilePicPath;
	}
	
	private class CompetitionSubmissionEventFeed extends CompetitionSubmission{
		
		private List<EventFeed> eventFeeds = new ArrayList<>();
		private List<EnrichedCompetitionRegistration> enrichedCompetitionRegistrations = new ArrayList<>();

		/**
		 * @param eventFeeds
		 */
		public CompetitionSubmissionEventFeed(CompetitionSubmission competitionSubmission, List<EventFeed> eventFeeds, List<EnrichedCompetitionRegistration> enrichedCompetitionRegistrations) {
			super(competitionSubmission);
			this.eventFeeds = eventFeeds;
			this.enrichedCompetitionRegistrations = enrichedCompetitionRegistrations;
		}
		
		/**
		 * @return the eventFeeds
		 */
		public List<EventFeed> getEventFeeds() {
			return eventFeeds;
		}


		/**
		 * @param eventFeeds the eventFeeds to set
		 */
		public void setEventFeeds(List<EventFeed> eventFeeds) {
			this.eventFeeds = eventFeeds;
		}

		/**
		 * @return the enrichedCompetitionRegistrations
		 */
		public List<EnrichedCompetitionRegistration> getEnrichedCompetitionRegistrations() {
			return enrichedCompetitionRegistrations;
		}

		/**
		 * @param enrichedCompetitionRegistrations the enrichedCompetitionRegistrations to set
		 */
		public void setEnrichedCompetitionRegistrations(
				List<EnrichedCompetitionRegistration> enrichedCompetitionRegistrations) {
			this.enrichedCompetitionRegistrations = enrichedCompetitionRegistrations;
		}

	}

	private class CompetitionResultEventFeed extends CompetitionResult{
		
		private List<EventFeed> eventFeeds = new ArrayList<>();
		private List<EnrichedCompetitionRegistration> enrichedCompetitionRegistrations = new ArrayList<>();
		private String compAwardType;
		private String filePath;
		
		/**
		 * @param eventFeeds
		 */
		public CompetitionResultEventFeed(CompetitionResult competitionResult, String compAwardType, String filePath, List<EventFeed> eventFeeds, List<EnrichedCompetitionRegistration> enrichedCompetitionRegistrations) {
			super(competitionResult);
			this.compAwardType = compAwardType;
			this.filePath = filePath;
			this.eventFeeds = eventFeeds;
			this.enrichedCompetitionRegistrations = enrichedCompetitionRegistrations;
		}
		
		/**
		 * @return the eventFeeds
		 */
		public List<EventFeed> getEventFeeds() {
			return eventFeeds;
		}


		/**
		 * @param eventFeeds the eventFeeds to set
		 */
		public void setEventFeeds(List<EventFeed> eventFeeds) {
			this.eventFeeds = eventFeeds;
		}
		
		/**
		 * @return the enrichedCompetitionRegistrations
		 */
		public List<EnrichedCompetitionRegistration> getEnrichedCompetitionRegistrations() {
			return enrichedCompetitionRegistrations;
		}

		/**
		 * @param enrichedCompetitionRegistrations the enrichedCompetitionRegistrations to set
		 */
		public void setEnrichedCompetitionRegistrations(
				List<EnrichedCompetitionRegistration> enrichedCompetitionRegistrations) {
			this.enrichedCompetitionRegistrations = enrichedCompetitionRegistrations;
		}

		/**
		 * @return the compAwardType
		 */
		public String getCompAwardType() {
			return compAwardType;
		}

		/**
		 * @param compAwardType the compAwardType to set
		 */
		public void setCompAwardType(String compAwardType) {
			this.compAwardType = compAwardType;
		}

		/**
		 * @return the filePath
		 */
		public String getFilePath() {
			return filePath;
		}

		/**
		 * @param filePath the filePath to set
		 */
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
				
	}
}
