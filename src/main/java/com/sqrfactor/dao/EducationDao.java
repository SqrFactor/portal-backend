/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Education;

/**
 * @author Angad Gill
 *
 */
public interface EducationDao {

	Education findById(long id);

	void saveEducation(Education education);

	void deleteEducationById(long id);

	List<Education> findAllEducations();
	
	List<Education> findEducationsByUserId(long userId);
}
