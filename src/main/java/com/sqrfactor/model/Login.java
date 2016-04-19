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
	private long userId;
	
	@Size(min = 3, max = 50)
	@Column(name = "userName", nullable = false)
	private String userName;
	
	@Size(min = 3, max = 50)
	@Column(name = "userPassword", nullable = false)
	private String userPassword;
	
	public Login(){
		
	}
	
	public Login(long userId, String userName, String userPassword){
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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

}
