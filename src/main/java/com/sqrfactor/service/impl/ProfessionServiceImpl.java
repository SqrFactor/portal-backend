/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.ProfessionDao;
import com.sqrfactor.model.Profession;
import com.sqrfactor.service.ProfessionService;

/**
 * @author Angad Gill
 *
 */
@Service("professionService")
@Transactional
public class ProfessionServiceImpl implements ProfessionService {

	@Autowired
	private ProfessionDao professionDao;

	/**
	 * 
	 */
	public ProfessionServiceImpl() {

	}

	@Override
	public Profession findById(long id) {
		return professionDao.findById(id);
	}

	@Override
	public void saveProfession(Profession profession) {
		professionDao.saveProfession(profession);

	}

	@Override
	public void updateProfession(Profession profession) {
		Profession entity = professionDao.findById(profession.getId());

		if (entity != null) {
			entity.setUserId(profession.getUserId());
			entity.setProfessionType(profession.getProfessionType());
			entity.setProfessionRole(profession.getProfessionRole());
			entity.setProfessionCompany(profession.getProfessionCompany());
			entity.setProfessionSalary(profession.getProfessionSalary());
			entity.setProfessionFromDate(profession.getProfessionFromDate());
			entity.setProfessionToDate(profession.getProfessionToDate());
			entity.setProfessionToDateIsCurrent(profession.isProfessionToDateIsCurrent());
		}

	}

	@Override
	public void deleteProfessionById(long id) {
		professionDao.deleteProfessionById(id);
	}

	@Override
	public List<Profession> findAllProfessions() {
		return professionDao.findAllProfessions();
	}

	public List<Profession> findProfessionsByUserId(long userId) {
		return professionDao.findProfessionsByUserId(userId);
	}
}
