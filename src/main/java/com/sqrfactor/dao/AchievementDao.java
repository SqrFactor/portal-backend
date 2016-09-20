/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Achievement;

/**
 * @author Angad Gill
 *
 */
public interface AchievementDao {

	Achievement findByAchievementId(long achievementId);
	
	void saveAchievement(Achievement achievement);

	void deleteAchievementById(long achievementId);

	List<Achievement> findAllAchievements();

}
