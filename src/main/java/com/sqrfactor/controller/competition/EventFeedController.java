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

import com.sqrfactor.model.Connection;
import com.sqrfactor.model.competition.EventFeed;
import com.sqrfactor.service.competition.EventFeedService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EventFeedController {

	@Autowired
	private EventFeedService eventFeedService;

	public EventFeedController() {
	}
	
	/**
	 * Get a EventFeed by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/eventfeed/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<EventFeed> getEventFeedById(@PathVariable int id) {
		EventFeed eventFeed = eventFeedService.findByEventFeedId(id);
		
		if (eventFeed == null) {
			return new ResponseEntity<EventFeed>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EventFeed>(eventFeed, HttpStatus.OK);
	}

	/**
	 * Create EventFeed
	 * 
	 * @param message
	 */
	@RequestMapping(value = "/eventfeed/", method = RequestMethod.POST)
	public ResponseEntity<EventFeed> createCompetition(@RequestBody EventFeed eventFeed) {

		eventFeedService.saveEventFeed(eventFeed);
		
		return new ResponseEntity<EventFeed>(eventFeed, HttpStatus.CREATED);
	}

	/**
	 * Update EventFeed
	 * 
	 * @param id
	 * @param eventFeed
	 * @return
	 */
	@RequestMapping(value = "/eventfeed/{id}", method = RequestMethod.PUT)
	public ResponseEntity<EventFeed> updateEventFeed(@PathVariable("id") int id,
			@RequestBody EventFeed eventFeed) {
		EventFeed currentEventfeed= eventFeedService.findByEventFeedId(id);

		if (currentEventfeed== null) {
			return new ResponseEntity<EventFeed>(HttpStatus.NOT_FOUND);
		}
		eventFeed.setEventFeedId(id);
		eventFeedService.updateEventFeed(eventFeed);
		return new ResponseEntity<EventFeed>(eventFeed, HttpStatus.OK);
	}

	/**
	 * Delete EventFeed by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/eventfeed/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EventFeed> deleteEventFeed(@PathVariable("id") int id) {
		EventFeed eventFeed = eventFeedService.findByEventFeedId(id);
		if (eventFeed == null) {
			return new ResponseEntity<EventFeed>(HttpStatus.NOT_FOUND);
		}

		eventFeedService.deleteEventFeedById(id);
		return new ResponseEntity<EventFeed>(HttpStatus.NO_CONTENT);
	}
}
