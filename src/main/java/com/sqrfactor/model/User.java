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
	
	@Column(name = "usrCountry")
	String usrCountry;
	
	@Column(name = "usrState")
	String usrState;
	
	@Column(name = "usrCity")
	String usrCity;
	
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

	/**
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param contactNo
	 * @param emailId
	 * @param usrCountry
	 * @param usrState
	 * @param usrCity
	 * @param dateOfBirth
	 * @param userTypeId
	 * @param isVerified
	 * @param about
	 * @param profilePicPath
	 */
	public User(long userId, String firstName, String lastName, String gender, String contactNo, String emailId,
			String usrCountry, String usrState, String usrCity, String dateOfBirth, String userTypeId,
			boolean isVerified, String about, String profilePicPath) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.usrCountry = usrCountry;
		this.usrState = usrState;
		this.usrCity = usrCity;
		this.dateOfBirth = dateOfBirth;
		this.userTypeId = userTypeId;
		this.isVerified = isVerified;
		this.about = about;
		this.profilePicPath = profilePicPath;
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

	/**
	 * @return the usrCountry
	 */
	public String getUsrCountry() {
		return usrCountry;
	}

	/**
	 * @param usrCountry the usrCountry to set
	 */
	public void setUsrCountry(String usrCountry) {
		this.usrCountry = usrCountry;
	}

	/**
	 * @return the usrState
	 */
	public String getUsrState() {
		return usrState;
	}

	/**
	 * @param usrState the usrState to set
	 */
	public void setUsrState(String usrState) {
		this.usrState = usrState;
	}

	/**
	 * @return the usrCity
	 */
	public String getUsrCity() {
		return usrCity;
	}

	/**
	 * @param usrCity the usrCity to set
	 */
	public void setUsrCity(String usrCity) {
		this.usrCity = usrCity;
	}
}
