/**
 * 
 */
package com.sqrfactor.model.competition;

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
@Table(name = "competition_awards")
public class CompetitionAward {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="compAwardId")
	private long compAwardId;

	@Column(name = "compId")
	long compId;
	
	@Column(name="awardType")
	private String awardType;
	
	@Column(name="awardPrice")
	private String awardPrice;

	@Column(name="awardDetails")
	private String awardDetails;

	/**
	 * 
	 */
	public CompetitionAward() {
		super();
	}
	
	/**
	 * @return the compAwardId
	 */
	public long getCompAwardId() {
		return compAwardId;
	}

	/**
	 * @param compAwardId the compAwardId to set
	 */
	public void setCompAwardId(long compAwardId) {
		this.compAwardId = compAwardId;
	}

	/**
	 * @return the compId
	 */
	public long getCompId() {
		return compId;
	}

	/**
	 * @param compId the compId to set
	 */
	public void setCompId(long compId) {
		this.compId = compId;
	}

	/**
	 * @return the awardType
	 */
	public String getAwardType() {
		return awardType;
	}

	/**
	 * @param awardType the awardType to set
	 */
	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	/**
	 * @return the awardPrice
	 */
	public String getAwardPrice() {
		return awardPrice;
	}

	/**
	 * @param awardPrice the awardPrice to set
	 */
	public void setAwardPrice(String awardPrice) {
		this.awardPrice = awardPrice;
	}

	/**
	 * @return the awardDetails
	 */
	public String getAwardDetails() {
		return awardDetails;
	}

	/**
	 * @param awardDetails the awardDetails to set
	 */
	public void setAwardDetails(String awardDetails) {
		this.awardDetails = awardDetails;
	}
}
