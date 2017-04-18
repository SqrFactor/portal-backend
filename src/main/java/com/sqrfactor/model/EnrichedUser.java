/**
 * 
 */
package com.sqrfactor.model;

/**
 * @author Angad Gill
 *
 */
public class EnrichedUser extends User{
	private EnrichedEducation recentEducation;
	private Profession recentProfession;
	
	public EnrichedUser(User user, EnrichedEducation recentEducation, Profession recentProfession){
		super(user);
		this.recentEducation = recentEducation;
		this.recentProfession = recentProfession;
	}

	/**
	 * @return the recentEducation
	 */
	public EnrichedEducation getRecentEducation() {
		return recentEducation;
	}

	/**
	 * @param recentEducation the recentEducation to set
	 */
	public void setRecentEducation(EnrichedEducation recentEducation) {
		this.recentEducation = recentEducation;
	}

	/**
	 * @return the recentProfession
	 */
	public Profession getRecentProfession() {
		return recentProfession;
	}

	/**
	 * @param recentProfession the recentProfession to set
	 */
	public void setRecentProfession(Profession recentProfession) {
		this.recentProfession = recentProfession;
	}

	
}
