/**
 * 
 */
package com.sqrfactor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sqrfactor.dao.AbstractDao;
import com.sqrfactor.dao.CollegeDao;
import com.sqrfactor.model.College;

/**
 * @author Angad
 *
 */
@Repository("collegeDao")
public class CollegeDaoImpl extends AbstractDao<String, College> implements CollegeDao {

	public College findByColCode(String colCode) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("colCode", colCode));
		return (College) criteria.uniqueResult();
	}

	public College findByColName(String colName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("colName", colName));
		return (College) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<College> findAllColleges() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder( Order.asc("colName") );
		return (List<College>) criteria.list();
	}
}
