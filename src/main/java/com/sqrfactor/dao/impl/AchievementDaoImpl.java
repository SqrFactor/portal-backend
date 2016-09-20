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
import com.sqrfactor.dao.AchievementDao;
import com.sqrfactor.model.Achievement;

/**
 * @author Angad Gill
 *
 */
@Repository("achievementDao")
public class AchievementDaoImpl extends AbstractDao<Long, Achievement>implements AchievementDao{

	public Achievement findById(long id) {
		return getByKey(id);
	}
	
	public Achievement findByAchievementId(long achievementId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("achievementId", achievementId));
		return (Achievement) criteria.uniqueResult();
	}
	
	public void saveAchievement(Achievement achievement) {
		persist(achievement);
	}

	public void deleteAchievementById(long achievementId) {
		Query query = getSession().createSQLQuery("delete from achievement_details where achievementId = :achievementId");
		query.setLong("achievementId", achievementId);
		query.executeUpdate();
	}

	@Override
	public List<Achievement> findAllAchievements() {
		Criteria criteria = createEntityCriteria();
		return (List<Achievement>) criteria.list();
	}


}
