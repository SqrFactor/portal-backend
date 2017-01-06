/**
 * 
 */
package com.sqrfactor.model;

import com.sqrfactor.model.competition.Competition;

/**
 * @author Angad Gill
 *
 */
public class EnrichedAnnouncement extends Announcement{

	private String userName;
	
	public EnrichedAnnouncement(Announcement announcement, String userName){
		super(announcement);
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
