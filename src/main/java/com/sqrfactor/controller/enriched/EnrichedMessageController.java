/**
 * 
 */
package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.EnrichedMessage;
import com.sqrfactor.model.Message;
import com.sqrfactor.model.User;
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
