/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Notification;

/**
 * @author Angad Gill
 *
 */
public interface NotificationService {

	Notification findById(long notificationId);

	void saveNotification(Notification notification);

	void updateNotification(Notification notification);

	void deleteNotificationById(long notificationId);
	
	List<Notification> findAllNotifications();
	
	List<Notification> findNotificationsBySourceUserId(long sourceUserId);
	
	List<Notification> findUnreadNotificationsBySourceUserId(long sourceUserId);

}
