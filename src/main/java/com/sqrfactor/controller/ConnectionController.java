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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.constants.EnumUtils;
import com.sqrfactor.model.Connection;
import com.sqrfactor.model.Notification;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.NotificationService;
import com.sqrfactor.util.Constants;

/**
 * @author Angad Gill
 *
 */
@RestController
public class ConnectionController {

	@Autowired
	private ConnectionService connectionService;
	
	@Autowired
	private NotificationService notificationService;
	
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
		
		//Check if already exists
		Connection c1 = connectionService.findConBySrcAndDestId(connection.getSourceId(), connection.getDestinationId());
		
		if(c1 == null){
			connectionService.saveConnection(connection);
		}

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
	public ResponseEntity<Connection> addConnection(@RequestBody Connection connection) {
		//Check if already exists
		Connection c1 = connectionService.findConBySrcAndDestId(connection.getSourceId(), connection.getDestinationId());
		
		if(c1 != null){
			return new ResponseEntity<Connection>(connection, HttpStatus.CONFLICT);
		}
		
		//Add connections
		connectionService.saveConnection(connection);
		
		//Add notification
		Notification notification = new Notification();
		long notificationSource = connection.getDestinationId();
		long notificationDestination = connection.getSourceId();
		
		notification.setSourceUserId(notificationSource);
		notification.setDestinationUserId(notificationDestination);
		notification.setNotificationTypeId(EnumUtils.NotificationType.ConnectionAdded.getNotificationCode());
		
		notificationService.saveNotification(notification);
		return new ResponseEntity<Connection>(connection, HttpStatus.CREATED);

	}

	/**
	 * Remove Connection
	 * 
	 * @param connection
	 */
	@RequestMapping(value = "/connection/remove", method = RequestMethod.POST)
	public ResponseEntity<Connection> removeConnection(@RequestBody Connection connection) {
		
		Connection c1 = connectionService.findConBySrcAndDestId(connection.getSourceId(), connection.getDestinationId());
		
		connectionService.deleteConnectionById(c1.getConnectionId());
		
		if (c1 == null) {
			return new ResponseEntity<Connection>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Connection>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Check if connected
	 * 
	 * @return
	 */
	@RequestMapping(value = "/connection/isconnected", method = RequestMethod.GET)
	public ResponseEntity<String> isConnected(@RequestParam("sourceId") long sourceId, @RequestParam("destinationId") long destinationId) {
		Connection connection = connectionService.findConBySrcAndDestId(sourceId, destinationId);
		
		if(connection != null){
			return new ResponseEntity<String>("true", HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}
	}
}
