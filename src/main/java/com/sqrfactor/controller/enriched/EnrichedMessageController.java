/**
 * 
 */
package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Connection;
import com.sqrfactor.model.EnrichedMessage;
import com.sqrfactor.model.EnrichedUserMessage;
import com.sqrfactor.model.Message;
import com.sqrfactor.model.User;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.MessageService;
import com.sqrfactor.service.UserService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedMessageController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ConnectionService connectionService;

	/**
	 * Get all messages by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/message/enriched/recipientuserid/{recipientUserId}", method = RequestMethod.GET)
	public ResponseEntity<List<EnrichedMessage>> getMessagesByRecipientUserId(@PathVariable("recipientUserId") long recipientUserId) {
		List<Message> messages = messageService.findMessagesByRecipientUserId(recipientUserId);
		if (messages.isEmpty()) {
			return new ResponseEntity<List<EnrichedMessage>>(HttpStatus.NOT_FOUND);
		}
		
		List<EnrichedMessage> enrichedMessages = new ArrayList<>();
		for(Message message : messages){
			
			String senderUserName = getName(message.getSenderUserId());
			String recipientUserName = getName(message.getRecipientUserId());
			String senderProfilePic = getProfilePic(message.getSenderUserId());
			String recipientProfilePic = getProfilePic(message.getRecipientUserId());
			
			final EnrichedMessage enrichedMessage = new EnrichedMessage(message, senderUserName, recipientUserName, 
					senderProfilePic, recipientProfilePic);
			enrichedMessages.add(enrichedMessage);
		}
		
		return new ResponseEntity<List<EnrichedMessage>>(enrichedMessages, HttpStatus.OK);
	}
	
	/**
	 * Get all messages by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/message/enriched/between", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EnrichedMessage>> getMessagesBetweenUserIds(@RequestParam("userId1") long userId1,
			@RequestParam("userId2") long userId2) {
		List<Message> messages = messageService.findMessagesBetweenUserIds(userId1, userId2);
		if (messages.isEmpty()) {
			return new ResponseEntity<List<EnrichedMessage>>(HttpStatus.NOT_FOUND);
		}
		
		List<EnrichedMessage> enrichedMessages = new ArrayList<>();
		for(Message message : messages){
			
			String senderUserName = getName(message.getSenderUserId());
			String recipientUserName = getName(message.getRecipientUserId());
			String senderProfilePicPath = getProfilePic(message.getSenderUserId());
			String recipientProfilePicPath = getProfilePic(message.getRecipientUserId());
			
			final EnrichedMessage enrichedMessage = new EnrichedMessage(message, senderUserName, recipientUserName,
					senderProfilePicPath, recipientProfilePicPath);
			enrichedMessages.add(enrichedMessage);
		}
		
		return new ResponseEntity<List<EnrichedMessage>>(enrichedMessages, HttpStatus.OK);
	}
	
	/**
	 * Get all messages by sourceId
	 * 
	 * @return
	 */
	/*@RequestMapping(value = "/message/enriched/usermessage/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EnrichedUserMessage>> getUserMessagesByUserId(@PathVariable("userId") long userId) {
		
		List<EnrichedUserMessage> enrichedUserMessages = new ArrayList<>();
		List<Connection> connections = connectionService.findConnectionsBySourceId(userId);
		
		if (connections.isEmpty()) {
			return new ResponseEntity<List<EnrichedUserMessage>>(HttpStatus.NOT_FOUND);
		}
		
		for(Connection connection: connections){
			String userName;
			String profilePicPath;
			
			User user = userService.findById(connection.getDestinationId());
			
			if(user == null){
				continue;
			}
			
			userName = getName(user.getUserId());
			profilePicPath = getProfilePic(user.getUserId());
			
			List<Message> messages = messageService.findMessagesBetweenUserIds(userId, connection.getDestinationId());
			
			if(messages.isEmpty()){
				continue;
			}
			
			List<EnrichedMessage> enrichedMessages = new ArrayList<>();
			for(Message message : messages){
				
				String senderUserName = getName(message.getSenderUserId());
				String recipientUserName = getName(message.getRecipientUserId());
				String senderProfilePicPath = getProfilePic(message.getSenderUserId());
				String recipientProfilePicPath = getProfilePic(message.getRecipientUserId());
				
				final EnrichedMessage enrichedMessage = new EnrichedMessage(message, senderUserName, recipientUserName,
						senderProfilePicPath, recipientProfilePicPath);
				enrichedMessages.add(enrichedMessage);
			}
			EnrichedUserMessage enrichedUserMessage = new EnrichedUserMessage(user.getUserId(), userName, profilePicPath, enrichedMessages);
			enrichedUserMessages.add(enrichedUserMessage);
		}
				
		return new ResponseEntity<List<EnrichedUserMessage>>(enrichedUserMessages, HttpStatus.OK);
	}
*/
	/**
	 * Get all messages by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/message/enriched/usermessage/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EnrichedUserMessage>> getUserMessagesByUserId(@PathVariable("userId") long userId) {
		
		//TODO refactor
		List<EnrichedUserMessage> enrichedUserMessages = new ArrayList<>();
		
		List<Message> messagesRecieved = messageService.findMessagesByRecipientUserId(userId);
		
		//Retrieve all the messages recieved
		List<EnrichedMessage> enrichedMessages = new ArrayList<>();
		for(Message message : messagesRecieved){
			String senderUserName = getName(message.getSenderUserId());
			String recipientUserName = getName(message.getRecipientUserId());
			String senderProfilePicPath = getProfilePic(message.getSenderUserId());
			String recipientProfilePicPath = getProfilePic(message.getRecipientUserId());
			
			final EnrichedMessage enrichedMessage = new EnrichedMessage(message, senderUserName, recipientUserName,
					senderProfilePicPath, recipientProfilePicPath);
			enrichedMessages.add(enrichedMessage);
		}
		
		List<Message> messagesSent = messageService.findMessagesBySenderUserId(userId);
		
		//Retrieve all the messages sent
		for(Message message : messagesSent){
			String senderUserName = getName(message.getSenderUserId());
			String recipientUserName = getName(message.getRecipientUserId());
			String senderProfilePicPath = getProfilePic(message.getSenderUserId());
			String recipientProfilePicPath = getProfilePic(message.getRecipientUserId());
			
			final EnrichedMessage enrichedMessage = new EnrichedMessage(message, senderUserName, recipientUserName,
					senderProfilePicPath, recipientProfilePicPath);
			enrichedMessages.add(enrichedMessage);
		}
		
		Map<Long, List<EnrichedMessage>> map = new HashMap<>();
		for(EnrichedMessage enrichedMessage : enrichedMessages){
			//Sent Message
			if(enrichedMessage.getSenderUserId() == userId){
				long key = enrichedMessage.getRecipientUserId();
				if(map.containsKey(key)){
					map.get(key).add(enrichedMessage);
				}else{
					List<EnrichedMessage> list = new ArrayList<EnrichedMessage>();
					list.add(enrichedMessage);
					map.put(key, list);
				}
				
			}
			//Recieved Message
			else{
				long key = enrichedMessage.getSenderUserId();
				if(map.containsKey(key)){
					map.get(key).add(enrichedMessage);
				}else{
					List<EnrichedMessage> list = new ArrayList<EnrichedMessage>();
					list.add(enrichedMessage);
					map.put(key, list);
				}
			}
		}
		
		// Put them as a list for each user
		for(Map.Entry<Long, List<EnrichedMessage>> entry : map.entrySet()){
			User user = userService.findById(entry.getKey());
			
			if(user == null){
				continue;
			}
			
			String userName = getName(user.getUserId());
			String profilePicPath = getProfilePic(user.getUserId());
			
			EnrichedUserMessage enrichedUserMessage = new EnrichedUserMessage(user.getUserId(), userName, profilePicPath, entry.getValue());
			enrichedUserMessages.add(enrichedUserMessage);
		}
		
		return new ResponseEntity<List<EnrichedUserMessage>>(enrichedUserMessages, HttpStatus.OK);
	}

	
	
	
	private String getName(long userId){
		
		User user = userService.findById(userId);

		if (user == null) {
			return null;
		}

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
	
	private String getProfilePic(long userId){
		
		User user = userService.findById(userId);

		if (user == null) {
			return null;
		}

		String profilePicPath = "";
		
		if (user.getProfilePicPath() != null) {
			profilePicPath = user.getProfilePicPath();
		}
		
		return profilePicPath;
	}
}
