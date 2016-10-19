/**
 * 
 */
package com.sqrfactor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.model.AdditionalProfession;
import com.sqrfactor.service.AdditionalProfessionService;

/**
 * @author Angad Gill
 *
 */
@RestController
public class AdditionalProfessionController {

	@Autowired
	private AdditionalProfessionService additionalProfessionService;

	public AdditionalProfessionController() {

	}

	/**
	 * Get Profession by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/additionalprofession/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<AdditionalProfession> getAdditionalProfessionById(@PathVariable int id) {
		AdditionalProfession additionalProfession = additionalProfessionService.findById(id);
		if (additionalProfession == null) {
			return new ResponseEntity<AdditionalProfession>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AdditionalProfession>(additionalProfession, HttpStatus.OK);
	}
	
	/**
	 * Get Profession by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/additionalprofession/userid/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<AdditionalProfession> getAdditionalProfessionByUserId(@PathVariable int userId) {
		AdditionalProfession additionalProfession = additionalProfessionService.findByUserId(userId);
		if (additionalProfession == null) {
			return new ResponseEntity<AdditionalProfession>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AdditionalProfession>(additionalProfession, HttpStatus.OK);
	}

	/**
	 * Create Additional Profession
	 * 
	 * @param additionalProfession
	 */
	@RequestMapping(value = "/additionalprofession/", method = RequestMethod.POST)
	public ResponseEntity<AdditionalProfession> createAdditionalProfession(@RequestBody AdditionalProfession additionalProfession) {

		if(additionalProfessionService.findByUserId(additionalProfession.getUserId()) != null){
			return new ResponseEntity<AdditionalProfession>(additionalProfession, HttpStatus.CONFLICT);
		}
		
		additionalProfessionService.saveAdditionalProfession(additionalProfession);

		return new ResponseEntity<AdditionalProfession>(additionalProfession, HttpStatus.CREATED);
	}

	/**
	 * Update Profession
	 * 
	 * @param id
	 * @param profession
	 * @return
	 */
	@RequestMapping(value = "/additionalprofession/{id}", method = RequestMethod.PUT)
	public ResponseEntity<AdditionalProfession> updateAdditionalProfession(@PathVariable("id") int id, @RequestBody AdditionalProfession additionalProfession) {
		AdditionalProfession currentAdditionalProfession = additionalProfessionService.findById(id);

		if (currentAdditionalProfession == null) {
			return new ResponseEntity<AdditionalProfession>(HttpStatus.NOT_FOUND);
		}
		
		additionalProfession.setId(id);
		additionalProfessionService.updateAdditionalProfession(additionalProfession);
		return new ResponseEntity<AdditionalProfession>(additionalProfession, HttpStatus.OK);
	}

	/**
	 * Delete Profession by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/additionalprofession/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AdditionalProfession> deleteAdditionalProfession(@PathVariable("id") int id) {
		AdditionalProfession additionalProfession = additionalProfessionService.findById(id);
		if (additionalProfession == null) {
			return new ResponseEntity<AdditionalProfession>(HttpStatus.NOT_FOUND);
		}

		additionalProfessionService.deleteById(id);

		return new ResponseEntity<AdditionalProfession>(HttpStatus.NO_CONTENT);
	}
}
