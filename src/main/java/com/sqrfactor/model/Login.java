package com.sqrfactor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "user_login")
public class Login {

	@Id
	@Column(name = "userId")
	private int userId;
	
	@Size(min = 3, max = 50)
	@Column(name = "userName", nullable = false)
	private String userName;
	
	@Size(min = 3, max = 50)
	@Column(name = "userPassword", nullable = false)
	private String userPassword;
	
	@Column(name = "passwordSalt", nullable = false)
	private String passwordSalt;
	
	public Login(){
		
	}
	
	public Login(int userId, String userName, String userPassword, String passwordSalt){
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.passwordSalt = passwordSalt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the passwordSalt
	 */
	public String getPasswordSalt() {
		return passwordSalt;
	}

	/**
	 * @param passwordSalt the passwordSalt to set
	 */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

}
