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
@Table(name = "achievement_earned")
public class AchievementEarned {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="achievementEarnedId")
	private long achievementEarnedId;

	@Column(name = "userId")
	long userId;
	
	@Column(name = "achievementId")
	long achievementId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdAt", nullable=false)
	private Date createdAt;
	
	public AchievementEarned() {
	}

	public AchievementEarned(AchievementEarned achievementEarned){
		super();
		this.achievementEarnedId = achievementEarned.achievementEarnedId;
		this.userId = achievementEarned.userId;
		this.achievementId = achievementEarned.achievementId;
		this.createdAt = achievementEarned.createdAt;
	}
	
	/**
	 * @param achievementEarnedId
	 * @param userId
	 * @param achievementId
	 * @param createdAt
	 */
	public AchievementEarned(long achievementEarnedId, long userId, long achievementId, Date createdAt) {
		super();
		this.achievementEarnedId = achievementEarnedId;
		this.userId = userId;
		this.achievementId = achievementId;
		this.createdAt = createdAt;
	}

	/**
	 * @return the achievementEarnedId
	 */
	public long getAchievementEarnedId() {
		return achievementEarnedId;
	}

	/**
	 * @param achievementEarnedId the achievementEarnedId to set
	 */
	public void setAchievementEarnedId(long achievementEarnedId) {
		this.achievementEarnedId = achievementEarnedId;
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
	 * @return the achievementId
	 */
	public long getAchievementId() {
		return achievementId;
	}

	/**
	 * @param achievementId the achievementId to set
	 */
	public void setAchievementId(long achievementId) {
		this.achievementId = achievementId;
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
