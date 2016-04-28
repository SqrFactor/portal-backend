/**
 * 
 */
package com.sqrfactor.model;

/**
 * @author Angad Gill
 *
 */
public class EnrichedMessage extends Message {

	private String senderUserName;
	private String recipientUserName;
	private String senderProfilePicPath;
	private String recipientProfilePicPath;
	
	public EnrichedMessage(EnrichedMessage enrichedMessage){
		this.senderUserName = enrichedMessage.getSenderUserName();
		this.recipientUserName = enrichedMessage.getRecipientUserName();
		this.senderProfilePicPath = enrichedMessage.getSenderProfilePicPath();
		this.recipientProfilePicPath = enrichedMessage.getRecipientProfilePicPath();
	}
	
	public EnrichedMessage(Message message, String senderUserName, String recipientUserName, String senderProfilePicPath, String recipientProfilePicPath){
		super(message);
		this.senderUserName = senderUserName;
		this.recipientUserName = recipientUserName;
		this.senderProfilePicPath = senderProfilePicPath;
		this.recipientProfilePicPath = recipientProfilePicPath;
	}

	/**
	 * @return the senderUserName
	 */
	public String getSenderUserName() {
		return senderUserName;
	}

	/**
	 * @param senderUserName the senderUserName to set
	 */
	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	/**
	 * @return the recipientUserName
	 */
	public String getRecipientUserName() {
		return recipientUserName;
	}

	/**
	 * @param recipientUserName the recipientUserName to set
	 */
	public void setRecipientUserName(String recipientUserName) {
		this.recipientUserName = recipientUserName;
	}

	/**
	 * @return the senderProfilePicPath
	 */
	public String getSenderProfilePicPath() {
		return senderProfilePicPath;
	}

	/**
	 * @param senderProfilePicPath the senderProfilePicPath to set
	 */
	public void setSenderProfilePicPath(String senderProfilePicPath) {
		this.senderProfilePicPath = senderProfilePicPath;
	}

	/**
	 * @return the recipientProfilePicPath
	 */
	public String getRecipientProfilePicPath() {
		return recipientProfilePicPath;
	}

	/**
	 * @param recipientProfilePicPath the recipientProfilePicPath to set
	 */
	public void setRecipientProfilePicPath(String recipientProfilePicPath) {
		this.recipientProfilePicPath = recipientProfilePicPath;
	}
	
	
}
