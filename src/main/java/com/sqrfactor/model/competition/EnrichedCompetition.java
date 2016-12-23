/**
 * 
 */
package com.sqrfactor.model.competition;

/**
 * @author Angad Gill
 *
 */
public class EnrichedCompetition extends Competition{
	
	private String userName;
	
	public EnrichedCompetition(Competition competition, String userName){
		super(competition);
		this.userName = userName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
