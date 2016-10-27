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
	private String sourceProfilePicPath;
	private String destinationProfilePicPath;
	
	public EnrichedNotification(Notification notification, String sourceUserName, String destinationUserName, String sourceProfilePicPath, String destinationProfilePicPath) {
		super(notification);
		this.sourceUserName = sourceUserName;
		this.destinationUserName = destinationUserName;
		this.sourceProfilePicPath = sourceProfilePicPath;
		this.destinationProfilePicPath = destinationProfilePicPath;
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

	/**
	 * @return the sourceProfilePicPath
	 */
	public String getSourceProfilePicPath() {
		return sourceProfilePicPath;
	}

	/**
	 * @param sourceProfilePicPath the sourceProfilePicPath to set
	 */
	public void setSourceProfilePicPath(String sourceProfilePicPath) {
		this.sourceProfilePicPath = sourceProfilePicPath;
	}

	/**
	 * @return the destinationProfilePicPath
	 */
	public String getDestinationProfilePicPath() {
		return destinationProfilePicPath;
	}

	/**
	 * @param destinationProfilePicPath the destinationProfilePicPath to set
	 */
	public void setDestinationProfilePicPath(String destinationProfilePicPath) {
		this.destinationProfilePicPath = destinationProfilePicPath;
	}
}
