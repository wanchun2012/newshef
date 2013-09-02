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

import com.acs.newshef.orm.ContactFaculty;
import com.acs.newshef.orm.ContactDepartment;

@Repository("contactDAO")
public class ContactDAOImpl 
{
	private static final Logger logger = LoggerFactory.getLogger(ContactDAOImpl.class);
	@Autowired
  private SessionFactory sessionFactory;
 
  public void addFaculty(ContactFaculty faculty) throws HibernateException
  {
    sessionFactory.getCurrentSession().save(faculty);
  }
    
  public void addDepartment(ContactDepartment department) throws HibernateException
  {
    sessionFactory.getCurrentSession().save(department);
  }
    
  public Set<ContactFaculty> loadFaculties() throws HibernateException 
  {
    Set<ContactFaculty> result = null;
    Query query = sessionFactory.getCurrentSession().createQuery("from ContactFaculty ORDER BY facultyName ASC");
    result = new HashSet(query.list());
    return result;
  }
  
  public void updateFaculty(ContactFaculty faculty) throws HibernateException 
  {
  	sessionFactory.getCurrentSession().update(faculty); 	 
  }
  
  public void updateDepartment(ContactDepartment department) throws HibernateException 
  {
  	sessionFactory.getCurrentSession().update(department); 	 
  }
  
  public ContactFaculty getFacultyById(String id) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();		  
  	return (ContactFaculty) session.get(ContactFaculty.class, Integer.parseInt(id));
  }
  
  public ContactDepartment getDepartmentById(String id) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();		  
  	return (ContactDepartment) session.get(ContactDepartment.class, Integer.parseInt(id));
  }
  
  public void removeFaculty(String id) throws HibernateException 
  {    		
  	Session session = sessionFactory.getCurrentSession();		  
  	session.delete(session.get(ContactFaculty.class, Integer.parseInt(id)));
  }
  
  public void removeDepartment(String id) throws HibernateException 
  {    	
  	Session session = sessionFactory.getCurrentSession();		  
  	session.delete(session.get(ContactDepartment.class, Integer.parseInt(id)));
  }

}
