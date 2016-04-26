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
	
	public EnrichedMessage(Message message, String senderUserName, String recipientUserName){
		super(message);
		this.senderUserName = senderUserName;
		this.recipientUserName = recipientUserName;
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
	
}
