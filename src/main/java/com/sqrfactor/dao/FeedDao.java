/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Feed;

/**
 * @author Angad Gill
 *
 */
public interface FeedDao {

	Feed findById(long id);

	void saveFeed(Feed feed);

	void deleteFeedById(long id);

	List<Feed> findAllFeeds();

	Feed findFeedById(long id);

	List<Feed> findByUserId(long userId);
	
	List<Feed> findByFeedRefId(long feedRefId);
}
