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
import com.sqrfactor.model.competition.EventFeed;
import com.sqrfactor.service.UserService;
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

}
