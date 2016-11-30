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
@Table(name = "competition_jury")
public class CompetitionJury {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="compJuryId")
	private long compJuryId;

	@Column(name = "compId")
	long compId;
	
	@Column(name="juryName")
	private String juryName;
	
	@Column(name="juryFirmName")
	private String juryFirmName;
	
	@Column(name="juryEmail")
	private String juryEmail;
	
	@Column(name="juryContactNo")
	private String juryContactNo;

	@Column(name="juryPic")
	private String juryPic;
	
	@Column(name="juryDetails")
	private String juryDetails;

	/**
	 * 
	 */
	public CompetitionJury() {
		super();
		
	}

	/**
	 * @return the compJuryId
	 */
	public long getCompJuryId() {
		return compJuryId;
	}

	/**
	 * @param compJuryId the compJuryId to set
	 */
	public void setCompJuryId(long compJuryId) {
		this.compJuryId = compJuryId;
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
	 * @return the juryName
	 */
	public String getJuryName() {
		return juryName;
	}

	/**
	 * @param juryName the juryName to set
	 */
	public void setJuryName(String juryName) {
		this.juryName = juryName;
	}

	/**
	 * @return the juryFirmName
	 */
	public String getJuryFirmName() {
		return juryFirmName;
	}

	/**
	 * @param juryFirmName the juryFirmName to set
	 */
	public void setJuryFirmName(String juryFirmName) {
		this.juryFirmName = juryFirmName;
	}

	/**
	 * @return the juryEmail
	 */
	public String getJuryEmail() {
		return juryEmail;
	}

	/**
	 * @param juryEmail the juryEmail to set
	 */
	public void setJuryEmail(String juryEmail) {
		this.juryEmail = juryEmail;
	}

	/**
	 * @return the juryContactNo
	 */
	public String getJuryContactNo() {
		return juryContactNo;
	}

	/**
	 * @param juryContactNo the juryContactNo to set
	 */
	public void setJuryContactNo(String juryContactNo) {
		this.juryContactNo = juryContactNo;
	}

	/**
	 * @return the juryPic
	 */
	public String getJuryPic() {
		return juryPic;
	}

	/**
	 * @param juryPic the juryPic to set
	 */
	public void setJuryPic(String juryPic) {
		this.juryPic = juryPic;
	}

	/**
	 * @return the juryDetails
	 */
	public String getJuryDetails() {
		return juryDetails;
	}

	/**
	 * @param juryDetails the juryDetails to set
	 */
	public void setJuryDetails(String juryDetails) {
		this.juryDetails = juryDetails;
	}

}
