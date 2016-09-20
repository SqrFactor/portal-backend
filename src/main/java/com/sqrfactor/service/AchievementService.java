/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Achievement;

/**
 * @author Angad Gill
 *
 */
public interface AchievementService {

	Achievement findByAchievementId(long achievementId);
	
	void saveAchievement(Achievement achievement);

	void updateAchievement(Achievement achievement);

	void deleteAchievementById(long achievementId);

	List<Achievement> findAllAchievements();

}
