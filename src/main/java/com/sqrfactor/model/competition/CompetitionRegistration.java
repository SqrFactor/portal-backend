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
	
	@Column(name = "compTeamCode")
	String compTeamCode;
	
	@Column(name = "compUserRole")
	String compUserRole;
	
	public CompetitionRegistration(){}
	
	/**
	 * @param compRegistrationId
	 * @param compId
	 * @param userId
	 * @param compTeamCode
	 * @param compUserRole
	 */
	public CompetitionRegistration(long compRegistrationId, long compId, long userId, String compTeamCode,
			String compUserRole) {
		super();
		this.compRegistrationId = compRegistrationId;
		this.compId = compId;
		this.userId = userId;
		this.compTeamCode = compTeamCode;
		this.compUserRole = compUserRole;
	}

	/**
	 * @param compRegistrationId
	 * @param compId
	 * @param userId
	 * @param compTeamCode
	 * @param compUserRole
	 */
	public CompetitionRegistration(CompetitionRegistration competitionRegistration) {
		super();
		this.compRegistrationId = competitionRegistration.getCompRegistrationId();
		this.compId = competitionRegistration.getCompId();
		this.userId = competitionRegistration.getUserId();
		this.compTeamCode = competitionRegistration.getCompTeamCode();
		this.compUserRole = competitionRegistration.getCompUserRole();
	}


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

	/**
	 * @return the compTeamCode
	 */
	public String getCompTeamCode() {
		return compTeamCode;
	}

	/**
	 * @param compTeamCode the compTeamCode to set
	 */
	public void setCompTeamCode(String compTeamCode) {
		this.compTeamCode = compTeamCode;
	}

	/**
	 * @return the compUserRole
	 */
	public String getCompUserRole() {
		return compUserRole;
	}

	/**
	 * @param compUserRole the compUserRole to set
	 */
	public void setCompUserRole(String compUserRole) {
		this.compUserRole = compUserRole;
	}
	
}
