/**
 * 
 */
package com.sqrfactor.email.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sqrfactor.email.Email;

/**
 * @author Angad Gill
 *
 */
public class BigRockEmailImpl extends Email{
	private String userName = "create@sqrfactor.in";
	private String password = "sqrfactor@129";
	
	private String fromEmail = "no-reply@sqrfactor.in";

	protected boolean send(String recipientEmail, String subject, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "cp-23.webhostbox.net");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			return true;
		} catch (MessagingException e) {
			return false;
		}
	}
}
