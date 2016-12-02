/**
 * 
 */
package com.sqrfactor.dao.competition;

import java.util.List;

import com.sqrfactor.model.competition.EventFeed;

/**
 * @author Angad Gill
 *
 */
public interface EventFeedDao {

	EventFeed findByEventFeedId(long eventFeedId);
	
	List<EventFeed> findAllByEventTypeAndEventTypeId(String eventFeed, long eventTypeId);
	
	void saveEventFeed(EventFeed eventFeed);

	void deleteEventFeedById(long eventFeedId);

}
