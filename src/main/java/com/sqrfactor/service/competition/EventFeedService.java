/**
 * 
 */
package com.sqrfactor.service.competition;

import java.util.List;

import com.sqrfactor.model.competition.EventFeed;

/**
 * @author Angad Gill
 *
 */
public interface EventFeedService {

	EventFeed findByEventFeedId(long eventFeedId);
	
	List<EventFeed> findAllByEventTypeAndEventTypeId(String eventType, long eventTypeId);
	
	void saveEventFeed(EventFeed eventFeed);

	void updateEventFeed(EventFeed eventFeed);

	void deleteEventFeedById(long eventFeedId);

}
