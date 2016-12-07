/**
 * 
 */
package com.sqrfactor.model;

/**
 * @author Angad Gill
 *
 */
public class EnrichedCollege extends College{

	private long userId;
	
	public EnrichedCollege(College college, long userId){
		super(college);
		this.userId = userId;
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
}
