/**
 * 
 */
package com.sqrfactor.controller.enriched;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Achievement;
import com.sqrfactor.model.AchievementEarned;
import com.sqrfactor.model.EnrichedAchievementEarned;
import com.sqrfactor.service.AchievementEarnedService;
import com.sqrfactor.service.AchievementService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EnrichedAchievementEarnedController {

	@Autowired
	private AchievementEarnedService achievementEarnedService;
	
	@Autowired
	private AchievementService achievementService;
	
	/**
	 * Get Enriched Achievement Earned by User Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/achievementearned/enriched/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EnrichedAchievementEarned>> getByUserId(@PathVariable int userId) {
		List<EnrichedAchievementEarned> enrichedAchievementsEarned = new ArrayList<EnrichedAchievementEarned>();
		
		List<AchievementEarned> achievementsEarned = achievementEarnedService.findAllByUserId(userId);
		if (achievementsEarned == null) {
			return new ResponseEntity<List<EnrichedAchievementEarned>>(HttpStatus.NOT_FOUND);
		}
		
		for(AchievementEarned achievementEarned : achievementsEarned){
			
			Achievement achievement = achievementService.findByAchievementId(achievementEarned.getAchievementId());
					
			if(achievement == null){
				continue;
			}
			
			String achievementName = achievement.getAchievementName();
			String achievementDescription = achievement.getAchievementDescription();
			
			EnrichedAchievementEarned  enrichedAchievementEarned = new EnrichedAchievementEarned(achievementEarned, achievementName, achievementDescription);
			enrichedAchievementsEarned.add(enrichedAchievementEarned);		
		}
		
		return new ResponseEntity<List<EnrichedAchievementEarned>>(enrichedAchievementsEarned, HttpStatus.OK);
	}
}
