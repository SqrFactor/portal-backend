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
import com.sqrfactor.dao.ConnectionDao;
import com.sqrfactor.model.Connection;

/**
 * @author Angad Gill
 *
 */
@Repository("connectionDao")
public class ConnectionDaoImpl extends AbstractDao<Long, Connection> implements ConnectionDao {

	@Override
	public Connection findById(long connectionId) {
		return getByKey(connectionId);
	}

	@Override
	public void saveConnection(Connection connection) {
		persist(connection);
	}

	@Override
	public void deleteConnectionById(long connectionId) {
		Query query = getSession().createSQLQuery("delete from connection_details where connectionId = :connectionId");
		query.setLong("connectionId", connectionId);
		query.executeUpdate();
	}

	@Override
	public List<Connection> findAllConnections() {
		Criteria criteria = createEntityCriteria();
		return (List<Connection>) criteria.list();
	}

	@Override
	public List<Connection> findConnectionsBySourceId(long sourceId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("sourceId", sourceId));
		return  (List<Connection>)criteria.list();
	}
	
	@Override
	public List<Connection> findConnectionsByDestinationId(long destinationId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("destinationId", destinationId));
		return  (List<Connection>)criteria.list();
	}
	
	@Override
	public Connection findConBySrcAndDestId(long sourceId, long destinationId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("sourceId", sourceId));
		criteria.add(Restrictions.eq("destinationId", destinationId));
		return (Connection) criteria.uniqueResult();
		
	}
}
