/**
 * 
 */
package com.sqrfactor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.FeedDao;
import com.sqrfactor.model.Feed;

/**
 * @author Angad Gill
 *
 */
@Repository("feedDao")
public class FeedDaoImpl extends AbstractDao<Long, Feed> implements FeedDao {

	@Override
	public Feed findById(long id) {
		return getByKey(id);
	}

	@Override
	public void saveFeed(Feed feed) {
		persist(feed);
	}

	@Override
	public void deleteFeedById(long feedId) {
		Query query = getSession().createSQLQuery("delete from feed_details where id = :id");
		query.setLong("feedId", feedId);
		query.executeUpdate();
	}

	@Override
	public List<Feed> findAllFeeds() {
		Criteria criteria = createEntityCriteria();
		return (List<Feed>) criteria.list();
	}

	@Override
	public Feed findFeedById(long feedId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("feedId", feedId));
		return (Feed) criteria.uniqueResult();
	}
	
	@Override
	public List<Feed> findByUserId(long userId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (List<Feed>) criteria.list();
	}
	
	@Override
	public List<Feed> findByFeedRefId(int feedRefId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("feedRefId", feedRefId));
		return (List<Feed>) criteria.list();
	}
	
}
