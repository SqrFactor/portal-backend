/**
 * 
 */
package com.sqrfactor.model;

/**
 * @author Angad Gill
 *
 */
public class EnrichedConnection extends Connection{

	private String sourceUserTypeId;
	private String destinationUserTypeId;
	private String destinationName;
	private String destinationProfilePicPath;
	
	public EnrichedConnection(Connection connection, String sourceUserTypeId, 
			String destinationUserTypeId, String destinationName, String destinationProfilePicPath){
		super(connection);
		this.sourceUserTypeId = sourceUserTypeId;
		this.destinationUserTypeId = destinationUserTypeId;
		this.destinationName = destinationName;
		this.destinationProfilePicPath = destinationProfilePicPath;
	}

	/**
	 * @return the sourceUserTypeId
	 */
	public String getSourceUserTypeId() {
		return sourceUserTypeId;
	}

	/**
	 * @param sourceUserTypeId the sourceUserTypeId to set
	 */
	public void setSourceUserTypeId(String sourceUserTypeId) {
		this.sourceUserTypeId = sourceUserTypeId;
	}

	/**
	 * @return the destinationUserTypeId
	 */
	public String getDestinationUserTypeId() {
		return destinationUserTypeId;
	}

	/**
	 * @param destinationUserTypeId the destinationUserTypeId to set
	 */
	public void setDestinationUserTypeId(String destinationUserTypeId) {
		this.destinationUserTypeId = destinationUserTypeId;
	}

	/**
	 * @return the destinationName
	 */
	public String getDestinationName() {
		return destinationName;
	}

	/**
	 * @param destinationName the destinationName to set
	 */
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
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
