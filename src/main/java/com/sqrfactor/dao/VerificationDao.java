/**
 * 
 */
package com.sqrfactor.dao;

import com.sqrfactor.model.Verification;

/**
 * @author Angad Gill
 *
 */
public interface VerificationDao {

	Verification findById(long verificationId);
	
	void saveVerification(Verification verification);

	void deleteVerificationById(long verificationId);

	Verification findByUserId(long userId); 
}
