/**
 * 
 */
package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping(value = "/enrichedfeed/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<EnrichedFeed> retrieveEnrichedFeedById(@PathVariable int id) {
		Feed feed = feedService.findById(id);
		
		if (feed == null) {
			return new ResponseEntity<EnrichedFeed>(HttpStatus.NOT_FOUND);
		}
		
		User user = userService.findById(feed.getUserId());

		if (user == null) {
			return new ResponseEntity<EnrichedFeed>(HttpStatus.NOT_FOUND);
		}

		String name = getName(user);
		String profilePicPath = getProfilePicPath(user);
		
		long refUserId = 0;
		String refName = "";
		String refProfilePicPath = "";
		
		Feed refFeed = feedService.findById(feed.getFeedRefId());
		if(refFeed != null){
			User refUser = userService.findById(refFeed.getUserId());
			
			if(refUser != null){
			
				refUserId = refUser.getUserId();
				refName = getName(refUser);
				refProfilePicPath = getProfilePicPath(refUser);
			}
		}
		
		EnrichedFeed enrichedFeed = new EnrichedFeed(feed, name, profilePicPath, refUserId, refName, refProfilePicPath);
		return new ResponseEntity<EnrichedFeed>(enrichedFeed, HttpStatus.OK);
	}
	
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

			String name = getName(user);
			String profilePicPath = getProfilePicPath(user);
			
			long refUserId = 0;
			String refName = "";
			String refProfilePicPath = "";
			
			Feed refFeed = feedService.findById(feed.getFeedRefId());
			if(refFeed != null){
				User refUser = userService.findById(refFeed.getUserId());
				
				if(refUser != null){
				
					refUserId = refUser.getUserId();
					refName = getName(refUser);
					refProfilePicPath = getProfilePicPath(refUser);
				}
			}
			
			EnrichedFeed enrichedFeed = new EnrichedFeed(feed, name, profilePicPath, refUserId, refName, refProfilePicPath);
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
	public ResponseEntity<List<EnrichedFeed>> getConnectionFeedById(@PathVariable int userId, @RequestParam(value = "isRed", required = false, defaultValue = "false") boolean isRed) {
		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();
		enrichedFeeds = getEnrichedFeedById(userId);
		
		List<Connection> connections = connectionService.findConnectionsBySourceId(userId);
		for(Connection connection : connections){
			enrichedFeeds.addAll(getEnrichedFeedById(connection.getDestinationId()));
		}
		
		if (enrichedFeeds.isEmpty()) {
			return new ResponseEntity<List<EnrichedFeed>>(HttpStatus.NOT_FOUND);
		}
		
		//Sort Based on likes
		if(isRed){
			enrichedFeeds.sort(new Comparator<EnrichedFeed>() {
				@Override
				public int compare(EnrichedFeed o1, EnrichedFeed o2) {
					if(o1.getFeedRefId() == 0){
						int o1LikesCount = 0;
						int o2LikesCount = 0;
						
						List<Feed> o1Refs = feedService.findByFeedRefId(o1.getFeedId());
						for(Feed feed : o1Refs){
							if(feed.getFeedActionId() == 2){
								o1LikesCount++;
							}
						}
						
						List<Feed> o2Refs = feedService.findByFeedRefId(o2.getFeedId());
						for(Feed feed : o2Refs){
							if(feed.getFeedActionId() == 2){
								o2LikesCount++;
							}
						}
						
						if(o1LikesCount > o2LikesCount){
							return -1;
						}else if(o1LikesCount < o2LikesCount){
							return 1;
						}
						
					}
					return 0;
				}
			});
		}else{
			enrichedFeeds.sort(new Comparator<EnrichedFeed>() {
				@Override
				public int compare(EnrichedFeed o1, EnrichedFeed o2) {
					return o1.getCreatedAt().compareTo(o2.getCreatedAt());
				}
				
			});
		}
		return new ResponseEntity<List<EnrichedFeed>>(enrichedFeeds, HttpStatus.OK);
	}

	private List<EnrichedFeed> getEnrichedFeedById(long userId){
		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();
		List<Feed> feeds = feedService.findByUserId(userId);
		for (Feed feed : feeds) {
			User user = userService.findById(feed.getUserId());
			
			//Remove Comments/like from feed
			if(feed.getFeedActionId()== 1 || feed.getFeedActionId() == 2){
				continue;
			}
			
			if (user == null) {
				continue;
			}

			String name = getName(user);
			String profilePicPath = getProfilePicPath(user);
			
			long refUserId = 0;
			String refName = "";
			String refProfilePicPath = "";
			
			Feed refFeed = feedService.findById(feed.getFeedRefId());
			if(refFeed != null){
				User refUser = userService.findById(refFeed.getUserId());
				
				if(refUser != null){
					refUserId = refUser.getUserId();
					refName = getName(refUser);
					refProfilePicPath = getProfilePicPath(refUser);
				}
			}
			
			EnrichedFeed enrichedFeed = new EnrichedFeed(feed, name, profilePicPath, refUserId, refName, refProfilePicPath);
			enrichedFeeds.add(enrichedFeed);
		}
		return enrichedFeeds;
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
