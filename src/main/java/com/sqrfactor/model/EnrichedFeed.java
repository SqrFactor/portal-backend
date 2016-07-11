package com.sqrfactor.model;

public class EnrichedFeed extends Feed{
	
	private String name;
	private String profilePicPath;
	
	private long refUserId;
	private String refName;
	private String refProfilePicPath;
	public EnrichedFeed(Feed feed, String name, String profilePicPath, long refUserId, String refName, String refProfilePicPath) {
		super(feed);
		this.name = name;
		this.profilePicPath = profilePicPath;
		this.refUserId = refUserId;
		this.refName = refName;
		this.refProfilePicPath = refProfilePicPath;
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
	
	/**
	 * @return the refUserId
	 */
	public long getRefUserId() {
		return refUserId;
	}

	/**
	 * @param refUserId the refUserId to set
	 */
	public void setRefUserId(long refUserId) {
		this.refUserId = refUserId;
	}

	/**
	 * @return the refName
	 */
	public String getRefName() {
		return refName;
	}

	/**
	 * @param refName the refName to set
	 */
	public void setRefName(String refName) {
		this.refName = refName;
	}

	/**
	 * @return the refProfilePicPath
	 */
	public String getRefProfilePicPath() {
		return refProfilePicPath;
	}

	/**
	 * @param refProfilePicPath the refProfilePicPath to set
	 */
	public void setRefProfilePicPath(String refProfilePicPath) {
		this.refProfilePicPath = refProfilePicPath;
	}
	
}