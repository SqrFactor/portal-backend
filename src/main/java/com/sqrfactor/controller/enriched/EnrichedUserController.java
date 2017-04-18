/**
 * 
 */
package com.sqrfactor.controller.enriched;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.College;
import com.sqrfactor.model.Education;
import com.sqrfactor.model.EnrichedEducation;
import com.sqrfactor.model.EnrichedUser;
import com.sqrfactor.model.Profession;
import com.sqrfactor.model.User;
import com.sqrfactor.service.CollegeService;
import com.sqrfactor.service.EducationService;
import com.sqrfactor.service.ProfessionService;
import com.sqrfactor.service.UserService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedUserController {

	@Autowired
	private EducationService educationService;
	
	@Autowired
	private CollegeService collegeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessionService professionService;
	
	public EnrichedUserController(){}
	
	/**
	 * Get a User by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "enriched/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<EnrichedUser> getUserById(@PathVariable int id) {
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<EnrichedUser>(HttpStatus.NOT_FOUND);
		}
		
		//Get education
		Education education = getRecentEducationByUserId(id);
		EnrichedEducation recentEducation = null;
		if (education != null) {
			College college = collegeService.findByColCode(education.getColCode());
			if (college != null) {
				recentEducation = new EnrichedEducation(education, college.getColName());
			} else {
				recentEducation = new EnrichedEducation(education, "");
			}
		}
		
		//Get profession
		Profession recentProfession = getRecentProfessionByUserId(id);
		EnrichedUser enrichedUser = new EnrichedUser(user, recentEducation, recentProfession);
		
		return new ResponseEntity<EnrichedUser>(enrichedUser, HttpStatus.OK);
	}
	
	public Education getRecentEducationByUserId(long userId) {
		List<Education> educations = educationService.findEducationsByUserId(userId);
		if (educations.isEmpty()) {
			return new Education();
		}
		
		//Sort to find the most recent
		educations.sort(new Comparator<Education>() {
			@Override
			public int compare(Education o1, Education o2) {
				try {
					Long o1EducationToDate = Long.parseLong(o1.getEducationToDate());
					Long o2EducationToDate = Long.parseLong(o2.getEducationToDate());

					Date now = new Date();

					if (o1.getEducationToDate() == null) {
						o1EducationToDate = now.getTime();
					}
					if (o2.getEducationToDate() == null) {
						o1EducationToDate = now.getTime();
					}

					if (o1EducationToDate > o2EducationToDate) {
						return -1;
					} else if (o1EducationToDate < o2EducationToDate) {
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
	
	public Profession getRecentProfessionByUserId(long userId) {
		List<Profession> professions = professionService.findProfessionsByUserId(userId);
		if (professions.isEmpty()) {
			return new Profession();
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
}
