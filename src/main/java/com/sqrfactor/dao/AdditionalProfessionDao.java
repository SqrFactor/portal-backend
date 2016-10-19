/**
 * 
 */
package com.sqrfactor.dao;

import com.sqrfactor.model.AdditionalProfession;

/**
 * @author Angad Gill
 *
 */
public interface AdditionalProfessionDao {

	AdditionalProfession findById(long id);

	void saveAdditionalProfession(AdditionalProfession additionalProfession);

	void deleteAdditionalProfessionById(long id);

	AdditionalProfession findByUserId(long userId);

}
