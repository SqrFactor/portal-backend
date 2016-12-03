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
import com.sqrfactor.model.competition.CompetitionSubmission;
import com.sqrfactor.model.competition.EventFeed;
import com.sqrfactor.service.UserService;
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
			
			CompetitionSubmissionEventFeed competitionSubmissionEventFeed = new CompetitionSubmissionEventFeed(competitionSubmission, eventFeeds);
			competitionSubmissionEventFeeds.add(competitionSubmissionEventFeed);
		}
		return new ResponseEntity<List<CompetitionSubmissionEventFeed>>(competitionSubmissionEventFeeds, HttpStatus.OK);
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

		/**
		 * @param eventFeeds
		 */
		public CompetitionSubmissionEventFeed(CompetitionSubmission competitionSubmission, List<EventFeed> eventFeeds) {
			super(competitionSubmission);
			this.eventFeeds = eventFeeds;
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
		
	}

}
