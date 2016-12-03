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
@Table(name = "competition_submission")
public class CompetitionSubmission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compSubmissionId")
	long compSubmissionId;
	
	@Column(name = "compId" , nullable = false)
	long compId;
	
	@Column(name="compTeamCode" , nullable = false)
	private String compTeamCode;
	
	@Column(name="filePath")
	private String filePath;
	
	@Column(name = "submittedByUserId" , nullable = false)
	long submittedByUserId;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
	
	public CompetitionSubmission(){}
	
	/**
	 * @param compSubmissionId
	 * @param compId
	 * @param compTeamCode
	 * @param filePath
	 * @param submittedByUserId
	 * @param createdAt
	 */
	public CompetitionSubmission(long compSubmissionId, long compId, String compTeamCode, String filePath,
			long submittedByUserId, Date createdAt) {
		super();
		this.compSubmissionId = compSubmissionId;
		this.compId = compId;
		this.compTeamCode = compTeamCode;
		this.filePath = filePath;
		this.submittedByUserId = submittedByUserId;
		this.createdAt = createdAt;
	}

	public CompetitionSubmission(CompetitionSubmission competitionSubmission){
		super();
		this.compSubmissionId = competitionSubmission.getCompSubmissionId();
		this.compId = competitionSubmission.getCompId();
		this.compTeamCode = competitionSubmission.getCompTeamCode();
		this.filePath = competitionSubmission.getFilePath();
		this.submittedByUserId = competitionSubmission.getSubmittedByUserId();
		this.createdAt = competitionSubmission.getCreatedAt();
	}


	/**
	 * @return the compSubmissionId
	 */
	public long getCompSubmissionId() {
		return compSubmissionId;
	}

	/**
	 * @param compSubmissionId the compSubmissionId to set
	 */
	public void setCompSubmissionId(long compSubmissionId) {
		this.compSubmissionId = compSubmissionId;
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
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
