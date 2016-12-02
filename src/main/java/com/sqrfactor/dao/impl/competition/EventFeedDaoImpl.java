/**
 * 
 */
package com.sqrfactor.dao.impl.competition;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.competition.EventFeedDao;
import com.sqrfactor.model.Connection;
import com.sqrfactor.model.competition.EventFeed;

/**
 * @author Angad Gill
 *
 */
@Repository("eventFeedDao")
public class EventFeedDaoImpl extends AbstractDao<Long, EventFeed> implements EventFeedDao{

	public EventFeed findById(long id) {
		return getByKey(id);
	}
	
	public EventFeed findByEventFeedId(long eventFeedId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("eventFeedId", eventFeedId));
		return (EventFeed) criteria.uniqueResult();
	}
	
	public List<EventFeed> findAllByEventTypeAndEventTypeId(String eventType, long eventTypeId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("eventType", eventType));
		criteria.add(Restrictions.eq("eventTypeId", eventTypeId));
		return (List<EventFeed>) criteria.list();
	}
	
	public void saveEventFeed(EventFeed eventFeed) {
		persist(eventFeed);
	}

	public void deleteEventFeedById(long eventFeedId) {
		Query query = getSession().createSQLQuery("delete from event_feed where eventFeedId = :eventFeedId");
		query.setLong("eventFeedId", eventFeedId);
		query.executeUpdate();
	}

}
