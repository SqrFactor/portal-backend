/**
 * 
 */
package com.sqrfactor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.MessageDao;
import com.sqrfactor.model.Message;

/**
 * @author Angad Gill
 *
 */
@Repository("messageDao")
public class MessageDaoImpl extends AbstractDao<Long, Message> implements MessageDao {

	@Override
	public Message findById(long messageId) {
		return getByKey(messageId);
	}

	@Override
	public void saveMessage(Message message) {
		persist(message);
	}

	@Override
	public void deleteMessageById(long messageId) {
		Query query = getSession().createSQLQuery("delete from message_details where messageId = :messageId");
		query.setLong("messageId", messageId);
		query.executeUpdate();
	}
	
	public List<Message> findAllMessages(){
		Criteria criteria = createEntityCriteria();
		return (List<Message>) criteria.list();
	}
	
	public List<Message> findMessagesByRecipientUserId(long recipientUserId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("recipientUserId", recipientUserId));
		return (List<Message>) criteria.list();
	}


}
