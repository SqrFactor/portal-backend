/**
 * 
 */
package com.sqrfactor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name="notification_details")
public class Notification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="notificationId")
	private long notificationId;
	
	@Column(name="sourceUserId")
	private long sourceUserId;
	
	@Column(name="destinationUserId")
	private long destinationUserId;
	
	@Column(name="notificationTypeId")
	private long notificationTypeId;
	
	@Column(name="feedRefId", nullable=true)
	private long feedRefId;
	
	@Column(name="isRead")
	private boolean isRead;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdAt", nullable=false)
	private Date createdAt;
	
	public Notification() {
	}

	/**
	 * @param notificationId
	 * @param sourceUserId
	 * @param destinationUserId
	 * @param notificationTypeId
	 * @param feedRefId
	 * @param isRead
	 * @param createdAt
	 */
	public Notification(long notificationId, long sourceUserId, long destinationUserId, long notificationTypeId,
			long feedRefId, boolean isRead, Date createdAt) {
		super();
		this.notificationId = notificationId;
		this.sourceUserId = sourceUserId;
		this.destinationUserId = destinationUserId;
		this.notificationTypeId = notificationTypeId;
		this.feedRefId = feedRefId;
		this.isRead = isRead;
		this.createdAt = createdAt;
	}

	/**
	 * @return the notificationId
	 */
	public long getNotificationId() {
		return notificationId;
	}

	/**
	 * @param notificationId the notificationId to set
	 */
	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	/**
	 * @return the sourceUserId
	 */
	public long getSourceUserId() {
		return sourceUserId;
	}

	/**
	 * @param sourceUserId the sourceUserId to set
	 */
	public void setSourceUserId(long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	/**
	 * @return the destinationUserId
	 */
	public long getDestinationUserId() {
		return destinationUserId;
	}

	/**
	 * @param destinationUserId the destinationUserId to set
	 */
	public void setDestinationUserId(long destinationUserId) {
		this.destinationUserId = destinationUserId;
	}

	/**
	 * @return the notificationTypeId
	 */
	public long getNotificationTypeId() {
		return notificationTypeId;
	}

	/**
	 * @param notificationTypeId the notificationTypeId to set
	 */
	public void setNotificationTypeId(long notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	/**
	 * @return the feedRefId
	 */
	public long getFeedRefId() {
		return feedRefId;
	}

	/**
	 * @param feedRefId the feedRefId to set
	 */
	public void setFeedRefId(long feedRefId) {
		this.feedRefId = feedRefId;
	}

	/**
	 * @return the isRead
	 */
	public boolean isRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
