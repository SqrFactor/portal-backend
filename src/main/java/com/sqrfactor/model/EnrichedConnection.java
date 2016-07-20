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
	
	public EnrichedConnection(Connection connection, String sourceUserTypeId, String destinationUserTypeId){
		super(connection);
		this.sourceUserTypeId = sourceUserTypeId;
		this.destinationUserTypeId = destinationUserTypeId;
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
}
