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
@Table(name = "additional_profession_details")
public class AdditionalProfession {

	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "coaNumber")
	private String coaNumber;
	
	@Column(name = "iiaNumber")
	private String iiaNumber;
	
	public AdditionalProfession() {
		
	}

	/**
	 * @param id
	 * @param userId
	 * @param coaNumber
	 * @param iiaNumber
	 */
	public AdditionalProfession(long id, long userId, String coaNumber, String iiaNumber) {
		super();
		this.id = id;
		this.userId = userId;
		this.coaNumber = coaNumber;
		this.iiaNumber = iiaNumber;
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
	 * @return the coaNumber
	 */
	public String getCoaNumber() {
		return coaNumber;
	}

	/**
	 * @param coaNumber the coaNumber to set
	 */
	public void setCoaNumber(String coaNumber) {
		this.coaNumber = coaNumber;
	}

	/**
	 * @return the iiaNumber
	 */
	public String getIiaNumber() {
		return iiaNumber;
	}

	/**
	 * @param iiaNumber the iiaNumber to set
	 */
	public void setIiaNumber(String iiaNumber) {
		this.iiaNumber = iiaNumber;
	}
	
	

}
