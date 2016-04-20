package com.sqrfactor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	long userId;

	@Size(min = 3, max = 50)
	@Column(name = "firstName", nullable = false)
	String firstName;

	@Size(min = 3, max = 50)
	@Column(name = "lastName")
	String lastName;

	@Size(min = 9, max = 12)
	@Column(name = "contactNo", nullable = false)
	String contactNo;
	
	@Size(min = 3, max = 50)
	@Column(name = "emailId", nullable = false)
	String emailId;
	
	@Column(name = "dateOfBirth")
	String dateOfBirth;
		
	@Column(name = "userTypeId", nullable = false)
	String userTypeId;
	
	@Column(name = "isVerified")
	boolean isVerified; 
	
	@Column(name = "profilePicPath")
	String profilePicPath;
	
	public User() {
	}

	public User(long userId, String firstName, String lastName, String dateOfBirth,
			String contactNo,String emailId, String userTypeId, boolean isVerified, String profilePicPath) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.userTypeId = userTypeId;
		this.isVerified = isVerified;
		this.profilePicPath  = profilePicPath;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	/**
	 * @return the profilePicPath
	 */
	public String getProfilePicPath() {
		return profilePicPath;
	}

	/**
	 * @param profilePicPath the profilePicPath to set
	 */
	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}
	
	
	
}
