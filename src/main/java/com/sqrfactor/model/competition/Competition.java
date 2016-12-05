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
@Table(name = "competition_details")
public class Competition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compId")
	long compId;
	
	@Column(name = "userId")
	long userId;
	
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
	
	@Column(name="compECriteriaOthers")
	private String compECriteriaOthers;
	
	@Column(name="coverPic")
	private String coverPic;
	
	@Column(name="profilePic")
	private String profilePic;
	
	@Column(name="compSubRqrmts")
	private String compSubRqrmts;
	
	@Column(name="compFAQ")
	private String compFAQ;
	
	@Column(name="compStartDate")
	private String compStartDate;
	
	@Column(name="compEndDate")
	private String compEndDate;
	
	@Column(name="compSubEndDate")
	private String compSubEndDate;
	
	@Column(name="compResultDate")
	private String compResultDate;
	
	@Column(name="compDetails")
	private String compDetails;

	@Column(name="compBriefPath")
	private String compBriefPath;
	
	@Column(name="compFees")
	private String compFees;
	
	@Column(name="compCurrency")
	private String compCurrency;
	
	/**
	 * 
	 */
	public Competition() {
		super();
	}
	
	/**
	 * @param compId
	 * @param userId
	 * @param compType
	 * @param compTitle
	 * @param compHeading
	 * @param compBrief
	 * @param compECriteria
	 * @param coverPic
	 * @param profilePic
	 * @param compSubRqrmts
	 * @param compFAQ
	 * @param compStartDate
	 * @param compEndDate
	 * @param compSubEndDate
	 * @param compResultDate
	 * @param compDetails
	 * @param compBriefPath
	 * @param compFees
	 * @param compCurrency
	 */
	public Competition(long compId, long userId, String compType, String compTitle, String compHeading,
			String compBrief, String compECriteria, String compECriteriaOthers, String coverPic, String profilePic, String compSubRqrmts,
			String compFAQ, String compStartDate, String compEndDate, String compSubEndDate, String compResultDate,
			String compDetails, String compBriefPath, String compFees, String compCurrency) {
		super();
		this.compId = compId;
		this.userId = userId;
		this.compType = compType;
		this.compTitle = compTitle;
		this.compHeading = compHeading;
		this.compBrief = compBrief;
		this.compECriteria = compECriteria;
		this.compECriteriaOthers = compECriteriaOthers;
		this.coverPic = coverPic;
		this.profilePic = profilePic;
		this.compSubRqrmts = compSubRqrmts;
		this.compFAQ = compFAQ;
		this.compStartDate = compStartDate;
		this.compEndDate = compEndDate;
		this.compSubEndDate = compSubEndDate;
		this.compResultDate = compResultDate;
		this.compDetails = compDetails;
		this.compBriefPath = compBriefPath;
		this.compFees = compFees;
		this.compCurrency = compCurrency;
	}

	/**
	 * @param compId
	 * @param userId
	 * @param compType
	 * @param compTitle
	 * @param compHeading
	 * @param compBrief
	 * @param compECriteria
	 * @param coverPic
	 * @param profilePic
	 * @param compSubRqrmts
	 * @param compFAQ
	 * @param compStartDate
	 * @param compEndDate
	 * @param compSubEndDate
	 * @param compResultDate
	 * @param compDetails
	 * @param compBriefPath
	 * @param compFees
	 * @param compCurrency
	 */
	public Competition(Competition competition) {
		super();
		this.compId = competition.getCompId();
		this.userId = competition.getUserId();
		this.compType = competition.getCompType();
		this.compTitle = competition.getCompTitle();
		this.compHeading = competition.getCompHeading();
		this.compBrief = competition.getCompBrief();
		this.compECriteria = competition.getCompECriteria();
		this.compECriteriaOthers = competition.getCompECriteriaOthers();
		this.coverPic = competition.getCoverPic();
		this.profilePic = competition.getProfilePic();
		this.compSubRqrmts = competition.getCompSubRqrmts();
		this.compFAQ = competition.getCompFAQ();
		this.compStartDate = competition.getCompStartDate();
		this.compEndDate = competition.getCompEndDate();
		this.compSubEndDate = competition.getCompSubEndDate();
		this.compResultDate = competition.getCompResultDate();
		this.compDetails = competition.getCompDetails();
		this.compBriefPath = competition.getCompBriefPath();
		this.compFees = competition.getCompFees();
		this.compCurrency = competition.getCompCurrency();
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
	 * @return the compECriteriaOthers
	 */
	public String getCompECriteriaOthers() {
		return compECriteriaOthers;
	}

	/**
	 * @param compECriteriaOthers the compECriteriaOthers to set
	 */
	public void setCompECriteriaOthers(String compECriteriaOthers) {
		this.compECriteriaOthers = compECriteriaOthers;
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
	public String getCompStartDate() {
		return compStartDate;
	}

	/**
	 * @param compStartDate the compStartDate to set
	 */
	public void setCompStartDate(String compStartDate) {
		this.compStartDate = compStartDate;
	}

	/**
	 * @return the compEndDate
	 */
	public String getCompEndDate() {
		return compEndDate;
	}

	/**
	 * @param compEndDate the compEndDate to set
	 */
	public void setCompEndDate(String compEndDate) {
		this.compEndDate = compEndDate;
	}

	/**
	 * @return the compSubEndDate
	 */
	public String getCompSubEndDate() {
		return compSubEndDate;
	}

	/**
	 * @param compSubEndDate the compSubEndDate to set
	 */
	public void setCompSubEndDate(String compSubEndDate) {
		this.compSubEndDate = compSubEndDate;
	}

	/**
	 * @return the compResultDate
	 */
	public String getCompResultDate() {
		return compResultDate;
	}

	/**
	 * @param compResultDate the compResultDate to set
	 */
	public void setCompResultDate(String compResultDate) {
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

	/**
	 * @return the compBriefPath
	 */
	public String getCompBriefPath() {
		return compBriefPath;
	}

	/**
	 * @param compBriefPath the compBriefPath to set
	 */
	public void setCompBriefPath(String compBriefPath) {
		this.compBriefPath = compBriefPath;
	}

	/**
	 * @return the compFees
	 */
	public String getCompFees() {
		return compFees;
	}

	/**
	 * @param compFees the compFees to set
	 */
	public void setCompFees(String compFees) {
		this.compFees = compFees;
	}

	/**
	 * @return the compCurrency
	 */
	public String getCompCurrency() {
		return compCurrency;
	}

	/**
	 * @param compCurrency the compCurrency to set
	 */
	public void setCompCurrency(String compCurrency) {
		this.compCurrency = compCurrency;
	}
	
}
