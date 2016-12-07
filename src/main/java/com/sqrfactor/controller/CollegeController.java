/**
 * 
 */
package com.sqrfactor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.College;
import com.sqrfactor.model.EnrichedCollege;
import com.sqrfactor.model.User;
import com.sqrfactor.service.CollegeService;
import com.sqrfactor.service.UserService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class CollegeController {

	@Autowired
	private CollegeService collegeService;

	@Autowired
	private UserService userService;
	
	public CollegeController() {
	}

	/**
	 * Get all the colleges
	 * 
	 * @return
	 */
	@RequestMapping(value = "/college/", method = RequestMethod.GET)
	public ResponseEntity<List<College>> getAllColleges() {
		List<College> colleges = collegeService.findAllColleges();
		if (colleges.isEmpty()) {
			return new ResponseEntity<List<College>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<College>>(colleges, HttpStatus.OK);
	}

	/**
	 * Get a College by colCode
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/college/enriched/colcode/{colCode}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<EnrichedCollege> getCollegeByColCode(@PathVariable String colCode) {
		College college = collegeService.findByColCode(colCode);
		if (college == null) {
			return new ResponseEntity<EnrichedCollege>(HttpStatus.NOT_FOUND);
		}
		
		User user = userService.findByEmailId(college.getColCode() + "@sqrfactor.in");
		
		EnrichedCollege enrichedCollege;
		if(user != null){
			enrichedCollege = new EnrichedCollege(college, user.getUserId());
		}else{
			enrichedCollege = new EnrichedCollege(college, 1);
		}
		
		return new ResponseEntity<EnrichedCollege>(enrichedCollege, HttpStatus.OK);
	}

	/**
	 * Get a College by colCode
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/college/colname/{colName}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<College> getCollegeByColName(@PathVariable String colName) {
		College college = collegeService.findByColName(colName);
		if (college == null) {
			return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<College>(college, HttpStatus.OK);
	}
}
