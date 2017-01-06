/**
 * 
 */
package com.sqrfactor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Announcement;
import com.sqrfactor.service.AnnouncementService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;

	public AnnouncementController() {

	}

	/**
	 * Get all the announcements
	 * 
	 * @return
	 */
	@RequestMapping(value = "/announcement/", method = RequestMethod.GET)
	public ResponseEntity<List<Announcement>> getAllAnnouncements() {
		List<Announcement> announcements = announcementService.findAllAnnouncements();
		if (announcements.isEmpty()) {
			return new ResponseEntity<List<Announcement>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Announcement>>(announcements, HttpStatus.OK);
	}

	/**
	 * Get Announcement by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/announcement/{announcementId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Announcement> getAnnouncementById(@PathVariable int announcementId) {
		Announcement announcement = announcementService.findByAnnouncementId(announcementId);
		if (announcement == null) {
			return new ResponseEntity<Announcement>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Announcement>(announcement, HttpStatus.OK);
	}

	/**
	 * Create Announcement
	 * 
	 * @param announcement
	 */
	@RequestMapping(value = "/announcement/", method = RequestMethod.POST)
	public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {

		announcementService.saveAnnouncement(announcement);

		return new ResponseEntity<Announcement>(announcement, HttpStatus.CREATED);
	}

	/**
	 * Update Announcement
	 * 
	 * @param id
	 * @param announcement
	 * @return
	 */
	@RequestMapping(value = "/announcement/{announcementId}", method = RequestMethod.PUT)
	public ResponseEntity<Announcement> updateAnnouncement(@PathVariable("announcementId") int announcementId,
			@RequestBody Announcement announcement) {
		Announcement currentAnnouncement = announcementService.findByAnnouncementId(announcementId);

		if (currentAnnouncement == null) {
			return new ResponseEntity<Announcement>(HttpStatus.NOT_FOUND);
		}
		announcement.setAnnouncementId(announcementId);
		announcementService.updateAnnouncement(announcement);
		return new ResponseEntity<Announcement>(announcement, HttpStatus.OK);
	}

	/**
	 * Delete Announcement by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/announcement/{announcementId}", method = RequestMethod.DELETE)
	public ResponseEntity<Announcement> deleteAnnouncement(@PathVariable("announcementId") int announcementId) {
		Announcement announcement = announcementService.findByAnnouncementId(announcementId);
		if (announcement == null) {
			return new ResponseEntity<Announcement>(HttpStatus.NOT_FOUND);
		}

		announcementService.deleteAnnouncementById(announcementId);

		return new ResponseEntity<Announcement>(HttpStatus.NO_CONTENT);
	}
}
