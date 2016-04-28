/**
 * 
 */
package com.sqrfactor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angad Gill
 *
 */
public class EnrichedUserMessage{
	
	private long userId;
	private String userName;
	private String profilePicPath;
	private List<EnrichedMessage> enrichedMessages = new ArrayList<>();
	
	/**
	 * @param enrichedMessage
	 * @param userId
	 * @param userName
	 * @param profilePicPath
	 * @param enrichedMessages
	 */
	public EnrichedUserMessage(long userId, String userName, String profilePicPath, List<EnrichedMessage> enrichedMessages) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.profilePicPath = profilePicPath;
		this.enrichedMessages = enrichedMessages;
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
	 * @return the enrichedMessages
	 */
	public List<EnrichedMessage> getEnrichedMessages() {
		return enrichedMessages;
	}

	/**
	 * @param enrichedMessages the enrichedMessages to set
	 */
	public void setEnrichedMessages(List<EnrichedMessage> enrichedMessages) {
		this.enrichedMessages = enrichedMessages;
	}
	
}
