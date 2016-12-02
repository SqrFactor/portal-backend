/**
 * 
 */
package com.sqrfactor.model.competition;

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
@Table(name = "event_feed")
public class EventFeed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventFeedId")
	long eventFeedId;
	
	@Column(name = "userId", nullable = false)
	long userId;
	
	@Column(name="eventType" , nullable = false)
	private String eventType;
	
	@Column(name = "eventTypeId", nullable = false)
	long eventTypeId;
	
	@Column(name = "eventActionId")
	int eventActionId;
	
	@Column(name = "eventRefId")
	long eventRefId;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modifiedAt", nullable = false)
    private Date modifiedAt;
	
	public EventFeed(){}

	/**
	 * @return the eventFeedId
	 */
	public long getEventFeedId() {
		return eventFeedId;
	}

	/**
	 * @param eventFeedId the eventFeedId to set
	 */
	public void setEventFeedId(long eventFeedId) {
		this.eventFeedId = eventFeedId;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the eventTypeId
	 */
	public long getEventTypeId() {
		return eventTypeId;
	}

	/**
	 * @param eventTypeId the eventTypeId to set
	 */
	public void setEventTypeId(long eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	/**
	 * @return the eventActionId
	 */
	public int getEventActionId() {
		return eventActionId;
	}

	/**
	 * @param eventActionId the eventActionId to set
	 */
	public void setEventActionId(int eventActionId) {
		this.eventActionId = eventActionId;
	}

	/**
	 * @return the eventRefId
	 */
	public long getEventRefId() {
		return eventRefId;
	}

	/**
	 * @param eventRefId the eventRefId to set
	 */
	public void setEventRefId(long eventRefId) {
		this.eventRefId = eventRefId;
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

	/**
	 * @return the modifiedAt
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * @param modifiedAt the modifiedAt to set
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
