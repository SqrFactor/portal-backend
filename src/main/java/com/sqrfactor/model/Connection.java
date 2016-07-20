package com.sqrfactor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "connection_details")
public class Connection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "connectionId")
	long connectionId;
	
	@Column(name = "sourceId")
	long sourceId;
	
	@Column(name = "destinationId")
	long destinationId;
	
	public Connection(){
		
	}
	
	public Connection(Connection connection){
		super();
		this.connectionId = connection.getConnectionId();
		this.sourceId = connection.getSourceId();
		this.destinationId = connection.getDestinationId();
	}
	
	public Connection(long sourceId, long destinationId){
		super();
		this.sourceId = sourceId;
		this.destinationId = destinationId;
	}

	/**
	 * @return the connectionId
	 */
	public long getConnectionId() {
		return connectionId;
	}

	/**
	 * @param connectionId the connectionId to set
	 */
	public void setConnectionId(long connectionId) {
		this.connectionId = connectionId;
	}

	/**
	 * @return the sourceId
	 */
	public long getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the destinationId
	 */
	public long getDestinationId() {
		return destinationId;
	}

	/**
	 * @param destinationId the destinationId to set
	 */
	public void setDestinationId(long destinationId) {
		this.destinationId = destinationId;
	}
}