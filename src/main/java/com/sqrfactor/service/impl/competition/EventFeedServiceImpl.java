/**
 * 
 */
package com.sqrfactor.service.impl.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.competition.EventFeedDao;
import com.sqrfactor.model.competition.EventFeed;
import com.sqrfactor.service.competition.EventFeedService;

/**
 * @author Angad Gill
 *
 */
@Service("eventFeedService")
@Transactional
public class EventFeedServiceImpl implements EventFeedService{
	
	@Autowired
	private EventFeedDao eventFeedDao;

	/**
	 * Find EventFeed by id
	 */
	public EventFeed findByEventFeedId(long eventFeedId) {
		return eventFeedDao.findByEventFeedId(eventFeedId);
	}
	
	/**
	 * Save EventFeed
	 */
	public void saveEventFeed(EventFeed eventFeed) {
		eventFeedDao.saveEventFeed(eventFeed);
	}

	/**
	 * Update EventFeed
	 */
	public void updateEventFeed(EventFeed eventFeed) {
		EventFeed entity = eventFeedDao.findByEventFeedId(eventFeed.getEventFeedId());
		if (entity != null) {
			entity.setUserId(eventFeed.getUserId());
			entity.setEventType(eventFeed.getEventType());
			entity.setEventTypeId(eventFeed.getEventTypeId());
			entity.setEventActionId(eventFeed.getEventActionId());
			entity.setEventRefId(eventFeed.getEventRefId());
			entity.setCreatedAt(eventFeed.getCreatedAt());
			entity.setModifiedAt(eventFeed.getModifiedAt());
		}
	}

	@Override
	public void deleteEventFeedById(long eventFeedId) {
		eventFeedDao.deleteEventFeedById(eventFeedId);
	}


}
