/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqrfactor.dao.FeedDao;
import com.sqrfactor.model.Feed;
import com.sqrfactor.service.FeedService;

/**
 * @author Angad Gill
 *
 */
@Service("feedService")
@Transactional
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedDao feedDao;

	/**
	 * Find All Feeds
	 */
	public List<Feed> findAllFeeds() {
		return feedDao.findAllFeeds();
	}

	/**
	 * Find Feed by id
	 */
	public Feed findById(long feedId) {
		return feedDao.findById(feedId);
	}
	
	/**
	 * Find Feed by user id
	 */
	public List<Feed> findByUserId(long userId) {
		return feedDao.findByUserId(userId);
	}
	
	/**
	 * Find Feed by feed ref id
	 */
	public List<Feed> findByFeedRefId(long feedRefId) {
		return feedDao.findByFeedRefId(feedRefId);
	}
	

	/**
	 * Save Feeds
	 */
	public void saveFeed(Feed feed) {
		Date now = new Date();
		feed.setCreatedAt(now);
		feed.setModifiedAt(now);
		feedDao.saveFeed(feed);
	}

	/**
	 * Update Feed
	 */
	public void updateFeed(Feed feed) {
		Feed entity = feedDao.findById(feed.getFeedId());

		if (entity != null) {
			entity.setUserId(feed.getUserId());
			entity.setFeedTypeId(feed.getFeedTypeId());
			entity.setFeedText(feed.getFeedText());
			entity.setFeedPath(feed.getFeedPath());
			entity.setFeedActionId(feed.getFeedActionId());
			entity.setFeedRefId(feed.getFeedRefId());
			entity.setPlaceName(feed.getPlaceName());
			entity.setPlaceAddress(feed.getPlaceAddress());
			entity.setPlaceLat(feed.getPlaceLat());
			entity.setPlaceLng(feed.getPlaceLng());
			Date now = new Date();
			entity.setModifiedAt(now);
		}
	}

	/**
	 * Delete feed by id
	 */
	public void deleteFeedById(long feedId) {
		feedDao.deleteFeedById(feedId);
	}

	/**
	 * Delete all feeds
	 */
	public void deleteAllFeeds() {
		// TODO
		// profiles.clear();
	}
}
