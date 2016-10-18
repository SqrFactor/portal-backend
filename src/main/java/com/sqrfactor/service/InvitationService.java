/**
 * 
 */
package com.sqrfactor.service;

import java.util.List;

import com.sqrfactor.model.Invitation;

/**
 * @author Angad Gill
 *
 */
public interface InvitationService {

	Invitation findById(long invitationId);
	
	void saveInvitation(Invitation invitation);

	void updateInvitation(Invitation invitation);

	void deleteById(long invitationId);
	
	Invitation findByInvitationId(long invitationId);
	
	List<Invitation> findByInvitedByUserId(long invitedByUserId);
	
	Invitation findByInvitedByAndTo(long invitedByUserId, long invitedToUserId);

}
