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
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Connection;
import com.sqrfactor.model.EnrichedFeed;
import com.sqrfactor.model.Feed;
import com.sqrfactor.model.User;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.FeedService;
import com.sqrfactor.service.UserService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedFeedController {

	@Autowired
	private FeedService feedService;

	@Autowired
	private UserService userService;

	@Autowired
	private ConnectionService connectionService;
	
	/**
	 * Get a Feed by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enrichedfeed/userid/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EnrichedFeed>> getFeedById(@PathVariable int userId) {
		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();
		enrichedFeeds = getEnrichedFeedById(userId);
		
		if (enrichedFeeds.isEmpty()) {
			return new ResponseEntity<List<EnrichedFeed>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<EnrichedFeed>>(enrichedFeeds, HttpStatus.OK);
	}

	/**
	 * Get a Feed by Ref Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enrichedfeed/feedrefid/{feedRefId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EnrichedFeed>> getFeedByFeedRefId(@PathVariable int feedRefId) {
		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();

		List<Feed> feeds = feedService.findByFeedRefId(feedRefId);
		for (Feed feed : feeds) {
			User user = userService.findById(feed.getUserId());

			if (user == null) {
				continue;
			}

			String firstName = "";
			String lastName = "";
			if (user.getFirstName() != null) {
				firstName = user.getFirstName();
			}
			if (user.getLastName() != null) {
				lastName = user.getLastName();
			}

			String name = firstName + " " + lastName;
			EnrichedFeed enrichedFeed = new EnrichedFeed(feed, name);
			enrichedFeeds.add(enrichedFeed);
		}
		if (enrichedFeeds.isEmpty()) {
			return new ResponseEntity<List<EnrichedFeed>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<EnrichedFeed>>(enrichedFeeds, HttpStatus.OK);

	}
	
	/**
	 * Get a Feed by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enrichedfeed/conuserid/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EnrichedFeed>> getConnectionFeedById(@PathVariable int userId) {
		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();
		enrichedFeeds = getEnrichedFeedById(userId);
		
		List<Connection> connections = connectionService.findConnectionsBySourceId(userId);
		for(Connection connection : connections){
			enrichedFeeds.addAll(getEnrichedFeedById(connection.getDestinationId()));
		}
		
		if (enrichedFeeds.isEmpty()) {
			return new ResponseEntity<List<EnrichedFeed>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<EnrichedFeed>>(enrichedFeeds, HttpStatus.OK);
	}

	private List<EnrichedFeed> getEnrichedFeedById(long userId){
		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();
		List<Feed> feeds = feedService.findByUserId(userId);
		for (Feed feed : feeds) {
			User user = userService.findById(feed.getUserId());
			
			//Remove Comments from feed
			if(feed.getFeedRefId() != 0){
				continue;
			}
			
			if (user == null) {
				continue;
			}

			String firstName = "";
			String lastName = "";
			if (user.getFirstName() != null) {
				firstName = user.getFirstName();
			}
			if (user.getLastName() != null) {
				lastName = user.getLastName();
			}

			String name = firstName + " " + lastName;
			EnrichedFeed enrichedFeed = new EnrichedFeed(feed, name);
			enrichedFeeds.add(enrichedFeed);
		}
		return enrichedFeeds;
	}

}
