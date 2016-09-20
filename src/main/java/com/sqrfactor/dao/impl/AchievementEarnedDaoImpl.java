/**
 * 
 */
package com.sqrfactor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.AchievementEarnedDao;
import com.sqrfactor.model.AchievementEarned;
import com.sqrfactor.model.Connection;

/**
 * @author Angad Gill
 *
 */
@Repository("achievementEarnedDao")
public class AchievementEarnedDaoImpl extends AbstractDao<Long, AchievementEarned>implements AchievementEarnedDao {

	public AchievementEarned findById(long id) {
		return getByKey(id);
	}
	
	public AchievementEarned findByAchievementEarnedId(long achievementEarnedId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("achievementEarnedId", achievementEarnedId));
		return (AchievementEarned) criteria.uniqueResult();
	}
	
	public void saveAchievementEarned(AchievementEarned achievementEarned) {
		persist(achievementEarned);
	}

	public void deleteAchievementEarnedById(long achievementEarnedId) {
		Query query = getSession().createSQLQuery("delete from achievement_details where achievementEarnedId = :achievementEarnedId");
		query.setLong("achievementEarnedId", achievementEarnedId);
		query.executeUpdate();
	}

	@Override
	public List<AchievementEarned> findAllAchievementsEarned() {
		Criteria criteria = createEntityCriteria();
		return (List<AchievementEarned>) criteria.list();
	}
	
	@Override
	public List<AchievementEarned> findAllByUserId(long userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return  (List<AchievementEarned>)criteria.list();
	}
	
	
}
