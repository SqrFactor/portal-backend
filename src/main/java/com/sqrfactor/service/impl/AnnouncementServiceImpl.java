/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.model.Announcement;
import com.sqrfactor.service.AnnouncementService;
import com.sqrfactor.dao.AnnouncementDao;

/**
 * @author Angad Gill
 *
 */
@Service("announcementService")
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService{

	@Autowired
	private AnnouncementDao announcementDao;

	/**
	 * Find All Announcements
	 */
	public List<Announcement> findAllAnnouncements() {
		return announcementDao.findAllAnnouncements();
	}

	/**
	 * Find Announcement by id
	 */
	public Announcement findByAnnouncementId(long announcementId) {
		return announcementDao.findByAnnouncementId(announcementId);
	}
	
	/**
	 * Save Announcements
	 */
	public void saveAnnouncement(Announcement announcement) {
		announcementDao.saveAnnouncement(announcement);
	}

	/**
	 * Update Announcement
	 */
	public void updateAnnouncement(Announcement announcement) {
		Announcement entity = announcementDao.findByAnnouncementId(announcement.getAnnouncementId());
		if (entity != null) {
			entity.setUserId(announcement.getUserId());
			entity.setAnnouncementType(announcement.getAnnouncementType());
			entity.setAnnouncementPic(announcement.getAnnouncementPic());
			entity.setAnnouncementTitle(announcement.getAnnouncementTitle());
			entity.setAnnouncementDesc(announcement.getAnnouncementDesc());
			entity.setPlaceName(announcement.getPlaceName());
			entity.setPlaceAddress(announcement.getPlaceAddress());
			entity.setPlaceLat(announcement.getPlaceLat());
			entity.setPlaceLng(announcement.getPlaceLng());
		}
	}

	@Override
	public void deleteAnnouncementById(long announcementId) {
		announcementDao.deleteAnnouncementById(announcementId);
	}
}
