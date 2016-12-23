/**
 * 
 */
package com.sqrfactor.controller;

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
import com.sqrfactor.model.EnrichedEducation;
import com.sqrfactor.model.Profession;
import com.sqrfactor.model.User;
import com.sqrfactor.model.competition.CompetitionSubmission;
import com.sqrfactor.service.CollegeService;
import com.sqrfactor.service.ConnectionService;
import com.sqrfactor.service.EducationService;
import com.sqrfactor.service.ProfessionService;
import com.sqrfactor.service.UserService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class SuggestionController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConnectionService connectionService;
	
	@Autowired
	private EducationService educationService;
	
	@Autowired
	private ProfessionService professionService;
	
	@Autowired
	private CollegeService collegeService;
	
	/**
	 * Get friend suggestions by user Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/suggest/connections/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SuggestedUser>> getSuggestedConnectionsByUserId(@PathVariable long userId) {
		List<SuggestedUser> suggestedConnections = new ArrayList<>();
		
		User currentUser = userService.findById(userId);
		if(currentUser == null){
			return new ResponseEntity<List<SuggestedUser>>(HttpStatus.NOT_FOUND);
		}
		
		//Get the colleges user has studied in 
		List<String>currentUserColCodes =  getColCodes(userId);
		
		//Find all the people he/she is following
		List<Connection> currentUserConnections = connectionService.findConnectionsBySourceId(userId);
		List<Long> currentUserFollowing = new ArrayList<>();
		for(Connection connection : currentUserConnections){
			currentUserFollowing.add(connection.getDestinationId());
		}
		
		List<User> users = userService.findAllUsers();
		for(User u : users){
			//Skip for current user
			if(u.getUserId() == userId){
				continue;
			}
			//See if the platform user has studied in the same college and is not connected
			List<String> uColCodes = getColCodes(u.getUserId());
			for(String uColCode : uColCodes){
				if(currentUserColCodes.contains(uColCode)){
					if(!currentUserFollowing.contains(u.getUserId())){
		
						Education education = getRecentEducationByUserId(u.getUserId());
						EnrichedEducation enrichedEducation = null;
						if(education != null){
							College college = collegeService.findByColCode(education.getColCode());
							if(college != null){
								enrichedEducation = new EnrichedEducation(education, college.getColName());
							}else{
								enrichedEducation = new EnrichedEducation(education, "");
							}
						}
						
						Profession profession = getRecentProfessionByUserId(u.getUserId());
						SuggestedUser suggestedUser = new SuggestedUser(u, enrichedEducation, profession);
		
						suggestedConnections.add(suggestedUser);
						break;
					}
				}	
			}
		}
		
		return new ResponseEntity<List<SuggestedUser>>(suggestedConnections, HttpStatus.OK);
	
	}
	
	private Education getRecentEducationByUserId(long userId) {
		List<Education> educations = educationService.findEducationsByUserId(userId);
		if (educations.isEmpty()) {
			return null;
		}
		
		//Sort to find the most recent
		educations.sort(new Comparator<Education>() {
			@Override
			public int compare(Education o1, Education o2) {
				if(Integer.parseInt(o1.getEducationToDate()) > Integer.parseInt(o2.getEducationToDate())){
					return -1;
				} else if(Integer.parseInt(o1.getEducationToDate()) < Integer.parseInt(o2.getEducationToDate())){
					return 1;
				}else{
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
		
		//Sort to find the most recent
		professions.sort(new Comparator<Profession>() {
			@Override
			public int compare(Profession o1, Profession o2) {
				Long o1ProfessionToDate = Long.parseLong(o1.getProfessionToDate());
				Long o2ProfessionToDate = Long.parseLong(o2.getProfessionToDate());
				Date now = new Date();
				
				if(o1.getProfessionToDate() == null){
					o1ProfessionToDate = now.getTime(); 
				}
				if(o2.getProfessionToDate() == null){
					o1ProfessionToDate = now.getTime();
				}
				
				if(o1ProfessionToDate > o2ProfessionToDate){
					return -1;
				} else if(o1ProfessionToDate < o2ProfessionToDate){
					return 1;
				}else{
					return 0;
				}
			}
		});
		
		return professions.get(0);
	}


	private List<String> getColCodes(long userId){
		List<String> colCodes = new ArrayList<>();
		for(Education education : educationService.findEducationsByUserId(userId)){
			if(StringUtils.isNotBlank(education.getColCode())){
				colCodes.add(education.getColCode());
			}
		}
		return colCodes;
	}
	
	private class SuggestedUser extends User{
		
		private EnrichedEducation education;
		private Profession profession;
		
		public SuggestedUser(User user, EnrichedEducation education, Profession profession){
			super(user);
			this.education = education;
			this.profession = profession;
		}

		/**
		 * @return the education
		 */
		public Education getEducation() {
			return education;
		}

		/**
		 * @param education the education to set
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
		 * @param profession the profession to set
		 */
		public void setProfession(Profession profession) {
			this.profession = profession;
		}
	}

}
