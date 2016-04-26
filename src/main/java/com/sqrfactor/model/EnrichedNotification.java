/**
 * 
 */
package com.sqrfactor.model;

/**
 * @author Angad Gill
 *
 */
public class EnrichedNotification extends Notification{

	private String sourceUserName;
	private String destinationUserName;
	
	public EnrichedNotification(Notification notification, String sourceUserName, String destinationUserName) {
		super(notification);
		this.sourceUserName = sourceUserName;
		this.destinationUserName = destinationUserName;
	}

	/**
	 * @return the sourceUserName
	 */
	public String getSourceUserName() {
		return sourceUserName;
	}

	/**
	 * @param sourceUserName the sourceUserName to set
	 */
	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}

	/**
	 * @return the destinationUserName
	 */
	public String getDestinationUserName() {
		return destinationUserName;
	}

	/**
	 * @param destinationUserName the destinationUserName to set
	 */
	public void setDestinationUserName(String destinationUserName) {
		this.destinationUserName = destinationUserName;
	}
}
