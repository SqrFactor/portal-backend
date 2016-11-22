/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Message;

/**
 * @author Angad Gill
 *
 */
public interface MessageDao {

	Message findById(long messageId);

	void saveMessage(Message message);

	void deleteMessageById(long messageId);
	
	List<Message> findAllMessages();
	
	List<Message> findMessagesBySenderUserId(long senderUserId);
	
	List<Message> findMessagesByRecipientUserId(long recipientUserId);
	
	List<Message> findUnreadMessagesByRecipientUserId(long recipientUserId);
	
	List<Message> findMessagesBetweenUserIds(long userId1, long userId2);
}
