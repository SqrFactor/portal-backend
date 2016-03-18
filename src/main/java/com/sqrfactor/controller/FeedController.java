/**
 * 
 */
package com.sqrfactor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Feed;
import com.sqrfactor.service.FeedService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class FeedController {

	@Autowired
	private FeedService feedService;
	
	public FeedController(){}
	
	/**
	 * Get all the feeds
	 * 
	 * @return
	 */
	@RequestMapping(value = "/feed/", method = RequestMethod.GET)
	public ResponseEntity<List<Feed>> getAllFeeds() {
		List<Feed> feeds = feedService.findAllFeeds();
		if (feeds.isEmpty()) {
			return new ResponseEntity<List<Feed>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
	}

	/**
	 * Get a Feed by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/feed/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Feed> getFeedById(@PathVariable int id) {
		Feed feed = feedService.findById(id);
		if (feed == null) {
			return new ResponseEntity<Feed>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Feed>(feed, HttpStatus.OK);
	}

	/**
	 * Create Feed
	 * 
	 * @param feed
	 */
	@RequestMapping(value = "/feed/", method = RequestMethod.POST)
	public ResponseEntity<Feed> createFeed(@RequestBody Feed feed) {
		if (feedService.findById(feed.getFeedId()) != null) {
			return new ResponseEntity<Feed>(feed, HttpStatus.CONFLICT);
		}

		feedService.saveFeed(feed);

		return new ResponseEntity<Feed>(feed, HttpStatus.CREATED);
	}

	/**
	 * Update Feed
	 * 
	 * @param id
	 * @param feed
	 * @return
	 */
	@RequestMapping(value = "/feed/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Feed> updateFeed(@PathVariable("id") int id, @RequestBody Feed feed) {
		Feed currentFeed = feedService.findById(id);

		if (currentFeed == null) {
			return new ResponseEntity<Feed>(HttpStatus.NOT_FOUND);
		}
		feed.setFeedId(id);
		feedService.updateFeed(feed);
		return new ResponseEntity<Feed>(feed, HttpStatus.OK);
	}

	/**
	 * Delete Feed by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/feed/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Feed> deleteFeed(@PathVariable("id") int id) {
		Feed feed = feedService.findById(id);
		if (feed == null) {
			return new ResponseEntity<Feed>(HttpStatus.NOT_FOUND);
		}

		feedService.deleteFeedById(id);
		return new ResponseEntity<Feed>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete all feeds
	 * 
	 * @return
	 */
	@RequestMapping(value = "/feed/", method = RequestMethod.DELETE)
	public ResponseEntity<Feed> deleteAllFeeds() {
		feedService.deleteAllFeeds();
		return new ResponseEntity<Feed>(HttpStatus.NO_CONTENT);
	}
}
