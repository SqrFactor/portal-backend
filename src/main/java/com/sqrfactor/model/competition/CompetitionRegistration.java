/**
 * 
 */
package com.sqrfactor.model.competition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "competition_registration")
public class CompetitionRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compRegistrationId")
	long compRegistrationId;
	
	@Column(name = "compId")
	long compId;
	
	@Column(name = "userId")
	long userId;
	
	public CompetitionRegistration(){}

	/**
	 * @return the compRegistrationId
	 */
	public long getCompRegistrationId() {
		return compRegistrationId;
	}

	/**
	 * @param compRegistrationId the compRegistrationId to set
	 */
	public void setCompRegistrationId(long compRegistrationId) {
		this.compRegistrationId = compRegistrationId;
	}

	/**
	 * @return the compId
	 */
	public long getCompId() {
		return compId;
	}

	/**
	 * @param compId the compId to set
	 */
	public void setCompId(long compId) {
		this.compId = compId;
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
	
}
