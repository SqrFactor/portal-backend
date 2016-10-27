/**
 * 
 */
package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.EnrichedNotification;
import com.sqrfactor.model.Notification;
import com.sqrfactor.model.User;
import com.sqrfactor.service.NotificationService;
import com.sqrfactor.service.UserService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedNotificationController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private NotificationService notificationService;

	/**
	 * Get all notifications by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/notification/enriched/sourceuserid/{sourceUserId}", method = RequestMethod.GET)
	public ResponseEntity<List<EnrichedNotification>> getNotificationsBySourceUserId(@PathVariable("sourceUserId") long sourceUserId) {
		List<Notification> notifications = notificationService.findNotificationsBySourceUserId(sourceUserId);
		if (notifications.isEmpty()) {
			return new ResponseEntity<List<EnrichedNotification>>(HttpStatus.NOT_FOUND);
		}
		
		List<EnrichedNotification> enrichedNotifications = new ArrayList<>();
		for(Notification notification : notifications){
			
			String sourceUserName = getName(notification.getSourceUserId());
			String destinationUserName = getName(notification.getDestinationUserId());
			String sourceProfilePicPath = getProfilePic(notification.getSourceUserId());
			String destinationProfilePicPath = getProfilePic(notification.getDestinationUserId());
			
			final EnrichedNotification enrichedNotification = new EnrichedNotification(notification, sourceUserName, destinationUserName, sourceProfilePicPath, destinationProfilePicPath);
			enrichedNotifications.add(enrichedNotification);
		}
		
		return new ResponseEntity<List<EnrichedNotification>>(enrichedNotifications, HttpStatus.OK);
	}
	
	private String getName(long userId){
		
		User user = userService.findById(userId);

		if (user == null) {
			return null;
		}

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
	
	private String getProfilePic(long userId){
		
		User user = userService.findById(userId);

		if (user == null) {
			return null;
		}

		String profilePicPath = "";
		
		if (user.getProfilePicPath() != null) {
			profilePicPath = user.getProfilePicPath();
		}
		
		return profilePicPath;
	}

}
