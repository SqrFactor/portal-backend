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
	
	@Column(name = "educationFromYear", nullable = false)
	String educationFromYear;
	
	@Column(name = "educationToYear", nullable = false)
	String educationToYear;
	
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
			String educationFromYear, String educationToYear, String colCode) {
		super();
		this.id = id;
		this.userId = userId;
		this.educationRegNo = educationRegNo;
		this.educationName = educationName;
		this.educationFromYear = educationFromYear;
		this.educationToYear = educationToYear;
		this.colCode = colCode;
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
	 * @return the educationFromYear
	 */
	public String getEducationFromYear() {
		return educationFromYear;
	}

	/**
	 * @param educationFromYear the educationFromYear to set
	 */
	public void setEducationFromYear(String educationFromYear) {
		this.educationFromYear = educationFromYear;
	}

	/**
	 * @return the educationToYear
	 */
	public String getEducationToYear() {
		return educationToYear;
	}

	/**
	 * @param educationToYear the educationToYear to set
	 */
	public void setEducationToYear(String educationToYear) {
		this.educationToYear = educationToYear;
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
