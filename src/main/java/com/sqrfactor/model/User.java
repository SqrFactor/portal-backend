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
	
	@Column(name = "dob")
	String dob;
	
	@Column(name = "colCode")
	String colCode;

	@Column(name = "highGrad")
	String highGrad;
	
	@Column(name = "yearGrad")
	String yearGrad;
	
	@Column(name = "userTypeId", nullable = false)
	String userTypeId;
	
	@Column(name = "isVerified")
	boolean isVerified; 
	
	public User() {
	}

	public User(long userId, String firstName, String lastName, String dob,
			String contactNo,String emailId,String colCode, String highGrad,
			String yearGrad, String userTypeId, boolean isVerified) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.dob = dob;
		this.colCode = colCode;
		this.highGrad = highGrad;
		this.yearGrad = yearGrad;
		this.userTypeId = userTypeId;
		this.isVerified = isVerified;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getColCode() {
		return colCode;
	}

	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	public String getHighGrad() {
		return highGrad;
	}

	public void setHighGrad(String highGrad) {
		this.highGrad = highGrad;
	}

	public String getYearGrad() {
		return yearGrad;
	}

	public void setYearGrad(String yearGrad) {
		this.yearGrad = yearGrad;
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
	
}
