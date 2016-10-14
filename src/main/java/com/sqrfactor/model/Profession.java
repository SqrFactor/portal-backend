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
@Table(name = "profession_details")
public class Profession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	long id;

	@Column(name = "userId")
	long userId;
	
	@Column(name = "professionType", nullable = false)
	String professionType;
	
	@Column(name = "professionRole", nullable = true)
	String professionRole;
	
	@Column(name = "professionCompany", nullable = false)
	String professionCompany;
	
	@Column(name = "professionSalary", nullable = true)
	String professionSalary;
	
	@Column(name = "professionFromYear", nullable = false)
	String professionFromYear;
	
	@Column(name = "professionToYear", nullable = false)
	String professionToYear;
	
	public Profession() {
	}

	/**
	 * @param id
	 * @param userId
	 * @param professionType
	 * @param professionRole
	 * @param professionCompany
	 * @param professionSalary
	 * @param professionFromYear
	 * @param professionToYear
	 */
	public Profession(long id, long userId, String professionType, String professionRole, String professionCompany,
			String professionSalary, String professionFromYear, String professionToYear) {
		super();
		this.id = id;
		this.userId = userId;
		this.professionType = professionType;
		this.professionRole = professionRole;
		this.professionCompany = professionCompany;
		this.professionSalary = professionSalary;
		this.professionFromYear = professionFromYear;
		this.professionToYear = professionToYear;
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
	 * @return the professionType
	 */
	public String getProfessionType() {
		return professionType;
	}

	/**
	 * @param professionType the professionType to set
	 */
	public void setProfessionType(String professionType) {
		this.professionType = professionType;
	}

	/**
	 * @return the professionRole
	 */
	public String getProfessionRole() {
		return professionRole;
	}

	/**
	 * @param professionRole the professionRole to set
	 */
	public void setProfessionRole(String professionRole) {
		this.professionRole = professionRole;
	}

	/**
	 * @return the professionCompany
	 */
	public String getProfessionCompany() {
		return professionCompany;
	}

	/**
	 * @param professionCompany the professionCompany to set
	 */
	public void setProfessionCompany(String professionCompany) {
		this.professionCompany = professionCompany;
	}

	/**
	 * @return the professionSalary
	 */
	public String getProfessionSalary() {
		return professionSalary;
	}

	/**
	 * @param professionSalary the professionSalary to set
	 */
	public void setProfessionSalary(String professionSalary) {
		this.professionSalary = professionSalary;
	}

	/**
	 * @return the professionFromYear
	 */
	public String getProfessionFromYear() {
		return professionFromYear;
	}

	/**
	 * @param professionFromYear the professionFromYear to set
	 */
	public void setProfessionFromYear(String professionFromYear) {
		this.professionFromYear = professionFromYear;
	}

	/**
	 * @return the professionToYear
	 */
	public String getProfessionToYear() {
		return professionToYear;
	}

	/**
	 * @param professionToYear the professionToYear to set
	 */
	public void setProfessionToYear(String professionToYear) {
		this.professionToYear = professionToYear;
	}
}
