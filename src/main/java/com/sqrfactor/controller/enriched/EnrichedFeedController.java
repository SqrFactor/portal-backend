/**
 * 
 */
package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sqrfactor.model.Connection;
import com.sqrfactor.model.EnrichedFeed;
import com.sqrfactor.model.Feed;
import com.sqrfactor.model.User;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.FeedService;
import com.sqrfactor.service.UserService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedFeedController {

	@Autowired
	private FeedService feedService;

	@Autowired
	private UserService userService;

	@Autowired
	private ConnectionService connectionService;

	/**
	 * Get a Feed by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enrichedfeed/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<EnrichedFeed> retrieveEnrichedFeedById(@PathVariable int id) {
		Feed feed = feedService.findById(id);

		if (feed == null) {
			return new ResponseEntity<EnrichedFeed>(HttpStatus.NOT_FOUND);
		}

		User user = userService.findById(feed.getUserId());

		if (user == null) {
			return new ResponseEntity<EnrichedFeed>(HttpStatus.NOT_FOUND);
		}

		String name = getName(user);
		String profilePicPath = getProfilePicPath(user);

		long refUserId = 0;
		String refName = "";
		String refProfilePicPath = "";

		Feed refFeed = feedService.findById(feed.getFeedRefId());
		if (refFeed != null) {
			User refUser = userService.findById(refFeed.getUserId());

			if (refUser != null) {

				refUserId = refUser.getUserId();
				refName = getName(refUser);
				refProfilePicPath = getProfilePicPath(refUser);
			}
		}

		EnrichedFeed enrichedFeed = new EnrichedFeed(feed, name, profilePicPath, refUserId, refName, refProfilePicPath);
		return new ResponseEntity<EnrichedFeed>(enrichedFeed, HttpStatus.OK);
	}

	/**
	 * Get a Feed by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enrichedfeed/userid/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SpecialEnrichedFeed>> getFeedById(@PathVariable int userId,
			@RequestParam(value = "first", required = false) int first,
			@RequestParam(value = "max", required = false) int max) {
		List<SpecialEnrichedFeed> specialEnrichedFeeds = new ArrayList<>();

		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();
		enrichedFeeds = getEnrichedFeedById(userId);

		if (enrichedFeeds.isEmpty()) {
			return new ResponseEntity<List<SpecialEnrichedFeed>>(HttpStatus.OK);
		}

		// ADD CHILD REF
		for (EnrichedFeed enrichedFeed : enrichedFeeds) {

			// Refactor
			List<EnrichedFeed> childFeeds = new ArrayList<EnrichedFeed>();

			List<Feed> feeds = feedService.findByFeedRefId(enrichedFeed.getFeedId());
			for (Feed feed : feeds) {
				User user = userService.findById(feed.getUserId());

				if (user == null) {
					continue;
				}

				String name = getName(user);
				String profilePicPath = getProfilePicPath(user);

				long refUserId = 0;
				String refName = "";
				String refProfilePicPath = "";

				Feed refFeed = feedService.findById(feed.getFeedRefId());
				if (refFeed != null) {
					User refUser = userService.findById(refFeed.getUserId());

					if (refUser != null) {

						refUserId = refUser.getUserId();
						refName = getName(refUser);
						refProfilePicPath = getProfilePicPath(refUser);
					}
				}

				EnrichedFeed childFeed = new EnrichedFeed(feed, name, profilePicPath, refUserId, refName,
						refProfilePicPath);
				childFeeds.add(childFeed);
			}

			SpecialEnrichedFeed specialEnrichedFeed = new SpecialEnrichedFeed(enrichedFeed, childFeeds);
			specialEnrichedFeeds.add(specialEnrichedFeed);
		}

		// Check if first max can return results
		if (specialEnrichedFeeds.size() <= first) {
			return new ResponseEntity<List<SpecialEnrichedFeed>>(HttpStatus.NOT_FOUND);
		} else if (first == 0 && max == 0) {
			return new ResponseEntity<List<SpecialEnrichedFeed>>(specialEnrichedFeeds, HttpStatus.OK);
		}

		// TODO Move this to dao
		List<SpecialEnrichedFeed> specialEnrichedFeedsToReturn = new ArrayList<>();
		for (int i = first; i < first + max && i < specialEnrichedFeedsToReturn.size(); i++) {
			specialEnrichedFeedsToReturn.add(specialEnrichedFeedsToReturn.get(i));
		}
		return new ResponseEntity<List<SpecialEnrichedFeed>>(specialEnrichedFeedsToReturn, HttpStatus.OK);
	}

	/**
	 * Get a Feed by Ref Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enrichedfeed/feedrefid/{feedRefId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EnrichedFeed>> getFeedByFeedRefId(@PathVariable int feedRefId) {
		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();

		List<Feed> feeds = feedService.findByFeedRefId(feedRefId);
		for (Feed feed : feeds) {
			User user = userService.findById(feed.getUserId());

			if (user == null) {
				continue;
			}

			String name = getName(user);
			String profilePicPath = getProfilePicPath(user);

			long refUserId = 0;
			String refName = "";
			String refProfilePicPath = "";

			Feed refFeed = feedService.findById(feed.getFeedRefId());
			if (refFeed != null) {
				User refUser = userService.findById(refFeed.getUserId());

				if (refUser != null) {

					refUserId = refUser.getUserId();
					refName = getName(refUser);
					refProfilePicPath = getProfilePicPath(refUser);
				}
			}

			EnrichedFeed enrichedFeed = new EnrichedFeed(feed, name, profilePicPath, refUserId, refName,
					refProfilePicPath);
			enrichedFeeds.add(enrichedFeed);
		}
		if (enrichedFeeds.isEmpty()) {
			return new ResponseEntity<List<EnrichedFeed>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<EnrichedFeed>>(enrichedFeeds, HttpStatus.OK);

	}

	/**
	 * Get a Feed by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enrichedfeed/conuserid/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SpecialEnrichedFeed>> getConnectionFeedById(@PathVariable int userId,
			@RequestParam(value = "isRed", required = false, defaultValue = "false") boolean isRed,
			@RequestParam(value = "first", required = false) int first,
			@RequestParam(value = "max", required = false) int max) {
		List<SpecialEnrichedFeed> specialEnrichedFeeds = new ArrayList<>();

		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();
		enrichedFeeds = getEnrichedFeedById(userId);

		List<Connection> connections = connectionService.findConnectionsBySourceId(userId);
		for (Connection connection : connections) {
			enrichedFeeds.addAll(getEnrichedFeedById(connection.getDestinationId()));
		}

		if (enrichedFeeds.isEmpty()) {
			return new ResponseEntity<List<SpecialEnrichedFeed>>(HttpStatus.NOT_FOUND);
		}

		// ADD CHILD REF
		for (EnrichedFeed enrichedFeed : enrichedFeeds) {

			// Refactor
			List<EnrichedFeed> childFeeds = new ArrayList<EnrichedFeed>();

			List<Feed> feeds = feedService.findByFeedRefId(enrichedFeed.getFeedId());
			for (Feed feed : feeds) {
				User user = userService.findById(feed.getUserId());

				if (user == null) {
					continue;
				}

				String name = getName(user);
				String profilePicPath = getProfilePicPath(user);

				long refUserId = 0;
				String refName = "";
				String refProfilePicPath = "";

				Feed refFeed = feedService.findById(feed.getFeedRefId());
				if (refFeed != null) {
					User refUser = userService.findById(refFeed.getUserId());

					if (refUser != null) {

						refUserId = refUser.getUserId();
						refName = getName(refUser);
						refProfilePicPath = getProfilePicPath(refUser);
					}
				}

				EnrichedFeed childFeed = new EnrichedFeed(feed, name, profilePicPath, refUserId, refName,
						refProfilePicPath);
				childFeeds.add(childFeed);
			}

			SpecialEnrichedFeed specialEnrichedFeed = new SpecialEnrichedFeed(enrichedFeed, childFeeds);
			specialEnrichedFeeds.add(specialEnrichedFeed);
		}

		// Sort Based on likes
		if (isRed) {
			specialEnrichedFeeds.sort(new Comparator<SpecialEnrichedFeed>() {
				@Override
				public int compare(SpecialEnrichedFeed o1, SpecialEnrichedFeed o2) {
					if (o1.getFeedRefId() == 0) {
						int o1LikesCount = 0;
						int o2LikesCount = 0;

						List<Feed> o1Refs = feedService.findByFeedRefId(o1.getFeedId());
						for (Feed feed : o1Refs) {
							if (feed.getFeedActionId() == 2) {
								o1LikesCount++;
							}
						}

						List<Feed> o2Refs = feedService.findByFeedRefId(o2.getFeedId());
						for (Feed feed : o2Refs) {
							if (feed.getFeedActionId() == 2) {
								o2LikesCount++;
							}
						}

						if (o1LikesCount > o2LikesCount) {
							return -1;
						} else if (o1LikesCount < o2LikesCount) {
							return 1;
						}

					}
					return 0;
				}
			});
		} else {
			specialEnrichedFeeds.sort(new Comparator<SpecialEnrichedFeed>() {
				@Override
				public int compare(SpecialEnrichedFeed o1, SpecialEnrichedFeed o2) {
					if (o1.getCreatedAt().getTime() > o2.getCreatedAt().getTime()) {
						return -1;
					} else if (o1.getCreatedAt().getTime() < o2.getCreatedAt().getTime()) {
						return 1;
					} else {
						return 0;
					}

				}

			});
		}

		// Check if first max can return results
		if (specialEnrichedFeeds.size() <= first) {
			return new ResponseEntity<List<SpecialEnrichedFeed>>(HttpStatus.NOT_FOUND);
		} else if (first == 0 && max == 0) {
			return new ResponseEntity<List<SpecialEnrichedFeed>>(specialEnrichedFeeds, HttpStatus.OK);
		}

		// TODO Move this to dao
		List<SpecialEnrichedFeed> specialEnrichedFeedsToReturn = new ArrayList<>();
		for (int i = first; i < first + max && i < enrichedFeeds.size(); i++) {
			specialEnrichedFeedsToReturn.add(specialEnrichedFeeds.get(i));
		}
		return new ResponseEntity<List<SpecialEnrichedFeed>>(specialEnrichedFeedsToReturn, HttpStatus.OK);
	}

	private List<EnrichedFeed> getEnrichedFeedById(long userId) {
		List<EnrichedFeed> enrichedFeeds = new ArrayList<EnrichedFeed>();
		List<Feed> feeds = feedService.findByUserId(userId);
		for (Feed feed : feeds) {
			User user = userService.findById(feed.getUserId());

			// Remove Comments/like from feed
			if (feed.getFeedActionId() == 1 || feed.getFeedActionId() == 2) {
				continue;
			}

			if (user == null) {
				continue;
			}

			String name = getName(user);
			String profilePicPath = getProfilePicPath(user);

			long refUserId = 0;
			String refName = "";
			String refProfilePicPath = "";

			Feed refFeed = feedService.findById(feed.getFeedRefId());
			if (refFeed != null) {
				User refUser = userService.findById(refFeed.getUserId());

				if (refUser != null) {
					refUserId = refUser.getUserId();
					refName = getName(refUser);
					refProfilePicPath = getProfilePicPath(refUser);
				}
			}

			EnrichedFeed enrichedFeed = new EnrichedFeed(feed, name, profilePicPath, refUserId, refName,
					refProfilePicPath);
			enrichedFeeds.add(enrichedFeed);
		}
		return enrichedFeeds;
	}

	private String getName(User user) {
		String firstName = "";
		String lastName = "";
		if (user.getFirstName() != null) {
			firstName = user.getFirstName();
		}
		if (user.getLastName() != null) {
			lastName = user.getLastName();
		}
		String name = firstName + " " + lastName;

		return name;
	}

	private String getProfilePicPath(User user) {
		String profilePicPath = "";

		if (user.getProfilePicPath() != null) {
			profilePicPath = user.getProfilePicPath();
		}
		return profilePicPath;
	}

	private class SpecialEnrichedFeed extends EnrichedFeed {
		private List<EnrichedFeed> childFeeds;

		public SpecialEnrichedFeed(EnrichedFeed enrichedFeed, List<EnrichedFeed> childFeeds) {
			super(enrichedFeed);
			this.childFeeds = childFeeds;
		}

		/**
		 * @return the childFeeds
		 */
		public List<EnrichedFeed> getChildFeeds() {
			return childFeeds;
		}

		/**
		 * @param childFeeds
		 *            the childFeeds to set
		 */
		public void setChildFeeds(List<EnrichedFeed> childFeeds) {
			this.childFeeds = childFeeds;
		}

	}

}
