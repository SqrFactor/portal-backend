package com.sqrfactor.email;

public abstract class Email {

	protected abstract boolean send(String recipientEmail, String subject, String body);

	public boolean sendVerificationMail(String recipientEmail, String verificationCode) {

		String subject = "Verify your email address";

		String link = "http://www.sqrfactor.in/#/verify?emailId=" + recipientEmail + "&verificationCode="
				+ verificationCode;

		String body = "Please click on the link to verify your email" + "\n\n " + link;

		if (send(recipientEmail, subject, body)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean sendForgotPasswordMail(String recipientEmail, String newPassword) {

		String subject = "Forgot Password";
		String link = "http://www.sqrfactor.in/#/login";
		String body = "Please click on the link to login with your new password" + "\n\n " + link + "\n\n"
				+ "New Password : " + newPassword;
		;

		if (send(recipientEmail, subject, body)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean sendClaimCollegeMail(String recipientEmail, long userId, String name, String phoneNumber, String collegeName) {

		String subject = "Claim Profile - " + collegeName;
		String body = "Claimer's Name : " + name + 
				"\n\nUserId : " + userId + 
				"\n\nEmailId : " + recipientEmail + 
				"\n\nPhone Number : " + phoneNumber;

		// Send Email to admin aswell
		send("create@sqrfactor.in", subject, body);

		subject = "Claim Profile - " + collegeName;
		body = "Dear " + name + ","

				+ "\n\nWe hope you're doing good! Thank you so much for registering on our portal :)"

				+ "\n\nYou've Claimed College Profile: " + collegeName
				+ ". Our team will review your profile and call you before the College page is approved to you. We would also want you to get a Teacher/HOD involved in this to verify your identity. You can either produce a letter by your HOD on college letterhead and stamp or you can give your HOD's contact detail referring to them about the portal and college page you have claimed."

				+ "\n\nOnce approved, you will be working with Team SqrFactor to create an amazing online network of authentic users related to our domain."

				+ "\n\nRegards," + "\nTeam SqrFactor";

		if (send(recipientEmail, subject, body)) {
			return true;
		} else {
			return false;
		}
	}
}