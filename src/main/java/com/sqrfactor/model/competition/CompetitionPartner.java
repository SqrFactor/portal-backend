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
@Table(name = "competition_partners")
public class CompetitionPartner {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="compPartnerId")
	private long compPartnerId;
	
	@Column(name = "compId")
	long compId;
	
	@Column(name="partnerName")
	private String partnerName;
	
	@Column(name="partnerWebsite")
	private String partnerWebsite;

	@Column(name="partnerEmail")
	private String partnerEmail;

	@Column(name="partnerContactNo")
	private String partnerContactNo;

	@Column(name="partnerPic")
	private String partnerPic;
	
	/**
	 * 
	 */
	public CompetitionPartner() {
		super();
	}
	
	/**
	 * @return the compPartnerId
	 */
	public long getCompPartnerId() {
		return compPartnerId;
	}

	/**
	 * @param compPartnerId the compPartnerId to set
	 */
	public void setCompPartnerId(long compPartnerId) {
		this.compPartnerId = compPartnerId;
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
	 * @return the partnerName
	 */
	public String getPartnerName() {
		return partnerName;
	}

	/**
	 * @param partnerName the partnerName to set
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	/**
	 * @return the partnerWebsite
	 */
	public String getPartnerWebsite() {
		return partnerWebsite;
	}

	/**
	 * @param partnerWebsite the partnerWebsite to set
	 */
	public void setPartnerWebsite(String partnerWebsite) {
		this.partnerWebsite = partnerWebsite;
	}

	/**
	 * @return the partnerEmail
	 */
	public String getPartnerEmail() {
		return partnerEmail;
	}

	/**
	 * @param partnerEmail the partnerEmail to set
	 */
	public void setPartnerEmail(String partnerEmail) {
		this.partnerEmail = partnerEmail;
	}

	/**
	 * @return the partnerContactNo
	 */
	public String getPartnerContactNo() {
		return partnerContactNo;
	}

	/**
	 * @param partnerContactNo the partnerContactNo to set
	 */
	public void setPartnerContactNo(String partnerContactNo) {
		this.partnerContactNo = partnerContactNo;
	}

	/**
	 * @return the partnerPic
	 */
	public String getPartnerPic() {
		return partnerPic;
	}

	/**
	 * @param partnerPic the partnerPic to set
	 */
	public void setPartnerPic(String partnerPic) {
		this.partnerPic = partnerPic;
	}
}
