/**
 * 
 */
package com.sqrfactor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Connection;
import com.sqrfactor.model.User;
import com.sqrfactor.service.ConnectionService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class ConnectionController {

	@Autowired
	private ConnectionService connectionService;
	
	public ConnectionController(){}
	
	/**
	 * Get all the connections
	 * 
	 * @return
	 */
	@RequestMapping(value = "/connection/", method = RequestMethod.GET)
	public ResponseEntity<List<Connection>> getAllConnections() {
		List<Connection> connections = connectionService.findAllConnections();
		if (connections.isEmpty()) {
			return new ResponseEntity<List<Connection>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Connection>>(connections, HttpStatus.OK);
	}

	/**
	 * Get a Connection by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/connection/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Connection> getConnectionById(@PathVariable int id) {
		Connection connection = connectionService.findById(id);
		if (connection == null) {
			return new ResponseEntity<Connection>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Connection>(connection, HttpStatus.OK);
	}

	/**
	 * Create Connection
	 * 
	 * @param connection
	 */
	@RequestMapping(value = "/connection/", method = RequestMethod.POST)
	public ResponseEntity<Connection> createConnection(@RequestBody Connection connection) {
		
		connectionService.saveConnection(connection);

		return new ResponseEntity<Connection>(connection, HttpStatus.CREATED);
	}

	/**
	 * Update Connection
	 * 
	 * @param id
	 * @param connection
	 * @return
	 */
	@RequestMapping(value = "/connection/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Connection> updateConnection(@PathVariable("id") int id, @RequestBody Connection connection) {
		Connection currentConnection = connectionService.findById(id);

		if (currentConnection == null) {
			return new ResponseEntity<Connection>(HttpStatus.NOT_FOUND);
		}
		connection.setConnectionId(id);
		connectionService.updateConnection(connection);
		return new ResponseEntity<Connection>(connection, HttpStatus.OK);
	}

	/**
	 * Delete Connection by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/connection/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Connection> deleteConnection(@PathVariable("id") int id) {
		Connection connection = connectionService.findById(id);
		if (connection == null) {
			return new ResponseEntity<Connection>(HttpStatus.NOT_FOUND);
		}

		connectionService.deleteConnectionById(id);
		return new ResponseEntity<Connection>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Get all connections by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/connection/sourceid/{sourceId}", method = RequestMethod.GET)
	public ResponseEntity<List<Connection>> getConnectionsBySourceId(@PathVariable("sourceId") long sourceId) {
		List<Connection> connections = connectionService.findConnectionsBySourceId(sourceId);
		if (connections.isEmpty()) {
			return new ResponseEntity<List<Connection>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Connection>>(connections, HttpStatus.OK);
	}
	
	/**
	 * Add Connection
	 * 
	 * @param connection
	 */
	@RequestMapping(value = "/connection/add", method = RequestMethod.POST)
	public ResponseEntity<List<Connection>> addConnection(@RequestBody Connection connection) {

		List<Connection> connections = new ArrayList<Connection>();
		
		//Check if already exists
		Connection c1 = connectionService.findConBySrcAndDestId(connection.getSourceId(), connection.getDestinationId());
		Connection c2 = connectionService.findConBySrcAndDestId(connection.getDestinationId(), connection.getSourceId());
		
		if(c1 != null || c2 != null){
			connections.add(c1);
			connections.add(c2);
			return new ResponseEntity<List<Connection>>(connections, HttpStatus.CONFLICT);
		}
		
		//Add connections
		connectionService.saveConnection(connection);
		
		Connection entity = new Connection();
		entity.setSourceId(connection.getDestinationId());
		entity.setDestinationId(connection.getSourceId());
		
		connectionService.saveConnection(entity);

		connections.add(connection);
		connections.add(entity);
		return new ResponseEntity<List<Connection>>(connections, HttpStatus.CREATED);
	}

	/**
	 * Remove Connection
	 * 
	 * @param connection
	 */
	@RequestMapping(value = "/connection/remove", method = RequestMethod.POST)
	public ResponseEntity<Connection> removeConnection(@RequestBody Connection connection) {
		
		Connection c1 = connectionService.findConBySrcAndDestId(connection.getSourceId(), connection.getDestinationId());
		Connection c2 = connectionService.findConBySrcAndDestId(connection.getDestinationId(), connection.getSourceId());

		connectionService.deleteConnectionById(c1.getConnectionId());
		connectionService.deleteConnectionById(c2.getConnectionId());
		
		if (c1 == null || c2 == null) {
			return new ResponseEntity<Connection>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Connection>(HttpStatus.NO_CONTENT);
	}
	
}
