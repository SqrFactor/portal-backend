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
@Table(name = "competition_result")
public class CompetitionResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compResultId")
	long compResultId;
	
	@Column(name = "compId" , nullable = false)
	long compId;
	
	@Column(name="compTeamCode" , nullable = false)
	private String compTeamCode;
	
	@Column(name="compAwardId" , nullable = false)
	private long compAwardId;
	
	@Column(name = "submittedByUserId" , nullable = false)
	long submittedByUserId;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
	
	public CompetitionResult(){}

	/**
	 * @return the compResultId
	 */
	public long getCompResultId() {
		return compResultId;
	}

	/**
	 * @param compResultId the compResultId to set
	 */
	public void setCompResultId(long compResultId) {
		this.compResultId = compResultId;
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
	 * @return the compTeamCode
	 */
	public String getCompTeamCode() {
		return compTeamCode;
	}

	/**
	 * @param compTeamCode the compTeamCode to set
	 */
	public void setCompTeamCode(String compTeamCode) {
		this.compTeamCode = compTeamCode;
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
	 * @return the submittedByUserId
	 */
	public long getSubmittedByUserId() {
		return submittedByUserId;
	}

	/**
	 * @param submittedByUserId the submittedByUserId to set
	 */
	public void setSubmittedByUserId(long submittedByUserId) {
		this.submittedByUserId = submittedByUserId;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
