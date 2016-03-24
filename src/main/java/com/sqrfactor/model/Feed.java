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
@Table(name = "feed_details")
public class Feed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedId")
	private long feedId;
	
	@Column(name = "userId", nullable = false)
	private long userId;
	
	@Column(name = "feedTypeId", nullable = false)
	private int feedTypeId;
	
	@Column(name = "feedText")
	private String feedText;
	
	@Column(name = "feedPath")
	private String feedPath;
	
	@Column(name = "feedActionId")
	private int feedActionId;
	
	@Column(name = "feedRefId")
	private int feedRefId;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modifiedAt", nullable = false)
    private Date modifiedAt;
	
	public Feed(){}

	/**
	 * @param feedId
	 * @param userId
	 * @param feedTypeId
	 * @param feedText
	 * @param feedPath
	 * @param feedActionId
	 * @param feedRefId
	 */
	public Feed(int feedId, int userId, int feedTypeId, String feedText, String feedPath, int feedActionId,
			int feedRefId, Date createdAt, Date modifiedAt) {
		super();
		this.feedId = feedId;
		this.userId = userId;
		this.feedTypeId = feedTypeId;
		this.feedText = feedText;
		this.feedPath = feedPath;
		this.feedActionId = feedActionId;
		this.feedRefId = feedRefId;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public Feed(Feed feed){
		super();
		this.feedId = feed.getFeedId();
		this.userId = feed.getUserId();
		this.feedTypeId = feed.getFeedTypeId();
		this.feedText = feed.getFeedText();
		this.feedPath = feed.getFeedPath();
		this.feedActionId = feed.getFeedActionId();
		this.feedRefId = feed.getFeedRefId();
		this.createdAt = feed.getCreatedAt();
		this.modifiedAt = feed.getModifiedAt();
	}

	/**
	 * @return the feedId
	 */
	public long getFeedId() {
		return feedId;
	}

	/**
	 * @param feedId the feedId to set
	 */
	public void setFeedId(long feedId) {
		this.feedId = feedId;
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
	 * @return the feedTypeId
	 */
	public int getFeedTypeId() {
		return feedTypeId;
	}

	/**
	 * @param feedTypeId the feedTypeId to set
	 */
	public void setFeedTypeId(int feedTypeId) {
		this.feedTypeId = feedTypeId;
	}

	/**
	 * @return the feedText
	 */
	public String getFeedText() {
		return feedText;
	}

	/**
	 * @param feedText the feedText to set
	 */
	public void setFeedText(String feedText) {
		this.feedText = feedText;
	}

	/**
	 * @return the feedPath
	 */
	public String getFeedPath() {
		return feedPath;
	}

	/**
	 * @param feedPath the feedPath to set
	 */
	public void setFeedPath(String feedPath) {
		this.feedPath = feedPath;
	}

	/**
	 * @return the feedActionId
	 */
	public int getFeedActionId() {
		return feedActionId;
	}

	/**
	 * @param feedActionId the feedActionId to set
	 */
	public void setFeedActionId(int feedActionId) {
		this.feedActionId = feedActionId;
	}

	/**
	 * @return the feedRefId
	 */
	public int getFeedRefId() {
		return feedRefId;
	}

	/**
	 * @param feedRefId the feedRefId to set
	 */
	public void setFeedRefId(int feedRefId) {
		this.feedRefId = feedRefId;
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
