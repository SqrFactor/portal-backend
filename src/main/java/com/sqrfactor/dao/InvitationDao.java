/**
 * 
 */
package com.sqrfactor.dao;

import java.util.List;

import com.sqrfactor.model.Invitation;

/**
 * @author Angad Gill
 *
 */
public interface InvitationDao {

	Invitation findById(long invitationId);
	
	void saveInvitation(Invitation invitation);

	void deleteInvitationById(long invitationId);

	Invitation findByInvitationId(long userId); 
	
	List<Invitation> findByInvitedByUserId(long invitedByUserId);
	
	Invitation findByInvitedByAndTo(long invitedByUserId, long invitedToUserId);
}
