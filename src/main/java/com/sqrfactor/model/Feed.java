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
	private long feedRefId;
	
	@Column(name = "placeName")
	private String placeName;
	
	@Column(name = "placeAddress")
	private String placeAddress;
	
	@Column(name = "placeLat")
	private double placeLat;
	
	@Column(name = "placeLng")
	private double placeLng;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modifiedAt", nullable = false)
    private Date modifiedAt;
	
	public Feed(){}

	public Feed(long feedId, long userId, int feedTypeId, String feedText, String feedPath, int feedActionId,
			long feedRefId, String placeName, String placeAddress, double placeLat, double placeLng, Date createdAt, Date modifiedAt) {
		super();
		this.feedId = feedId;
		this.userId = userId;
		this.feedTypeId = feedTypeId;
		this.feedText = feedText;
		this.feedPath = feedPath;
		this.feedActionId = feedActionId;
		this.feedRefId = feedRefId;
		this.placeName = placeName;
		this.placeAddress = placeAddress;
		this.placeLat = placeLat;
		this.placeLng = placeLng;
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
		this.placeName = feed.getPlaceName();
		this.placeAddress = feed.getPlaceAddress();
		this.placeLat = feed.getPlaceLat();
		this.placeLng = feed.getPlaceLng();
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
	 * @return the placeName
	 */
	public String getPlaceName() {
		return placeName;
	}

	/**
	 * @param placeName the placeName to set
	 */
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	/**
	 * @return the placeAddress
	 */
	public String getPlaceAddress() {
		return placeAddress;
	}

	/**
	 * @param placeAddress the placeAddress to set
	 */
	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	/**
	 * @return the placeLat
	 */
	public double getPlaceLat() {
		return placeLat;
	}

	/**
	 * @param placeLat the placeLat to set
	 */
	public void setPlaceLat(double placeLat) {
		this.placeLat = placeLat;
	}

	/**
	 * @return the placeLng
	 */
	public double getPlaceLng() {
		return placeLng;
	}

	/**
	 * @param placeLng the placeLng to set
	 */
	public void setPlaceLng(double placeLng) {
		this.placeLng = placeLng;
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
