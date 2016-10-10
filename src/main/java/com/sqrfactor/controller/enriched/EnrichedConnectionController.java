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
			
			String destinationName = getName(destinationUser);
			String destinationProfilePicPath = getProfilePicPath(destinationUser);
			
			String sourceName = getName(sourceUser);
			String sourceProfilePicPath = getProfilePicPath(sourceUser);
			
			EnrichedConnection enrichedConnection = new EnrichedConnection(connection, sourceUser.getUserTypeId(),
					destinationUser.getUserTypeId(), destinationName, destinationProfilePicPath, sourceName, sourceProfilePicPath);
			enrichedConnections.add(enrichedConnection);
		
		}
		
		return new ResponseEntity<List<EnrichedConnection>>(enrichedConnections, HttpStatus.OK);
	}
	
	/**
	 * Get all enriched connections by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/connection/enriched/destinationid/{destinationId}", method = RequestMethod.GET)
	public ResponseEntity<List<EnrichedConnection>> getConnectionsByDestinationId(@PathVariable("destinationId") long destinationId) {
		List<EnrichedConnection> enrichedConnections = new ArrayList<EnrichedConnection>();
		
		List<Connection> connections = connectionService.findConnectionsByDestinationId(destinationId);
		if (connections.isEmpty()) {
			return new ResponseEntity<List<EnrichedConnection>>(HttpStatus.NOT_FOUND);
		}
		
		User destinationUser = userService.findById(destinationId);
		
		for(Connection connection : connections){
			
			User sourceUser = userService.findById(connection.getSourceId());
			if(sourceUser == null){
				continue;
			}
			
			String destinationName = getName(destinationUser);
			String destinationProfilePicPath = getProfilePicPath(destinationUser);
			
			String sourceName = getName(sourceUser);
			String sourceProfilePicPath = getProfilePicPath(sourceUser);
			
			EnrichedConnection enrichedConnection = new EnrichedConnection(connection, sourceUser.getUserTypeId(),
					destinationUser.getUserTypeId(), destinationName, destinationProfilePicPath, sourceName, sourceProfilePicPath);
			enrichedConnections.add(enrichedConnection);
		
		}
		
		return new ResponseEntity<List<EnrichedConnection>>(enrichedConnections, HttpStatus.OK);
	}
	
	private String getName(User user){
		String firstName = "";
		String lastName = "";
		if (user.getFirstName() != null) {
			firstName = user.getFirstName();
		}
		if (user.getLastName() != null) {
			lastName = user.getLastName();
		}
		String name = firstName + " " + lastName;
		
		return name;
	}
	
	private String getProfilePicPath(User user){
		String profilePicPath = "";
		
		if(user.getProfilePicPath() != null){
			profilePicPath = user.getProfilePicPath();
		}
		return profilePicPath;
	}
}