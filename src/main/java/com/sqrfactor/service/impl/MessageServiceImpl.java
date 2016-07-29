/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.MessageDao;
import com.sqrfactor.model.Message;
import com.sqrfactor.service.MessageService;

/**
 * @author Angad Gill
 *
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Override
	public Message findById(long messageId) {
		return messageDao.findById(messageId);
	}

	@Override
	public void saveMessage(Message message) {
		messageDao.saveMessage(message);
	}

	@Override
	public void updateMessage(Message message) {
		Message entity = messageDao.findById(message.getMessageId());

		if (entity != null) {
			entity.setSenderUserId(message.getSenderUserId());
			entity.setRecipientUserId(message.getRecipientUserId());
			entity.setMessageText(message.getMessageText());
			entity.setRead(message.isRead());
			entity.setCreatedAt(message.getCreatedAt());
		}
	}

	@Override
	public void deleteMessageById(long messageId) {
		messageDao.deleteMessageById(messageId);
	}

	@Override
	public List<Message> findAllMessages(){
		return messageDao.findAllMessages();
	}
	
	@Override
	public List<Message> findMessagesByRecipientUserId(long recipientUserId){
		return messageDao.findMessagesByRecipientUserId(recipientUserId);
	}
	
	@Override
	public List<Message> findMessagesBetweenUserIds(long userId1, long userId2){
		return messageDao.findMessagesBetweenUserIds(userId1, userId2);
	}
}
