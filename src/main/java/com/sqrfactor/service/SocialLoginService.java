/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.SocialLogin;

/**
 * @author Angad Gill
 *
 */
public interface SocialLoginService {

	SocialLogin findByUserId(long userId);
	
	void saveSocialLogin(SocialLogin socialLogin);

	void updateSocialLogin(SocialLogin login);
	
	void deleteSocialLoginById(long userId);

	List<SocialLogin> findAllSocialLogins();

	SocialLogin findBySocialUID(String socialUID, String loginVia);
}
