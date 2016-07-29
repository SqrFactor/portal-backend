/**
 * 
 */
package com.sqrfactor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Notification;
import com.sqrfactor.model.Notification;
import com.sqrfactor.model.Notification;
import com.sqrfactor.service.NotificationService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	public NotificationController() {
	}
	
	/**
	 * Get all the notifications
	 * 
	 * @return
	 */
	@RequestMapping(value = "/notification/", method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> getAllNotifications() {
		List<Notification> notifications = notificationService.findAllNotifications();
		if (notifications.isEmpty()) {
			return new ResponseEntity<List<Notification>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Notification>>(notifications, HttpStatus.OK);
	}

	/**
	 * Get a Notification by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/notification/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Notification> getNotificationById(@PathVariable int id) {
		Notification notification = notificationService.findById(id);
		if (notification == null) {
			return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Notification>(notification, HttpStatus.OK);
	}

	/**
	 * Create Notification
	 * 
	 * @param notification
	 */
	@RequestMapping(value = "/notification/", method = RequestMethod.POST)
	public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {

		notificationService.saveNotification(notification);

		return new ResponseEntity<Notification>(notification, HttpStatus.CREATED);
	}

	/**
	 * Update Notification
	 * 
	 * @param id
	 * @param notification
	 * @return
	 */
	@RequestMapping(value = "/notification/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Notification> updateNotification(@PathVariable("id") int id,
			@RequestBody Notification notification) {
		Notification currentNotification = notificationService.findById(id);

		if (currentNotification == null) {
			return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
		}
		notification.setNotificationId(id);
		notificationService.updateNotification(notification);
		return new ResponseEntity<Notification>(notification, HttpStatus.OK);
	}

	/**
	 * Delete Notification by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/notification/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Notification> deleteNotification(@PathVariable("id") int id) {
		Notification notification = notificationService.findById(id);
		if (notification == null) {
			return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
		}

		notificationService.deleteNotificationById(id);
		return new ResponseEntity<Notification>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Get all notifications by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/notification/sourceuserid/{sourceUserId}", method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> getNotificationsBySourceUserId(@PathVariable("sourceUserId") long sourceUserId) {
		List<Notification> notifications = notificationService.findNotificationsBySourceUserId(sourceUserId);
		if (notifications.isEmpty()) {
			return new ResponseEntity<List<Notification>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Notification>>(notifications, HttpStatus.OK);
	}
	

}
