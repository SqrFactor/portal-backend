/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Notification;

/**
 * @author Angad Gill
 *
 */
public interface NotificationDao {

	Notification findById(long notificationId);

	void saveNotification(Notification notification);

	void deleteNotificationById(long notificationId);
	
	List<Notification> findAllNotifications();
	
	List<Notification> findNotificationsBySourceUserId(long sourceUserId);
	
	List<Notification> findUnreadNotificationsBySourceUserId(long sourceUserId);
}
