/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.EducationDao;
import com.sqrfactor.model.Education;
import com.sqrfactor.service.EducationService;

/**
 * @author Angad Gill
 *
 */
@Service("educationService")
@Transactional
public class EducationServiceImpl implements EducationService {

	@Autowired
	private EducationDao educationDao;

	/**
	 * 
	 */
	public EducationServiceImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sqrfactor.service.EducationService#findById(long)
	 */
	@Override
	public Education findById(long id) {
		return educationDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sqrfactor.service.EducationService#saveEducation(com.sqrfactor.model.
	 * Education)
	 */
	@Override
	public void saveEducation(Education education) {
		educationDao.saveEducation(education);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sqrfactor.service.EducationService#updateEducation(com.sqrfactor.
	 * model.Education)
	 */
	@Override
	public void updateEducation(Education education) {
		Education entity = educationDao.findById(education.getId());

		if (entity != null) {
			entity.setUserId(education.getUserId());
			entity.setColCode(education.getColCode());
			entity.setEducationFromYear(education.getEducationFromYear());
			entity.setEducationName(education.getEducationName());
			entity.setEducationToYear(education.getEducationToYear());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sqrfactor.service.EducationService#deleteAllEducationByUserId(long)
	 */
	@Override
	public void deleteEducationById(long id) {
		educationDao.deleteEducationById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sqrfactor.service.EducationService#findAllEducations()
	 */
	@Override
	public List<Education> findAllEducations() {
		return educationDao.findAllEducations();
	}

	public List<Education> findEducationsByUserId(long userId) {
		return educationDao.findEducationsByUserId(userId);
	}

}
