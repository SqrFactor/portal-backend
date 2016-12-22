/**
 * 
 */
package com.sqrfactor.model;

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
@Table(name = "education_details")
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	long id;

	@Column(name = "userId")
	long userId;
	
	@Column(name = "educationRegNo", nullable = true)
	String educationRegNo;
	
	@Column(name = "educationName", nullable = false)
	String educationName;
	
	@Column(name = "educationFromDate", nullable = false)
	String educationFromDate;
	
	@Column(name = "educationToDate", nullable = false)
	String educationToDate;
	
	@Column(name = "colCode", nullable = false)
	String colCode;
	
	public Education() {
	}

	/**
	 * @param id
	 * @param userId
	 * @param educationRegNo
	 * @param educationName
	 * @param educationFromYear
	 * @param educationToYear
	 * @param colCode
	 */
	public Education(long id, long userId, String educationRegNo, String educationName,
			String educationFromDate, String educationToDate, String colCode) {
		super();
		this.id = id;
		this.userId = userId;
		this.educationRegNo = educationRegNo;
		this.educationName = educationName;
		this.educationFromDate = educationFromDate;
		this.educationToDate = educationToDate;
		this.colCode = colCode;
	}
	
	public Education(Education education) {
		super();
		this.id = education.getId();
		this.userId = education.getUserId();
		this.educationRegNo = education.getEducationRegNo();
		this.educationName = education.getEducationName();
		this.educationFromDate = education.getEducationFromDate();
		this.educationToDate = education.getEducationToDate();
		this.colCode = education.getColCode();
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the educationName
	 */
	public String getEducationName() {
		return educationName;
	}

	/**
	 * @param educationName the educationName to set
	 */
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	/**
	 * @return the educationFromDate
	 */
	public String getEducationFromDate() {
		return educationFromDate;
	}

	/**
	 * @param educationFromDate the educationFromDate to set
	 */
	public void setEducationFromDate(String educationFromDate) {
		this.educationFromDate = educationFromDate;
	}

	/**
	 * @return the educationToDate
	 */
	public String getEducationToDate() {
		return educationToDate;
	}

	/**
	 * @param educationToDate the educationToDate to set
	 */
	public void setEducationToDate(String educationToDate) {
		this.educationToDate = educationToDate;
	}

	/**
	 * @return the colCode
	 */
	public String getColCode() {
		return colCode;
	}

	/**
	 * @param colCode the colCode to set
	 */
	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	/**
	 * @return the educationRegNo
	 */
	public String getEducationRegNo() {
		return educationRegNo;
	}

	/**
	 * @param educationRegNo the educationRegNo to set
	 */
	public void setEducationRegNo(String educationRegNo) {
		this.educationRegNo = educationRegNo;
	}
	
	
	
}
