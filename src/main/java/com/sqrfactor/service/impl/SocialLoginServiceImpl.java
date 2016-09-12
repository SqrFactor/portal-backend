/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.SocialLoginDao;
import com.sqrfactor.model.SocialLogin;
import com.sqrfactor.service.SocialLoginService;

/**
 * @author Angad Gill
 *
 */
@Service("socialLoginService")
@Transactional
public class SocialLoginServiceImpl implements SocialLoginService {

	@Autowired
	private SocialLoginDao socialLoginDao;

	/**
	 * Find All SocialLogins
	 */
	public List<SocialLogin> findAllSocialLogins() {
		return socialLoginDao.findAllSocialLogins();
	}

	/**
	 * Find SocialLogin by id
	 */
	public SocialLogin findByUserId(long userId) {
		return socialLoginDao.findByUserId(userId);
	}
	
	/**
	 * Save SocialLogins
	 */
	public void saveSocialLogin(SocialLogin socialLogin) {
		socialLoginDao.saveSocialLogin(socialLogin);
	}

	/**
	 * Update SocialLogin
	 */
	public void updateSocialLogin(SocialLogin socialLogin) {
		SocialLogin entity = socialLoginDao.findByUserId(socialLogin.getUserId());
		if (entity != null) {
			entity.setSocialUID(socialLogin.getSocialUID());
			entity.setLoginVia(socialLogin.getLoginVia());
		}
	}

	/**
	 * Delete socialLogin by id
	 */
	public void deleteSocialLoginById(long userId) {
		socialLoginDao.deleteSocialLoginByUserId(userId);
	}
	
	public SocialLogin findBySocialUID(String socialUID, String loginVia){
		return socialLoginDao.findBySocialUID(socialUID, loginVia);
	}
}
