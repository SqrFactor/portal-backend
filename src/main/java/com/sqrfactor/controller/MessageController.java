package com.sqrfactor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Message;
import com.sqrfactor.service.MessageService;
import com.sqrfactor.util.Constants;

/**
 * @author Angad Gill
 *
 */
@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private WebsocketServer websocketServer;

	public MessageController() {
	}
	
	/**
	 * Get all the messages
	 * 
	 * @return
	 */
	@RequestMapping(value = "/message/", method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getAllMessages() {
		List<Message> messages = messageService.findAllMessages();
		if (messages.isEmpty()) {
			return new ResponseEntity<List<Message>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}

	/**
	 * Get a Message by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Message> getMessageById(@PathVariable int id) {
		Message message = messageService.findById(id);
		if (message == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	/**
	 * Create Message
	 * 
	 * @param message
	 */
	@RequestMapping(value = "/message/", method = RequestMethod.POST)
	public ResponseEntity<Message> createMessage(@RequestBody Message message) {

		messageService.saveMessage(message);
		
		//Notify User
		websocketServer.sendMessageToUser(message.getRecipientUserId(), Constants.MESSAGE);

		return new ResponseEntity<Message>(message, HttpStatus.CREATED);
	}

	/**
	 * Update Message
	 * 
	 * @param id
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/message/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Message> updateMessage(@PathVariable("id") int id,
			@RequestBody Message message) {
		Message currentMessage = messageService.findById(id);

		if (currentMessage == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}
		message.setMessageId(id);
		messageService.updateMessage(message);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	/**
	 * Delete Message by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Message> deleteMessage(@PathVariable("id") int id) {
		Message message = messageService.findById(id);
		if (message == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}

		messageService.deleteMessageById(id);
		return new ResponseEntity<Message>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Get all messages by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/message/recipientuserid/{recipientUserId}", method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getMessagesBySourceUserId(@PathVariable("recipientUserId") long recipientUserId) {
		List<Message> messages = messageService.findMessagesByRecipientUserId(recipientUserId);
		if (messages.isEmpty()) {
			return new ResponseEntity<List<Message>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}
	

}
