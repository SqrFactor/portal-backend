/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.AchievementEarnedDao;
import com.sqrfactor.model.AchievementEarned;
import com.sqrfactor.service.AchievementEarnedService;

/**
 * @author Angad Gill
 *
 */
@Service("achievementEarnedService")
@Transactional
public class AchievementEarnedServiceImpl implements AchievementEarnedService{

	@Autowired
	private AchievementEarnedDao achievementEarnedDao;

	/**
	 * Find All Achievements Earned
	 */
	public List<AchievementEarned> findAllAchievementsEarned() {
		return achievementEarnedDao.findAllAchievementsEarned();
	}

	/**
	 * Find Achievement Earned by id
	 */
	public AchievementEarned findByAchievementEarnedId(long achievementEarnedId) {
		return achievementEarnedDao.findByAchievementEarnedId(achievementEarnedId);
	}
	
	/**
	 * Save Achievement Earned
	 */
	public void saveAchievementEarned(AchievementEarned achievementEarned) {
		achievementEarnedDao.saveAchievementEarned(achievementEarned);
	}

	/**
	 * Update Achievement Earned
	 */
	public void updateAchievementEarned(AchievementEarned achievementEarned) {
		AchievementEarned entity = achievementEarnedDao.findByAchievementEarnedId(achievementEarned.getAchievementEarnedId());
		if (entity != null) {
			entity.setAchievementId(achievementEarned.getAchievementId());
			entity.setUserId(achievementEarned.getUserId());
		}
	}

	@Override
	public void deleteAchievementEarnedById(long achievementEarnedId) {
		achievementEarnedDao.deleteAchievementEarnedById(achievementEarnedId);
	}
	
	@Override
	public List<AchievementEarned> findAllByUserId(long userId) {
		return achievementEarnedDao.findAllByUserId(userId);
	}
}
