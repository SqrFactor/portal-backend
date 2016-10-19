/**
 * 
 */
package com.sqrfactor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.AdditionalProfessionDao;
import com.sqrfactor.model.AdditionalProfession;
import com.sqrfactor.service.AdditionalProfessionService;

/**
 * @author Angad Gill
 *
 */
@Service("additionalProfessionService")
@Transactional
public class AdditionalProfessionServiceImpl implements AdditionalProfessionService {

	@Autowired
	private AdditionalProfessionDao additionalProfessionDao;
	
	public AdditionalProfession findById(long id) {
		return additionalProfessionDao.findById(id);
	}
	
	public AdditionalProfession findByUserId(long userId) {
		return additionalProfessionDao.findByUserId(userId);
	}

	public void saveAdditionalProfession(AdditionalProfession additionalProfession) {
		additionalProfessionDao.saveAdditionalProfession(additionalProfession);
	}

	public void updateAdditionalProfession(AdditionalProfession additionalProfession) {
		AdditionalProfession entity = additionalProfessionDao.findById(additionalProfession.getId());
		
		if (entity != null) {
			entity.setUserId(additionalProfession.getUserId());
			entity.setCoaNumber(additionalProfession.getCoaNumber());
			entity.setIiaNumber(additionalProfession.getIiaNumber());
		}
	}

	/**
	 * Delete profesison by id
	 */
	public void deleteById(long id) {
		additionalProfessionDao.deleteAdditionalProfessionById(id);
	}

}
