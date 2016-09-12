/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.SocialLogin;

/**
 * @author Angad Gill
 *
 */
public interface SocialLoginDao {

	SocialLogin findByUserId(long userId);
	
	List<SocialLogin> findAllSocialLogins();
	
	void saveSocialLogin(SocialLogin socialLogin);
	
	void deleteSocialLoginByUserId(long userId);

	SocialLogin findBySocialUID(String socialUID, String loginVia);

}
