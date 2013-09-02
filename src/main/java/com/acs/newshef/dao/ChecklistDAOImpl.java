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

import com.acs.newshef.orm.ChecklistActivity;
import com.acs.newshef.orm.ChecklistGroup;

@Repository("checklistDAO")
public class ChecklistDAOImpl 
{
	private static final Logger logger = LoggerFactory.getLogger(ChecklistDAOImpl.class);
	@Autowired
  private SessionFactory sessionFactory;
 
  public void addChecklistGroup(ChecklistGroup group) throws HibernateException
  {
    sessionFactory.getCurrentSession().save(group);
  }
    
  public void addChecklistActivity(ChecklistActivity activity) throws HibernateException
  {
    sessionFactory.getCurrentSession().save(activity);
  }
    
  public Set<ChecklistGroup> loadGroups() throws HibernateException 
  {
    Set<ChecklistGroup> result = null;
    Query query = sessionFactory.getCurrentSession().createQuery("from ChecklistGroup ORDER BY id ASC");
    result = new HashSet(query.list());
    return result;
  }
  
  public void updateGroups(ChecklistGroup group) throws HibernateException 
  {
  	sessionFactory.getCurrentSession().update(group); 	 
  }
  
  public void updateActivities(ChecklistActivity activity) throws HibernateException 
  {
  	sessionFactory.getCurrentSession().update(activity); 	 
  }
  
  public ChecklistGroup getGroupById(String id) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();		  
  	return (ChecklistGroup) session.get(ChecklistGroup.class, Integer.parseInt(id));
  }
  
  public ChecklistActivity getActivityById(String id) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();		  
  	return (ChecklistActivity) session.get(ChecklistActivity.class, Integer.parseInt(id));
  }
  
  public void removeGroup(String id) throws HibernateException 
  {    		
  	Session session = sessionFactory.getCurrentSession();		  
  	session.delete(session.get(ChecklistGroup.class, Integer.parseInt(id)));
  }
  
  public void removeActivity(String id) throws HibernateException 
  {    		
  	Session session = sessionFactory.getCurrentSession();		  
  	session.delete(session.get(ChecklistActivity.class, Integer.parseInt(id)));
  }

}
