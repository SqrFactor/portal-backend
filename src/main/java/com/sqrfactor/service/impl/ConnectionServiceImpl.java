/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.ConnectionDao;
import com.sqrfactor.model.Connection;
import com.sqrfactor.service.ConnectionService;

/**
 * @author Angad Gill
 *
 */
@Service("connectionService")
@Transactional
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	private ConnectionDao connectionDao;
	
	@Override
	public Connection findById(long connectionId) {
		return connectionDao.findById(connectionId);
	}

	@Override
	public void saveConnection(Connection connection) {
		connectionDao.saveConnection(connection);
	}

	@Override
	public void updateConnection(Connection connection) {
		Connection entity = connectionDao.findById(connection.getConnectionId());
		
		if(entity != null){
			entity.setSourceId(connection.getSourceId());
			entity.setDestinationId(connection.getDestinationId());
		}
		
	}

	@Override
	public void deleteConnectionById(long connectionId) {
		connectionDao.deleteConnectionById(connectionId);
	}

	@Override
	public List<Connection> findAllConnections() {
		return connectionDao.findAllConnections();
	}
	
	@Override
	public List<Connection> findConnectionsBySourceId(long sourceId) {
		return connectionDao.findConnectionsBySourceId(sourceId);
	}

	@Override
	public List<Connection> findConnectionsByDestinationId(long destinationId) {
		return connectionDao.findConnectionsByDestinationId(destinationId);
	}
	
	@Override
	public Connection findConBySrcAndDestId(long sourceId, long destinationId){
		return connectionDao.findConBySrcAndDestId(sourceId, destinationId);
	}

}
