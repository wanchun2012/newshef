package com.acs.newshef.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
 
import com.acs.newshef.orm.UEBPosition;
import com.acs.newshef.orm.UEBSubPosition;

@Repository("uebDAO")
public class UEBDAOImpl 
{
	
	private static final Logger logger = LoggerFactory.getLogger(UEBDAOImpl.class);
	@Autowired
  private SessionFactory sessionFactory;
 
  public void addPosition(UEBPosition position) throws HibernateException
  {
    sessionFactory.getCurrentSession().save(position);
  }
  
  public void addSubPosition(UEBSubPosition subposition) throws HibernateException
  {
    sessionFactory.getCurrentSession().save(subposition);
  }
  
  public Set<UEBPosition> loadPositions() throws HibernateException 
  {
  	Set<UEBPosition> result = null;
  	Query query = sessionFactory.getCurrentSession().createQuery("from UEBPosition ORDER BY positionName ASC");
  	result = new HashSet(query.list());
  	return result;
  }
  
  public void updatePosition(UEBPosition position) throws HibernateException 
  {
  	sessionFactory.getCurrentSession().update(position); 	 
  }
  
  public void updateSubPosition(UEBSubPosition subposition) throws HibernateException 
  {
  	sessionFactory.getCurrentSession().update(subposition); 	 
  }
  
  public UEBPosition getPositionById(String id) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();		  
  	return (UEBPosition) session.get(UEBPosition.class, Integer.parseInt(id));
  }
  
  public UEBSubPosition getSubPositionById(String id) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();		  
  	return (UEBSubPosition) session.get(UEBSubPosition.class, Integer.parseInt(id));
  }
  
  public void removePosition(String id) throws HibernateException 
  {    		
  	Session session = sessionFactory.getCurrentSession();		  
  	session.delete(session.get(UEBPosition.class, Integer.parseInt(id)));
  }
  
  public void removeSubPosition(String id) throws HibernateException 
  {    	
  	Session session = sessionFactory.getCurrentSession();		  
  	session.delete(session.get(UEBSubPosition.class, Integer.parseInt(id)));
  }
}
