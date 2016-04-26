/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.NotificationDao;
import com.sqrfactor.model.Notification;
import com.sqrfactor.service.NotificationService;

/**
 * @author Angad Gill
 *
 */
@Service("notificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;

	@Override
	public Notification findById(long notificationId) {
		return notificationDao.findById(notificationId);
	}

	@Override
	public void saveNotification(Notification notification) {
		notificationDao.saveNotification(notification);
	}

	@Override
	public void updateNotification(Notification notification) {
		Notification entity = notificationDao.findById(notification.getNotificationId());

		if (entity != null) {
			entity.setSourceUserId(notification.getSourceUserId());
			entity.setDestinationUserId(notification.getDestinationUserId());
			entity.setNotificationTypeId(notification.getNotificationTypeId());
			entity.setFeedRefId(notification.getFeedRefId());
			entity.setRead(notification.isRead());
			entity.setCreatedAt(notification.getCreatedAt());
		}
	}

	@Override
	public void deleteNotificationById(long notificationId) {
		notificationDao.deleteNotificationById(notificationId);
	}

	@Override
	public List<Notification> findAllNotifications(){
		return notificationDao.findAllNotifications();
	}
	
	@Override
	public List<Notification> findNotificationsBySourceUserId(long sourceUserId){
		return notificationDao.findNotificationsBySourceUserId(sourceUserId);
	}
}
