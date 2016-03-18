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
	
	void saveFeed(Feed feed);

	void updateFeed(Feed feed);

	void deleteFeedById(long userId);

	List<Feed> findAllFeeds();

	void deleteAllFeeds();
}
