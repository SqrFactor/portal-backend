/**
 * 
 */
package com.sqrfactor.model;

/**
 * @author Angad Gill
 *
 */
public class EnrichedAchievementEarned extends AchievementEarned{
	
	private String achievementName;
	private String achievementDescription;
	
	public EnrichedAchievementEarned(AchievementEarned achievementEarned, String achievementName, String achievementDescription){
		super(achievementEarned);
		this.achievementName = achievementName;
		this.achievementDescription = achievementDescription;
	}

	/**
	 * @return the achievementName
	 */
	public String getAchievementName() {
		return achievementName;
	}

	/**
	 * @param achievementName the achievementName to set
	 */
	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}

	/**
	 * @return the achievementDescription
	 */
	public String getAchievementDescription() {
		return achievementDescription;
	}

	/**
	 * @param achievementDescription the achievementDescription to set
	 */
	public void setAchievementDescription(String achievementDescription) {
		this.achievementDescription = achievementDescription;
	}
}
