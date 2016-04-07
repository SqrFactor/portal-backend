/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Connection;

/**
 * @author Angad Gill
 *
 */
public interface ConnectionDao {

	Connection findById(long connectionId);
	
	void saveConnection(Connection connection);

	void deleteConnectionById(long connectionId);

	List<Connection> findAllConnections();

	List<Connection> findConnectionsBySourceId(long sourceId);
	
	Connection findConBySrcAndDestId(long sourceId, long destinationId);

}
