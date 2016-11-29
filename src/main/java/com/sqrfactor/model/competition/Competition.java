/**
 * 
 */
package com.sqrfactor.model.competition;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "competition_details")
public class Competition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compId")
	long compId;
	
	@Column(name="compType" , nullable = false)
	private String compType;
	
	@Column(name="compTitle" , nullable = false)
	private String compTitle;
	
	@Column(name="compHeading")
	private String compHeading;
	
	@Column(name="compBrief")
	private String compBrief;

	@Column(name="compECriteria")
	private String compECriteria;
	
	@Column(name="coverPic")
	private String coverPic;
	
	@Column(name="profilePic")
	private String profilePic;
	
	@Column(name="compSubRqrmts")
	private String compSubRqrmts;
	
	@Column(name="compFAQ")
	private String compFAQ;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="compStartDate")
	private Date compStartDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="compEndDate")
	private Date compEndDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="compSubEndDate")
	private Date compSubEndDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="compResultDate")
	private Date compResultDate;
	
	@Column(name="compDetails")
	private String compDetails;
	
	/**
	 * 
	 */
	public Competition() {
		super();
	
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
	 * @return the compType
	 */
	public String getCompType() {
		return compType;
	}

	/**
	 * @param compType the compType to set
	 */
	public void setCompType(String compType) {
		this.compType = compType;
	}

	/**
	 * @return the compTitle
	 */
	public String getCompTitle() {
		return compTitle;
	}

	/**
	 * @param compTitle the compTitle to set
	 */
	public void setCompTitle(String compTitle) {
		this.compTitle = compTitle;
	}

	/**
	 * @return the compHeading
	 */
	public String getCompHeading() {
		return compHeading;
	}

	/**
	 * @param compHeading the compHeading to set
	 */
	public void setCompHeading(String compHeading) {
		this.compHeading = compHeading;
	}

	/**
	 * @return the compBrief
	 */
	public String getCompBrief() {
		return compBrief;
	}

	/**
	 * @param compBrief the compBrief to set
	 */
	public void setCompBrief(String compBrief) {
		this.compBrief = compBrief;
	}

	/**
	 * @return the compECriteria
	 */
	public String getCompECriteria() {
		return compECriteria;
	}

	/**
	 * @param compECriteria the compECriteria to set
	 */
	public void setCompECriteria(String compECriteria) {
		this.compECriteria = compECriteria;
	}

	/**
	 * @return the coverPic
	 */
	public String getCoverPic() {
		return coverPic;
	}

	/**
	 * @param coverPic the coverPic to set
	 */
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	/**
	 * @return the profilePic
	 */
	public String getProfilePic() {
		return profilePic;
	}

	/**
	 * @param profilePic the profilePic to set
	 */
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	/**
	 * @return the compSubRqrmts
	 */
	public String getCompSubRqrmts() {
		return compSubRqrmts;
	}

	/**
	 * @param compSubRqrmts the compSubRqrmts to set
	 */
	public void setCompSubRqrmts(String compSubRqrmts) {
		this.compSubRqrmts = compSubRqrmts;
	}

	/**
	 * @return the compFAQ
	 */
	public String getCompFAQ() {
		return compFAQ;
	}

	/**
	 * @param compFAQ the compFAQ to set
	 */
	public void setCompFAQ(String compFAQ) {
		this.compFAQ = compFAQ;
	}

	/**
	 * @return the compStartDate
	 */
	public Date getCompStartDate() {
		return compStartDate;
	}

	/**
	 * @param compStartDate the compStartDate to set
	 */
	public void setCompStartDate(Date compStartDate) {
		this.compStartDate = compStartDate;
	}

	/**
	 * @return the compEndDate
	 */
	public Date getCompEndDate() {
		return compEndDate;
	}

	/**
	 * @param compEndDate the compEndDate to set
	 */
	public void setCompEndDate(Date compEndDate) {
		this.compEndDate = compEndDate;
	}

	/**
	 * @return the compSubEndDate
	 */
	public Date getCompSubEndDate() {
		return compSubEndDate;
	}

	/**
	 * @param compSubEndDate the compSubEndDate to set
	 */
	public void setCompSubEndDate(Date compSubEndDate) {
		this.compSubEndDate = compSubEndDate;
	}

	/**
	 * @return the compResultDate
	 */
	public Date getCompResultDate() {
		return compResultDate;
	}

	/**
	 * @param compResultDate the compResultDate to set
	 */
	public void setCompResultDate(Date compResultDate) {
		this.compResultDate = compResultDate;
	}

	/**
	 * @return the compDetails
	 */
	public String getCompDetails() {
		return compDetails;
	}

	/**
	 * @param compDetails the compDetails to set
	 */
	public void setCompDetails(String compDetails) {
		this.compDetails = compDetails;
	}
	
}
