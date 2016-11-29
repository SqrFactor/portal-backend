/**
 * 
 */
package com.sqrfactor.dao.impl.competition;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.competition.CompetitionPartnerDao;
import com.sqrfactor.model.competition.CompetitionPartner;

/**
 * @author Angad Gill
 *
 */
@Repository("competitionPartnerDao")
public class CompetitionPartnerDaoImpl extends AbstractDao<Long, CompetitionPartner> implements CompetitionPartnerDao{

	public CompetitionPartner findById(long id) {
		return getByKey(id);
	}
	
	public CompetitionPartner findByCompetitionPartnerId(long compPartnerId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("compPartnerId", compPartnerId));
		return (CompetitionPartner) criteria.uniqueResult();
	}
	
	public void saveCompetitionPartner(CompetitionPartner competitionPartner) {
		persist(competitionPartner);
	}

	public void deleteCompetitionPartnerById(long compPartnerId) {
		Query query = getSession().createSQLQuery("delete from competition_partners where compPartnerId = :compPartnerId");
		query.setLong("compPartnerId", compPartnerId);
		query.executeUpdate();
	}

}
