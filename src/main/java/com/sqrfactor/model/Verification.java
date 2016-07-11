package com.sqrfactor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "verification_details")
public class Verification {

	@Id
	@Column(name = "verificationId")
	private long verificationId;
	
	@Column(name = "verificationUserId")
	private long verificationUserId;
	
	@Column(name = "emailCode")
	private String emailCode;
	
	@Column(name = "phoneCode")
	private String phoneCode;
	
	public Verification(){
		
	}

	/**
	 * @param verificationId
	 * @param verificationUserId
	 * @param emailCode
	 * @param phoneCode
	 */
	public Verification(long verificationId, long verificationUserId, String emailCode, String phoneCode) {
		super();
		this.verificationId = verificationId;
		this.verificationUserId = verificationUserId;
		this.emailCode = emailCode;
		this.phoneCode = phoneCode;
	}

	/**
	 * @return the verificationId
	 */
	public long getVerificationId() {
		return verificationId;
	}

	/**
	 * @param verificationId the verificationId to set
	 */
	public void setVerificationId(long verificationId) {
		this.verificationId = verificationId;
	}

	/**
	 * @return the verificationUserId
	 */
	public long getVerificationUserId() {
		return verificationUserId;
	}

	/**
	 * @param verificationUserId the verificationUserId to set
	 */
	public void setVerificationUserId(long verificationUserId) {
		this.verificationUserId = verificationUserId;
	}

	/**
	 * @return the emailCode
	 */
	public String getEmailCode() {
		return emailCode;
	}

	/**
	 * @param emailCode the emailCode to set
	 */
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	/**
	 * @return the phoneCode
	 */
	public String getPhoneCode() {
		return phoneCode;
	}

	/**
	 * @param phoneCode the phoneCode to set
	 */
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
}
