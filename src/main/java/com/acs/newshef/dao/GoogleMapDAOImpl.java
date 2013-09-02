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

import com.acs.newshef.orm.GoogleMap;

@Repository("mapDAO")

public class GoogleMapDAOImpl 
{
	private static final Logger logger = LoggerFactory.getLogger(GoogleMapDAOImpl.class);
	@Autowired
  private SessionFactory sessionFactory;
 
  public void addMarker(GoogleMap marker) throws HibernateException
  {
    sessionFactory.getCurrentSession().save(marker);
  }
    
  public Set<GoogleMap> loadMarkers() throws HibernateException 
  {
    Set<GoogleMap> result = null;
    Query query = sessionFactory.getCurrentSession().createQuery("from GoogleMap ORDER BY title ASC");
    result = new HashSet(query.list());
    return result;
  }
  
  public void updateMarker(GoogleMap marker) throws HibernateException 
  {
  	sessionFactory.getCurrentSession().update(marker); 	 
  }
  
  public GoogleMap getMarkerById(String id) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();		  
  	return (GoogleMap) session.get(GoogleMap.class, Integer.parseInt(id));
  }
  
  public void remove(String id) throws HibernateException 
  {    		
  	Session session = sessionFactory.getCurrentSession();		  
  	session.delete(session.get(GoogleMap.class, Integer.parseInt(id)));
  }
}
