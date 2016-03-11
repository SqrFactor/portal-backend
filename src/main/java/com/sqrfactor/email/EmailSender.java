package com.sqrfactor.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Angad Gill
 *
 */
public class EmailSender {

	private String subject;
	private String link;
	private String body;
	private String recipientEmail;

	public EmailSender(String recipientEmail) {
		this.recipientEmail = recipientEmail;

		subject = "Verify your email address";
		link = "http://ec2-54-175-44-164.compute-1.amazonaws.com:8080/portal/user/verify?emailId=" + recipientEmail;
		body = "Please click on the link to verify your email" + "n\n " + link;
	}

	public boolean send() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "cp-23.webhostbox.net");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("create@sqrfactor.in", "sqrfactor@129");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("no-reply@sqrfactor.in"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			return true;
		} catch (MessagingException e) {
			return false;
			// throw new RuntimeException(e);
		}
	}
}
