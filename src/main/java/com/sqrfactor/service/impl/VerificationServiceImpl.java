/**
 * 
 */
package com.sqrfactor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.VerificationDao;
import com.sqrfactor.model.Verification;
import com.sqrfactor.service.VerificationService;

/**
 * @author Angad Gill
 *
 */
@Service("verificationService")
@Transactional
public class VerificationServiceImpl implements VerificationService{

	@Autowired
	private VerificationDao verificationDao;
	
	public Verification findById(long verificationId) {
		return verificationDao.findById(verificationId);
	}
	
	public Verification findByUserId(long userId) {
		return verificationDao.findByUserId(userId);
	}

	public void saveVerification(Verification verification) {
		verificationDao.saveVerification(verification);
	}

	public void updateVerification(Verification verification) {
		Verification entity = verificationDao.findById(verification.getVerificationId());
		
		if (entity != null) {
			entity.setEmailCode(verification.getEmailCode());
			entity.setPhoneCode(verification.getPhoneCode());
		}
	}

	/**
	 * Delete user by id
	 */
	public void deleteById(long verificationId) {
		verificationDao.deleteVerificationById(verificationId);
	}

}
