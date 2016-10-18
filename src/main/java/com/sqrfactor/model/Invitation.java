/**
 * 
 */
package com.sqrfactor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "invitation_details")
public class Invitation {

	@Id
	@Column(name = "invitationId")
	private long invitationId;
	
	@Column(name = "invitedByUserId")
	private long invitedByUserId;
	
	@Column(name = "invitedToUserId")
	private long invitedToUserId;
		
	public Invitation(){
		
	}

	/**
	 * @param invitationId
	 * @param invitedByUserId
	 * @param invitedToUserId
	 */
	public Invitation(long invitationId, long invitedByUserId, long invitedToUserId) {
		super();
		this.invitationId = invitationId;
		this.invitedByUserId = invitedByUserId;
		this.invitedToUserId = invitedToUserId;
	}

	/**
	 * @return the invitationId
	 */
	public long getInvitationId() {
		return invitationId;
	}

	/**
	 * @param invitationId the invitationId to set
	 */
	public void setInvitationId(long invitationId) {
		this.invitationId = invitationId;
	}

	/**
	 * @return the invitedByUserId
	 */
	public long getInvitedByUserId() {
		return invitedByUserId;
	}

	/**
	 * @param invitedByUserId the invitedByUserId to set
	 */
	public void setInvitedByUserId(long invitedByUserId) {
		this.invitedByUserId = invitedByUserId;
	}

	/**
	 * @return the invitedToUserId
	 */
	public long getInvitedToUserId() {
		return invitedToUserId;
	}

	/**
	 * @param invitedToUserId the invitedToUserId to set
	 */
	public void setInvitedToUserId(long invitedToUserId) {
		this.invitedToUserId = invitedToUserId;
	}

}
