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
	
	@Size(min = 1, max = 10)
	@Column(name = "gender")
	String gender;

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
	
	@Column(name = "about")
	String about;
	
	@Column(name = "profilePicPath")
	String profilePicPath;
	
	public User() {
	}

	public User(long userId, String firstName, String lastName, String gender, String dateOfBirth,
			String contactNo,String emailId, String userTypeId, boolean isVerified, String about, String profilePicPath) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.userTypeId = userTypeId;
		this.isVerified = isVerified;
		this.about = about;
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
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	
	public String getAbout(){
		return about;
	}
	
	public void setAbout(String about){
		this.about = about;
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
