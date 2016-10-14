/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;
import com.sqrfactor.model.Profession;

/**
 * @author Angad Gill
 *
 */
public interface ProfessionDao {

	Profession findById(long id);

	void saveProfession(Profession profession);

	void deleteProfessionById(long id);

	List<Profession> findAllProfessions();
	
	List<Profession> findProfessionsByUserId(long userId);
}

