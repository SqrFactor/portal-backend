/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.AchievementEarned;

/**
 * @author Angad Gill
 *
 */
public interface AchievementEarnedDao {

	AchievementEarned findByAchievementEarnedId(long achievementEarnedId);
	
	void saveAchievementEarned(AchievementEarned achievementEarned);

	void deleteAchievementEarnedById(long achievementId);

	List<AchievementEarned> findAllAchievementsEarned();
	
	List<AchievementEarned> findAllByUserId(long userId);
}
