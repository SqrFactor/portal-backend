/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Announcement;

/**
 * @author Angad Gill
 *
 */
public interface AnnouncementDao {

	Announcement findByAnnouncementId(long announcementId);
	
	void saveAnnouncement(Announcement announcement);

	void deleteAnnouncementById(long announcementId);

	List<Announcement> findAllAnnouncements();
}
