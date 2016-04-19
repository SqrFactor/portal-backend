/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Feed;

/**
 * @author Angad Gill
 *
 */
public interface FeedService {
	
	Feed findById(long feedId);
	
	List<Feed> findByUserId(long userId);
	
	List<Feed> findByFeedRefId(int feedRefId);
	
	void saveFeed(Feed feed);

	void updateFeed(Feed feed);

	void deleteFeedById(long feedId);

	List<Feed> findAllFeeds();

	void deleteAllFeeds();
}
