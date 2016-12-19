/**
 * 
 */
package com.sqrfactor.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.Profession;
import com.sqrfactor.service.ProfessionService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class ProfessionController {

	@Autowired
	private ProfessionService professionService;

	public ProfessionController() {

	}

	/**
	 * Get all the professions
	 * 
	 * @return
	 */
	@RequestMapping(value = "/profession/", method = RequestMethod.GET)
	public ResponseEntity<List<Profession>> getAllProfessions() {
		List<Profession> professions = professionService.findAllProfessions();
		if (professions.isEmpty()) {
			return new ResponseEntity<List<Profession>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Profession>>(professions, HttpStatus.OK);
	}

	/**
	 * Get Profession by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/profession/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Profession> getProfessionById(@PathVariable int id) {
		Profession profession = professionService.findById(id);
		if (profession == null) {
			return new ResponseEntity<Profession>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Profession>(profession, HttpStatus.OK);
	}

	/**
	 * Create Profession
	 * 
	 * @param profession
	 */
	@RequestMapping(value = "/profession/", method = RequestMethod.POST)
	public ResponseEntity<Profession> createProfession(@RequestBody Profession profession) {

		professionService.saveProfession(profession);

		return new ResponseEntity<Profession>(profession, HttpStatus.CREATED);
	}

	/**
	 * Update Profession
	 * 
	 * @param id
	 * @param profession
	 * @return
	 */
	@RequestMapping(value = "/profession/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Profession> updateProfession(@PathVariable("id") int id, @RequestBody Profession profession) {
		Profession currentProfession = professionService.findById(id);

		if (currentProfession == null) {
			return new ResponseEntity<Profession>(HttpStatus.NOT_FOUND);
		}
		profession.setId(id);
		professionService.updateProfession(profession);
		return new ResponseEntity<Profession>(profession, HttpStatus.OK);
	}

	/**
	 * Delete Profession by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/profession/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Profession> deleteProfession(@PathVariable("id") int id) {
		Profession profession = professionService.findById(id);
		if (profession == null) {
			return new ResponseEntity<Profession>(HttpStatus.NOT_FOUND);
		}

		professionService.deleteProfessionById(id);

		return new ResponseEntity<Profession>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Get all profession by userId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/profession/userid/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<Profession>> getProfessionsByUserId(@PathVariable("userId") long userId) {
		List<Profession> professions = professionService.findProfessionsByUserId(userId);
		if (professions.isEmpty()) {
			return new ResponseEntity<List<Profession>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Profession>>(professions, HttpStatus.OK);
	}

	/**
	 * Get most recent profession by userId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/profession/recent/userid/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Profession> getRecentProfessionByUserId(@PathVariable("userId") long userId) {
		List<Profession> professions = professionService.findProfessionsByUserId(userId);
		if (professions.isEmpty()) {
			return new ResponseEntity<Profession>(HttpStatus.NOT_FOUND);
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
		
		return new ResponseEntity<Profession>(professions.get(0), HttpStatus.OK);
	}
	
}
