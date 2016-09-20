/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.AchievementEarned;

/**
 * @author Angad Gill
 *
 */
public interface AchievementEarnedService {

	AchievementEarned findByAchievementEarnedId(long achievementEarnedId);
	
	void saveAchievementEarned(AchievementEarned achievementEarned);

	void updateAchievementEarned(AchievementEarned achievementEarned);

	void deleteAchievementEarnedById(long achievementEarnedId);

	List<AchievementEarned> findAllAchievementsEarned();
	
	List<AchievementEarned> findAllByUserId(long userId);

}
