/**
 * 
 */
package com.sqrfactor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.NotificationDao;
import com.sqrfactor.model.Connection;
import com.sqrfactor.model.Message;
import com.sqrfactor.model.Notification;

/**
 * @author Angad Gill
 *
 */
@Repository("notificationDao")
public class NotificationDaoImpl extends AbstractDao<Long, Notification> implements NotificationDao {

	@Override
	public Notification findById(long notificationId) {
		return getByKey(notificationId);
	}

	@Override
	public void saveNotification(Notification notification) {
		persist(notification);
	}

	@Override
	public void deleteNotificationById(long notificationId) {
		Query query = getSession().createSQLQuery("delete from notification_details where notificationId = :notificationId");
		query.setLong("notificationId", notificationId);
		query.executeUpdate();
	}
	
	public List<Notification> findAllNotifications(){
		Criteria criteria = createEntityCriteria();
		return (List<Notification>) criteria.list();
	}
	
	public List<Notification> findNotificationsBySourceUserId(long sourceUserId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("sourceUserId", sourceUserId));
		return (List<Notification>) criteria.list();
	}
	
	public List<Notification> findUnreadNotificationsBySourceUserId(long sourceUserId){
		Criteria criteria = createEntityCriteria();
		Criterion source = Restrictions.eq("sourceUserId", sourceUserId);
		Criterion read = Restrictions.eq("isRead", false);
		
		criteria.add(Restrictions.and(source, read));
		return (List<Notification>) criteria.list();
	}


}
