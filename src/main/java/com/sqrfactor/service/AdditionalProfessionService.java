/**
 * 
 */
package com.sqrfactor.service;

import com.sqrfactor.model.AdditionalProfession;

/**
 * @author Angad Gill
 *
 */
public interface AdditionalProfessionService {

	AdditionalProfession findById(long id);
	
	void saveAdditionalProfession(AdditionalProfession additionalProfession);

	void updateAdditionalProfession(AdditionalProfession additionalProfession);

	void deleteById(long id);
	
	AdditionalProfession findByUserId(long userId);

}