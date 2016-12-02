/**
 * 
 */
package com.sqrfactor.dao.competition;

import com.sqrfactor.model.competition.EventFeed;

/**
 * @author Angad Gill
 *
 */
public interface EventFeedDao {

	EventFeed findByEventFeedId(long eventFeedId);
	
	void saveEventFeed(EventFeed eventFeed);

	void deleteEventFeedById(long eventFeedId);

}
