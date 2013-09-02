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

import com.acs.newshef.orm.Link;
 
@Repository("linkDAO")

public class LinkDAOImpl 
{
	private static final Logger logger = LoggerFactory.getLogger(LinkDAOImpl.class);
	@Autowired
  private SessionFactory sessionFactory;
 
  public void addLink(Link link) throws HibernateException
  {
    sessionFactory.getCurrentSession().save(link);
  }
    
  public Set<Link> loadLinks() throws HibernateException 
  {
    Set<Link> result = null;
    Query query = sessionFactory.getCurrentSession().createQuery("from Link ORDER BY description ASC");
    result = new HashSet(query.list());
    return result;
  }
  
  public void updateLink(Link link) throws HibernateException 
  {
  	sessionFactory.getCurrentSession().update(link); 	 
  }
  
  public Link getLinkById(String id) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();		  
  	return (Link) session.get(Link.class, Integer.parseInt(id));
  }
  
  public void remove(String id) throws HibernateException 
  {    	
  	Session session = sessionFactory.getCurrentSession();		  
  	session.delete(session.get(Link.class, Integer.parseInt(id)));
  }

}
