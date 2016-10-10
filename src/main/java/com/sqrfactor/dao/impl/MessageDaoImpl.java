/**
 * 
 */
package com.sqrfactor.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.MessageDao;
import com.sqrfactor.model.Message;
import com.sqrfactor.model.User;

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

	@Override
	public List<Message> findMessagesBySenderUserId(long senderUserId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("senderUserId", senderUserId));
		return (List<Message>) criteria.list();
	}
	
	public List<Message> findMessagesByRecipientUserId(long recipientUserId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("recipientUserId", recipientUserId));
		return (List<Message>) criteria.list();
	}
	
	public List<Message> findMessagesBetweenUserIds(long userId1, long userId2){
		
		List<Message> messages = new ArrayList<>();
		Criteria criteria = createEntityCriteria();
		
		Criterion sender1 = Restrictions.eq("senderUserId",userId1);
		Criterion recipient1 = Restrictions.eq("recipientUserId",userId2);
		
		Criterion sender2 = Restrictions.eq("senderUserId",userId2);
		Criterion recipient2 = Restrictions.eq("recipientUserId",userId1);
		
		LogicalExpression andExp1 = Restrictions.and(sender1, recipient1);
		LogicalExpression andExp2 = Restrictions.and(sender2, recipient2);
		
		LogicalExpression orExp = Restrictions.or(andExp1, andExp2);
		criteria.add( orExp );
		
		messages.addAll((List<Message>)criteria.list());
		
		return messages;
	}

}
