package com.sqrfactor.sms;

public abstract class SMS {
	protected abstract boolean send(String contactNumber, String message);

	public boolean sendSuccessfulRegistrationMessage(String contactNumber) {
		String message = "Hi, Thanks for registering on Sqrfactor.in";
		if (send(contactNumber, message)) {
			return true;
		} else {
			return false;
		}
	}
}
