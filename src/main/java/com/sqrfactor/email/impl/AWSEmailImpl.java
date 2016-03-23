/**
 * 
 */
package com.sqrfactor.email.impl;

import org.apache.log4j.Logger;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.sqrfactor.email.Email;

/**
 * @author Angad Gill
 *
 */
public class AWSEmailImpl extends Email {

	private static final Logger logger = Logger.getLogger(AWSEmailImpl.class);


	private String fromEmail = "no-reply@sqrfactor.in";

	@Override
	protected boolean send(String recipientEmail, String subject, String body) {

		Destination destination = new Destination().withToAddresses(new String[] { recipientEmail });

		// Create the subject and body of the message.
		Content subjectContent = new Content().withData(subject);
		Content bodyContent = new Content().withData(body);
		Body bodyText = new Body().withText(bodyContent);

		// Create a message with the specified subject and body.
		Message message = new Message().withSubject(subjectContent).withBody(bodyText);

		// Assemble the email.
		SendEmailRequest request = new SendEmailRequest().withSource(fromEmail).withDestination(destination)
				.withMessage(message);

		try {
			System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(new PropertiesCredentials(
					AWSEmailImpl.class.getClassLoader().getResourceAsStream("\\AwsCredentials.properties")));

			Region REGION = Region.getRegion(Regions.US_EAST_1);
			client.setRegion(REGION);

			// Send the email.
			client.sendEmail(request);
			System.out.println("Email sent!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
			return false;
		}
		return true;
	}
	
	public static void main(String args[]){
		AWSEmailImpl awsEmailImpl = new AWSEmailImpl();
		awsEmailImpl.send("angad.cec@gmail.com", "Hello", "Hi This is a test mail.");
		logger.info("Mail Sent");
			
	}
}
