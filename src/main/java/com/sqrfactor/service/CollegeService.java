/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.College;

/**
 * @author Angad
 *
 */
public interface CollegeService {
	
	College findByColCode(String colCode);
	
	College findByColName(String colName);
	
	List<College> findAllColleges();

}
