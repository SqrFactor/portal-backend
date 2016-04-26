/**
 * 
 */
package com.sqrfactor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name="message_details")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="messageId")
	private long messageId;
	
	@Column(name="senderUserId")
	private long senderUserId;
	
	@Column(name="recipientUserId")
	private long recipientUserId;
	
	@Column(name="messageText")
	private String messageText;
	
	@Column(name="isRead")
	private boolean isRead;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdAt", nullable=false)
	private Date createdAt;
	
	public Message() {
	}

	public Message(Message message){
		super();
		this.messageId = message.messageId;
		this.senderUserId = message.senderUserId;
		this.recipientUserId = message.recipientUserId;
		this.messageText = message.messageText;
		this.isRead = message.isRead;
		this.createdAt = message.createdAt;

	}
	
	public Message(long messageId, long senderUserId, long recipientUserId, String messageText, boolean isRead, Date createdAt) {
		super();
		this.messageId = messageId;
		this.senderUserId = senderUserId;
		this.recipientUserId = recipientUserId;
		this.messageText = messageText;
		this.isRead = isRead;
		this.createdAt = createdAt;
	}

	/**
	 * @return the messageId
	 */
	public long getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the senderUserId
	 */
	public long getSenderUserId() {
		return senderUserId;
	}

	/**
	 * @param senderUserId the senderUserId to set
	 */
	public void setSenderUserId(long senderUserId) {
		this.senderUserId = senderUserId;
	}

	/**
	 * @return the recipientUserId
	 */
	public long getRecipientUserId() {
		return recipientUserId;
	}

	/**
	 * @param recipientUserId the recipientUserId to set
	 */
	public void setRecipientUserId(long recipientUserId) {
		this.recipientUserId = recipientUserId;
	}

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/**
	 * @return the isRead
	 */
	public boolean isRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
