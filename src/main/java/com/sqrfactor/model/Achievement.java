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
@Table(name = "achievement_details")
public class Achievement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="achievementId")
	private long achievementId;
	
	@Column(name="achievementName")
	private String achievementName;
	
	@Column(name="achievementDescription")
	private String achievementDescription;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdAt", nullable=false)
	private Date createdAt;
	
	public Achievement() {
	}

	public Achievement(Achievement achievement){
		super();
		this.achievementId = achievement.achievementId;
		this.achievementName = achievement.achievementName;
		this.achievementDescription = achievement.achievementDescription;
		this.createdAt = achievement.createdAt;
	}
	
	/**
	 * @param achievementId
	 * @param achievementName
	 * @param achievementDescription
	 * @param createdAt
	 */
	public Achievement(long achievementId, String achievementName, String achievementDescription, Date createdAt) {
		super();
		this.achievementId = achievementId;
		this.achievementName = achievementName;
		this.achievementDescription = achievementDescription;
		this.createdAt = createdAt;
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
	 * @return the achievementName
	 */
	public String getAchievementName() {
		return achievementName;
	}

	/**
	 * @param achievementName the achievementName to set
	 */
	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}

	/**
	 * @return the achievementDescription
	 */
	public String getAchievementDescription() {
		return achievementDescription;
	}

	/**
	 * @param achievementDescription the achievementDescription to set
	 */
	public void setAchievementDescription(String achievementDescription) {
		this.achievementDescription = achievementDescription;
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
