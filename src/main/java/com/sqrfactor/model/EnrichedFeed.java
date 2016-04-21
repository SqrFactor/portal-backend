package com.sqrfactor.model;

public class EnrichedFeed extends Feed{
	
	private String name;
	private String profilePicPath;
	public EnrichedFeed(Feed feed, String name, String profilePicPath) {
		super(feed);
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