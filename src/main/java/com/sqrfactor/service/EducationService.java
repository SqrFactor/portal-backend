/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Education;

/**
 * @author Angad Gill
 *
 */
public interface EducationService {

	Education findById(long id);
	
	void saveEducation(Education education);

	void updateEducation(Education education);

	void deleteEducationById(long id);

	List<Education> findAllEducations();
	
	List<Education> findEducationsByUserId(long userId);

}
