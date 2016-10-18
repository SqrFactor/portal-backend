/**
 * 
 */
package com.sqrfactor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqrfactor.dao.InvitationDao;
import com.sqrfactor.model.Invitation;
import com.sqrfactor.service.InvitationService;

/**
 * @author Angad Gill
 *
 */
@Service("invitationService")
@Transactional
public class InvitationServiceImpl implements InvitationService{

	@Autowired
	private InvitationDao invitationDao;
	
	public Invitation findById(long invitationId) {
		return invitationDao.findById(invitationId);
	}
	
	public Invitation findByInvitationId(long invitationId) {
		return invitationDao.findByInvitationId(invitationId);
	}
	
	public List<Invitation> findByInvitedByUserId(long invitedByUserId) {
		return invitationDao.findByInvitedByUserId(invitedByUserId);
	}
	
	public Invitation findByInvitedByAndTo(long invitedByUserId, long invitedToUserId){
		return invitationDao.findByInvitedByAndTo(invitedByUserId, invitedToUserId);
	}

	public void saveInvitation(Invitation invitation) {
		invitationDao.saveInvitation(invitation);
	}

	public void updateInvitation(Invitation invitation) {
		Invitation entity = invitationDao.findById(invitation.getInvitationId());
		
		if (entity != null) {
			entity.setInvitedByUserId(invitation.getInvitedByUserId());
			entity.setInvitedToUserId(invitation.getInvitedToUserId());
		}
	}

	/**
	 * Delete user by id
	 */
	public void deleteById(long invitationId) {
		invitationDao.deleteInvitationById(invitationId);
	}

}
