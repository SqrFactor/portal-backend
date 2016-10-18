/**
 * 
 */
package com.sqrfactor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.InvitationDao;
import com.sqrfactor.model.Connection;
import com.sqrfactor.model.Invitation;

/**
 * @author Angad Gill
 *
 */
@Repository("invitationDao")
public class InvitationDaoImpl extends AbstractDao<Long, Invitation> implements InvitationDao {

	public Invitation findById(long invitationId) {
		return getByKey(invitationId);
	}

	public void saveInvitation(Invitation invitation) {
		persist(invitation);
	}

	public void deleteInvitationById(long invitationId) {
		Query query = getSession().createSQLQuery("delete from invitation_details where invitationId = :invitationId");
		query.setLong("invitationId", invitationId);
		query.executeUpdate();
	}
	
	public Invitation findByInvitationId(long invitationUserId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("invitationId", invitationUserId));
		return (Invitation) criteria.uniqueResult();
	}
	
	public List<Invitation> findByInvitedByUserId(long invitedByUserId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("invitedByUserId", invitedByUserId));
		return criteria.list();
	}
	
	public Invitation findByInvitedByAndTo(long invitedByUserId, long invitedToUserId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("invitedByUserId", invitedByUserId));
		criteria.add(Restrictions.eq("invitedToUserId", invitedToUserId));
		return (Invitation) criteria.uniqueResult();
	}
	
}
