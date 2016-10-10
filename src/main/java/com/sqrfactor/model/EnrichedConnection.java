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
	private String sourceName;
	private String sourceProfilePicPath;
	
	public EnrichedConnection(Connection connection, String sourceUserTypeId, 
			String destinationUserTypeId, String destinationName, String destinationProfilePicPath, String sourceName, String sourceProfilePicPath){
		super(connection);
		this.sourceUserTypeId = sourceUserTypeId;
		this.destinationUserTypeId = destinationUserTypeId;
		this.destinationName = destinationName;
		this.destinationProfilePicPath = destinationProfilePicPath;
		this.sourceName = sourceName;
		this.sourceProfilePicPath = sourceProfilePicPath;
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

	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
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
	
}
