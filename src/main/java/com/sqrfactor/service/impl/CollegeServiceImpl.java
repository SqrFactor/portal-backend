/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.CollegeDao;
import com.sqrfactor.model.College;
import com.sqrfactor.service.CollegeService;

/**
 * @author Angad Gill
 *
 */
@Service("collegeService")
@Transactional
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	private CollegeDao collegeDao;
	
	@Override
	public College findByColCode(String colCode) {
		return collegeDao.findByColCode(colCode);
	}

	@Override
	public College findByColName(String colName) {
		return collegeDao.findByColName(colName);
	}

	@Override
	public List<College> findAllColleges() {
		return collegeDao.findAllColleges();
	}
}
