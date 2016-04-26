/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Message;

/**
 * @author Angad Gill
 *
 */
public interface MessageService {

	Message findById(long messageId);

	void saveMessage(Message message);

	void updateMessage(Message message);

	void deleteMessageById(long messageId);
	
	List<Message> findAllMessages();
	
	List<Message> findMessagesByRecipientUserId(long sourceUserId);

}
