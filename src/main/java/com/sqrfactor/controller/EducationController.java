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

import com.sqrfactor.model.Education;
import com.sqrfactor.service.EducationService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class EducationController {

	@Autowired
	private EducationService educationService;

	public EducationController() {

	}

	/**
	 * Get all the educations
	 * 
	 * @return
	 */
	@RequestMapping(value = "/education/", method = RequestMethod.GET)
	public ResponseEntity<List<Education>> getAllEducations() {
		List<Education> educations = educationService.findAllEducations();
		if (educations.isEmpty()) {
			return new ResponseEntity<List<Education>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Education>>(educations, HttpStatus.OK);
	}

	/**
	 * Get Education by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/education/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Education> getEducationById(@PathVariable int id) {
		Education education = educationService.findById(id);
		if (education == null) {
			return new ResponseEntity<Education>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Education>(education, HttpStatus.OK);
	}

	/**
	 * Create Education
	 * 
	 * @param education
	 */
	@RequestMapping(value = "/education/", method = RequestMethod.POST)
	public ResponseEntity<Education> createEducation(@RequestBody Education education) {

		educationService.saveEducation(education);

		return new ResponseEntity<Education>(education, HttpStatus.CREATED);
	}

	/**
	 * Update Education
	 * 
	 * @param id
	 * @param education
	 * @return
	 */
	@RequestMapping(value = "/education/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Education> updateEducation(@PathVariable("id") int id, @RequestBody Education education) {
		Education currentEducation = educationService.findById(id);

		if (currentEducation == null) {
			return new ResponseEntity<Education>(HttpStatus.NOT_FOUND);
		}
		education.setId(id);
		educationService.updateEducation(education);
		return new ResponseEntity<Education>(education, HttpStatus.OK);
	}

	/**
	 * Delete Education by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/education/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Education> deleteConnection(@PathVariable("id") int id) {
		Education education = educationService.findById(id);
		if (education == null) {
			return new ResponseEntity<Education>(HttpStatus.NOT_FOUND);
		}

		educationService.deleteEducationById(id);
		return new ResponseEntity<Education>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Get all connections by sourceId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/education/userid/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<Education>> getEducationsByUserId(@PathVariable("userId") long userId) {
		List<Education> educations = educationService.findEducationsByUserId(userId);
		if (educations.isEmpty()) {
			return new ResponseEntity<List<Education>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Education>>(educations, HttpStatus.OK);
	}

}
