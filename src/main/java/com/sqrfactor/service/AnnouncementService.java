/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Announcement;

/**
 * @author Angad Gill
 *
 */
public interface AnnouncementService {

	Announcement findByAnnouncementId(long announcementId);

	void saveAnnouncement(Announcement announcement);

	void updateAnnouncement(Announcement announcement);

	void deleteAnnouncementById(long announcementId);

	List<Announcement> findAllAnnouncements();

}
