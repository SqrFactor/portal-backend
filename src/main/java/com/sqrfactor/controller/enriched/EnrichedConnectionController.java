package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.College;
import com.sqrfactor.model.Connection;
import com.sqrfactor.model.Education;
import com.sqrfactor.model.EnrichedConnection;
import com.sqrfactor.model.EnrichedEducation;
import com.sqrfactor.model.EnrichedFeed;
import com.sqrfactor.model.Profession;
import com.sqrfactor.model.User;
import com.sqrfactor.service.CollegeService;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.EducationService;
import com.sqrfactor.service.ProfessionService;
import com.sqrfactor.service.UserService;

/**
 * 
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedConnectionController {

	@Autowired
	private UserService userService;

	@Autowired
	private ConnectionService connectionService;

	@Autowired
	private CollegeService collegeService;

	@Autowired
	private EducationService educationService;

	@Autowired
	private ProfessionService professionService;

	/**
	 * Get all enriched connections by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/connection/enriched/sourceid/{sourceId}", method = RequestMethod.GET)
	public ResponseEntity<List<EnrichedConnectionWithRecent>> getConnectionsBySourceId(
			@PathVariable("sourceId") long sourceId) {
		List<EnrichedConnectionWithRecent> enrichedConnectionsWithRecent = new ArrayList<EnrichedConnectionWithRecent>();

		List<Connection> connections = connectionService.findConnectionsBySourceId(sourceId);
		if (connections.isEmpty()) {
			return new ResponseEntity<List<EnrichedConnectionWithRecent>>(HttpStatus.NOT_FOUND);
		}

		User sourceUser = userService.findById(sourceId);

		for (Connection connection : connections) {

			User destinationUser = userService.findById(connection.getDestinationId());
			if (destinationUser == null) {
				continue;
			}

			String destinationName = getName(destinationUser);
			String destinationProfilePicPath = getProfilePicPath(destinationUser);

			String sourceName = getName(sourceUser);
			String sourceProfilePicPath = getProfilePicPath(sourceUser);

			EnrichedConnection enrichedConnection = new EnrichedConnection(connection, sourceUser.getUserTypeId(),
					destinationUser.getUserTypeId(), destinationName, destinationProfilePicPath, sourceName,
					sourceProfilePicPath);

			// Add Recent details
			Education education = getRecentEducationByUserId(enrichedConnection.getDestinationId());
			EnrichedEducation enrichedEducation = null;
			if (education != null) {
				College college = collegeService.findByColCode(education.getColCode());
				if (college != null) {
					enrichedEducation = new EnrichedEducation(education, college.getColName());
				} else {
					enrichedEducation = new EnrichedEducation(education, "");
				}
			}

			Profession profession = getRecentProfessionByUserId(enrichedConnection.getDestinationId());

			EnrichedConnectionWithRecent enrichedConnectionWithRecent = new EnrichedConnectionWithRecent(
					enrichedConnection, enrichedEducation, profession);
			enrichedConnectionsWithRecent.add(enrichedConnectionWithRecent);
		}

		return new ResponseEntity<List<EnrichedConnectionWithRecent>>(enrichedConnectionsWithRecent, HttpStatus.OK);
	}

	/**
	 * Get all enriched connections by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/connection/enriched/destinationid/{destinationId}", method = RequestMethod.GET)
	public ResponseEntity<List<EnrichedConnectionWithRecent>> getConnectionsByDestinationId(
			@PathVariable("destinationId") long destinationId) {
		List<EnrichedConnectionWithRecent> enrichedConnectionsWithRecent = new ArrayList<EnrichedConnectionWithRecent>();

		List<Connection> connections = connectionService.findConnectionsByDestinationId(destinationId);
		if (connections.isEmpty()) {
			return new ResponseEntity<List<EnrichedConnectionWithRecent>>(HttpStatus.NOT_FOUND);
		}

		User destinationUser = userService.findById(destinationId);

		for (Connection connection : connections) {

			User sourceUser = userService.findById(connection.getSourceId());
			if (sourceUser == null) {
				continue;
			}

			String destinationName = getName(destinationUser);
			String destinationProfilePicPath = getProfilePicPath(destinationUser);

			String sourceName = getName(sourceUser);
			String sourceProfilePicPath = getProfilePicPath(sourceUser);

			EnrichedConnection enrichedConnection = new EnrichedConnection(connection, sourceUser.getUserTypeId(),
					destinationUser.getUserTypeId(), destinationName, destinationProfilePicPath, sourceName,
					sourceProfilePicPath);

			// Add Recent details
			Education education = getRecentEducationByUserId(enrichedConnection.getSourceId());
			EnrichedEducation enrichedEducation = null;
			if (education != null) {
				College college = collegeService.findByColCode(education.getColCode());
				if (college != null) {
					enrichedEducation = new EnrichedEducation(education, college.getColName());
				} else {
					enrichedEducation = new EnrichedEducation(education, "");
				}
			}

			Profession profession = getRecentProfessionByUserId(enrichedConnection.getSourceId());

			EnrichedConnectionWithRecent enrichedConnectionWithRecent = new EnrichedConnectionWithRecent(
					enrichedConnection, enrichedEducation, profession);
			enrichedConnectionsWithRecent.add(enrichedConnectionWithRecent);

		}

		return new ResponseEntity<List<EnrichedConnectionWithRecent>>(enrichedConnectionsWithRecent, HttpStatus.OK);
	}

	private Education getRecentEducationByUserId(long userId) {
		List<Education> educations = educationService.findEducationsByUserId(userId);
		if (educations.isEmpty()) {
			return null;
		}

		// Sort to find the most recent
		educations.sort(new Comparator<Education>() {
			@Override
			public int compare(Education o1, Education o2) {
				try {
					if (Integer.parseInt(o1.getEducationToDate()) > Integer.parseInt(o2.getEducationToDate())) {
						return -1;
					} else if (Integer.parseInt(o1.getEducationToDate()) < Integer.parseInt(o2.getEducationToDate())) {
						return 1;
					} else {
						return 0;
					}
				} catch (Exception e) {
					return 0;
				}
			}
		});

		return educations.get(0);
	}

	private Profession getRecentProfessionByUserId(long userId) {
		List<Profession> professions = professionService.findProfessionsByUserId(userId);
		if (professions.isEmpty()) {
			return null;
		}

		// Sort to find the most recent
		professions.sort(new Comparator<Profession>() {
			@Override
			public int compare(Profession o1, Profession o2) {
				try {
					Long o1ProfessionToDate = Long.parseLong(o1.getProfessionToDate());
					Long o2ProfessionToDate = Long.parseLong(o2.getProfessionToDate());

					Date now = new Date();

					if (o1.getProfessionToDate() == null) {
						o1ProfessionToDate = now.getTime();
					}
					if (o2.getProfessionToDate() == null) {
						o1ProfessionToDate = now.getTime();
					}

					if (o1ProfessionToDate > o2ProfessionToDate) {
						return -1;
					} else if (o1ProfessionToDate < o2ProfessionToDate) {
						return 1;
					} else {
						return 0;
					}
				} catch (Exception e) {
					return 0;
				}
			}
		});

		return professions.get(0);
	}

	private List<String> getColCodes(long userId) {
		List<String> colCodes = new ArrayList<>();
		for (Education education : educationService.findEducationsByUserId(userId)) {
			if (StringUtils.isNotBlank(education.getColCode())) {
				colCodes.add(education.getColCode());
			}
		}
		return colCodes;
	}

	private String getName(User user) {
		String firstName = "";
		String lastName = "";
		if (user.getFirstName() != null) {
			firstName = user.getFirstName();
		}
		if (user.getLastName() != null) {
			lastName = user.getLastName();
		}
		String name = firstName + " " + lastName;

		return name;
	}

	private String getProfilePicPath(User user) {
		String profilePicPath = "";

		if (user.getProfilePicPath() != null) {
			profilePicPath = user.getProfilePicPath();
		}
		return profilePicPath;
	}

	private class EnrichedConnectionWithRecent {

		private EnrichedEducation education;
		private Profession profession;
		private EnrichedConnection enrichedConnection;

		public EnrichedConnectionWithRecent(EnrichedConnection enrichedConnection, EnrichedEducation education,
				Profession profession) {
			super();
			this.enrichedConnection = enrichedConnection;
			this.education = education;
			this.profession = profession;
		}

		/**
		 * @return the enrichedConnection
		 */
		public EnrichedConnection getEnrichedConnection() {
			return enrichedConnection;
		}

		/**
		 * @param enrichedConnection
		 *            the enrichedConnection to set
		 */
		public void setEnrichedConnection(EnrichedConnection enrichedConnection) {
			this.enrichedConnection = enrichedConnection;
		}

		/**
		 * @return the education
		 */
		public Education getEducation() {
			return education;
		}

		/**
		 * @param education
		 *            the education to set
		 */
		public void setEducation(EnrichedEducation education) {
			this.education = education;
		}

		/**
		 * @return the profession
		 */
		public Profession getProfession() {
			return profession;
		}

		/**
		 * @param profession
		 *            the profession to set
		 */
		public void setProfession(Profession profession) {
			this.profession = profession;
		}

	}
}