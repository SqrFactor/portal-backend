/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.AchievementDao;
import com.sqrfactor.model.Achievement;
import com.sqrfactor.service.AchievementService;

/**
 * @author Angad Gill
 *
 */
@Service("achievementService")
@Transactional
public class AchievementServiceImpl implements AchievementService{

	@Autowired
	private AchievementDao achievementDao;

	/**
	 * Find All Achievements
	 */
	public List<Achievement> findAllAchievements() {
		return achievementDao.findAllAchievements();
	}

	/**
	 * Find Achievement by id
	 */
	public Achievement findByAchievementId(long achievementId) {
		return achievementDao.findByAchievementId(achievementId);
	}
	
	/**
	 * Save Achievements
	 */
	public void saveAchievement(Achievement achievement) {
		achievementDao.saveAchievement(achievement);
	}

	/**
	 * Update Achievement
	 */
	public void updateAchievement(Achievement achievement) {
		Achievement entity = achievementDao.findByAchievementId(achievement.getAchievementId());
		if (entity != null) {
			entity.setAchievementName(achievement.getAchievementName());
			entity.setAchievementDescription(achievement.getAchievementDescription());
			
		}
	}

	@Override
	public void deleteAchievementById(long achievementId) {
		achievementDao.deleteAchievementById(achievementId);
	}
}
