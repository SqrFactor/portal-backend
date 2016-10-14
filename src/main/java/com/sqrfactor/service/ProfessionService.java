/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Profession;

/**
 * @author Angad Gill
 *
 */
public interface ProfessionService {

	Profession findById(long id);
	
	void saveProfession(Profession profession);

	void updateProfession(Profession profession);

	void deleteProfessionById(long id);

	List<Profession> findAllProfessions();
	
	List<Profession> findProfessionsByUserId(long userId);

}

