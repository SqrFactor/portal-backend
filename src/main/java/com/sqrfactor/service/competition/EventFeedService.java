/**
 * 
 */
package com.sqrfactor.service.competition;

import com.sqrfactor.model.competition.EventFeed;

/**
 * @author Angad Gill
 *
 */
public interface EventFeedService {

	EventFeed findByEventFeedId(long eventFeedId);
	
	void saveEventFeed(EventFeed eventFeed);

	void updateEventFeed(EventFeed eventFeed);

	void deleteEventFeedById(long eventFeedId);

}
