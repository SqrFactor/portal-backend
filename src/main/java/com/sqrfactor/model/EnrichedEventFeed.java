/**
 * 
 */
package com.sqrfactor.model;

import com.sqrfactor.model.competition.EventFeed;

/**
 * @author Angad Gill
 *
 */
public class EnrichedEventFeed extends EventFeed{

	private String name;
	private String profilePicPath;
	
	public EnrichedEventFeed(EventFeed eventFeed, String name, String profilePicPath){
		super(eventFeed);
		this.name = name;
		this.profilePicPath = profilePicPath;
	}
	
	/**
	 * @return the profilePicPath
	 */
	public String getProfilePicPath() {
		return profilePicPath;
	}

	/**
	 * @param profilePicPath the profilePicPath to set
	 */
	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
