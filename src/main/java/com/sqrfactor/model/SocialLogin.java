/**
 * 
 */
package com.sqrfactor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "social_login")
public class SocialLogin {

	@Id
	@Column(name = "userId")
	private long userId;
	
	@Size(min = 1, max = 100)
	@Column(name = "socialUID")
	private String socialUID;
	
	@Size(min = 1, max = 20)
	@Column(name = "loginVia")
	private String loginVia;
	
	public SocialLogin() {
		
	}
	
	/**
	 * @param userId
	 * @param socialUID
	 * @param loginVia
	 */
	public SocialLogin(long userId, String socialUID, String loginVia) {
		super();
		this.userId = userId;
		this.socialUID = socialUID;
		this.loginVia = loginVia;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the socialUID
	 */
	public String getSocialUID() {
		return socialUID;
	}

	/**
	 * @param socialUID the socialUID to set
	 */
	public void setSocialUID(String socialUID) {
		this.socialUID = socialUID;
	}

	/**
	 * @return the loginVia
	 */
	public String getLoginVia() {
		return loginVia;
	}

	/**
	 * @param loginVia the loginVia to set
	 */
	public void setLoginVia(String loginVia) {
		this.loginVia = loginVia;
	}
}
