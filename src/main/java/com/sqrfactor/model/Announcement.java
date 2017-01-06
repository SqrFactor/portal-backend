/**
 * 
 */
package com.sqrfactor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "announcement_details")
public class Announcement {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "announcementId")
		private long announcementId;
		
		@Column(name = "userId", nullable = false)
		private long userId;
		
		@Column(name = "announcementType", nullable = false)
		private String announcementType;
		
		@Column(name = "announcementPic")
		private String announcementPic;

		@Column(name = "announcementTitle")
		private String announcementTitle;

		@Column(name = "announcementDesc")
		private String announcementDesc;
		
		@Column(name = "placeName")
		private String placeName;
		
		@Column(name = "placeAddress")
		private String placeAddress;
		
		@Column(name = "placeLat")
		private double placeLat;
		
		@Column(name = "placeLng")
		private double placeLng;
		
		@Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "createdAt", nullable = false)
	    private Date createdAt;

		public Announcement(){}
		
		/**
		 * @param announcementId
		 * @param userId
		 * @param announcementType
		 * @param announcementPic
		 * @param announcementTitle
		 * @param announcementDesc
		 * @param placeName
		 * @param placeAddress
		 * @param placeLat
		 * @param placeLng
		 * @param createdAt
		 */
		public Announcement(Announcement announcement) {
			super();
			this.announcementId = announcement.getAnnouncementId();
			this.userId = announcement.getUserId();
			this.announcementType = announcement.getAnnouncementType();
			this.announcementPic = announcement.getAnnouncementPic();
			this.announcementTitle = announcement.getAnnouncementTitle();
			this.announcementDesc = announcement.getAnnouncementDesc();
			this.placeName = announcement.getPlaceName();
			this.placeAddress = announcement.getPlaceAddress();
			this.placeLat = announcement.getPlaceLat();
			this.placeLng = announcement.getPlaceLng();
			this.createdAt = announcement.getCreatedAt();
		}



		/**
		 * @return the announcementId
		 */
		public long getAnnouncementId() {
			return announcementId;
		}

		/**
		 * @param announcementId the announcementId to set
		 */
		public void setAnnouncementId(long announcementId) {
			this.announcementId = announcementId;
		}

		/**
		 * @return the userId
		 */
		public long getUserId() {
			return userId;
		}

		/**
		 * @param userId the userId to set
		 */
		public void setUserId(long userId) {
			this.userId = userId;
		}

		/**
		 * @return the announcementType
		 */
		public String getAnnouncementType() {
			return announcementType;
		}

		/**
		 * @param announcementType the announcementType to set
		 */
		public void setAnnouncementType(String announcementType) {
			this.announcementType = announcementType;
		}

		/**
		 * @return the announcementPic
		 */
		public String getAnnouncementPic() {
			return announcementPic;
		}

		/**
		 * @param announcementPic the announcementPic to set
		 */
		public void setAnnouncementPic(String announcementPic) {
			this.announcementPic = announcementPic;
		}

		/**
		 * @return the announcementTitle
		 */
		public String getAnnouncementTitle() {
			return announcementTitle;
		}

		/**
		 * @param announcementTitle the announcementTitle to set
		 */
		public void setAnnouncementTitle(String announcementTitle) {
			this.announcementTitle = announcementTitle;
		}

		/**
		 * @return the announcementDesc
		 */
		public String getAnnouncementDesc() {
			return announcementDesc;
		}

		/**
		 * @param announcementDesc the announcementDesc to set
		 */
		public void setAnnouncementDesc(String announcementDesc) {
			this.announcementDesc = announcementDesc;
		}

		/**
		 * @return the placeName
		 */
		public String getPlaceName() {
			return placeName;
		}

		/**
		 * @param placeName the placeName to set
		 */
		public void setPlaceName(String placeName) {
			this.placeName = placeName;
		}

		/**
		 * @return the placeAddress
		 */
		public String getPlaceAddress() {
			return placeAddress;
		}

		/**
		 * @param placeAddress the placeAddress to set
		 */
		public void setPlaceAddress(String placeAddress) {
			this.placeAddress = placeAddress;
		}

		/**
		 * @return the placeLat
		 */
		public double getPlaceLat() {
			return placeLat;
		}

		/**
		 * @param placeLat the placeLat to set
		 */
		public void setPlaceLat(double placeLat) {
			this.placeLat = placeLat;
		}

		/**
		 * @return the placeLng
		 */
		public double getPlaceLng() {
			return placeLng;
		}

		/**
		 * @param placeLng the placeLng to set
		 */
		public void setPlaceLng(double placeLng) {
			this.placeLng = placeLng;
		}

		/**
		 * @return the createdAt
		 */
		public Date getCreatedAt() {
			return createdAt;
		}

		/**
		 * @param createdAt the createdAt to set
		 */
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		
}