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
import com.sqrfactor.dao.AnnouncementDao;
import com.sqrfactor.model.Announcement;

/**
 * @author Angad Gill
 *
 */

@Repository("announcementDao")
public class AnnouncementDaoImpl extends AbstractDao<Long, Announcement> implements AnnouncementDao {

	public Announcement findById(long id) {
		return getByKey(id);
	}

	public Announcement findByAnnouncementId(long announcementId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("announcementId", announcementId));
		return (Announcement) criteria.uniqueResult();
	}

	public void saveAnnouncement(Announcement announcement) {
		persist(announcement);
	}

	public void deleteAnnouncementById(long announcementId) {
		Query query = getSession()
				.createSQLQuery("delete from announcement_details where announcementId = :announcementId");
		query.setLong("announcementId", announcementId);
		query.executeUpdate();
	}

	@Override
	public List<Announcement> findAllAnnouncements() {
		Criteria criteria = createEntityCriteria();
		return (List<Announcement>) criteria.list();
	}

}
