package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Connection;
import com.sqrfactor.model.EnrichedConnection;
import com.sqrfactor.model.EnrichedFeed;
import com.sqrfactor.model.User;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.UserService;

/**
 * 
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedConnectionController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConnectionService connectionService;


	/**
	 * Get all enriched connections by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/connection/enriched/sourceid/{sourceId}", method = RequestMethod.GET)
	public ResponseEntity<List<EnrichedConnection>> getConnectionsBySourceId(@PathVariable("sourceId") long sourceId) {
		List<EnrichedConnection> enrichedConnections = new ArrayList<EnrichedConnection>();
		
		List<Connection> connections = connectionService.findConnectionsBySourceId(sourceId);
		if (connections.isEmpty()) {
			return new ResponseEntity<List<EnrichedConnection>>(HttpStatus.NOT_FOUND);
		}
		
		User sourceUser = userService.findById(sourceId);
		
		for(Connection connection : connections){
			
			User destinationUser = userService.findById(connection.getDestinationId());
			if(destinationUser == null){
				continue;
			}
			
			EnrichedConnection enrichedConnection = new EnrichedConnection(connection, sourceUser.getUserTypeId(), destinationUser.getUserTypeId());
			enrichedConnections.add(enrichedConnection);
		
		}
		
		return new ResponseEntity<List<EnrichedConnection>>(enrichedConnections, HttpStatus.OK);
	}
}