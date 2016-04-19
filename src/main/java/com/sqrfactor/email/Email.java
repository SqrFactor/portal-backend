package com.sqrfactor.email;

public abstract class Email{
	
	protected abstract boolean send(String recipientEmail, String subject, String body);
	
	public boolean sendVerificationMail(String recipientEmail){

		String subject = "Verify your email address";
		String link = "http://ec2-54-210-175-152.compute-1.amazonaws.com:8080/portal-frontend/index.html#/verify?emailId=" + recipientEmail;
		String body = "Please click on the link to verify your email" + "n\n " + link;
		
		if(send(recipientEmail, subject, body)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean sendForgotPasswordMail(String recipientEmail){

		String subject = "Forgot Password";
		String link = "http://ec2-54-175-44-164.compute-1.amazonaws.com:8080/portal/user/forgotpassword";
		String body = "Please click on the link to verify your email" + "n\n " + link;
		
		if(send(recipientEmail, subject, body)){
			return true;
		}else{
			return false;
		}
	}
}