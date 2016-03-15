package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.College;

/**
 * @author Angad Gill
 *
 */
public interface CollegeDao {

	College findByColCode(String colCode);
	
	College findByColName(String colName);
	
	List<College> findAllColleges();
}
