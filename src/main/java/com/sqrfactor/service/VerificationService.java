/**
 * 
 */
package com.sqrfactor.service;

import com.sqrfactor.model.Verification;

/**
 * @author Angad Gill
 *
 */
public interface VerificationService {

	Verification findById(long verificationId);
	
	void saveVerification(Verification verification);

	void updateVerification(Verification verification);

	void deleteById(long verificationId);
	
	Verification findByUserId(long userId);

}
