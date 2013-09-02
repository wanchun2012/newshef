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

import com.acs.newshef.orm.FAQ;
 
@Repository("faqDAO")

public class FAQDAOImpl 
{
	private static final Logger logger = LoggerFactory.getLogger(FAQDAOImpl.class);
	@Autowired
  private SessionFactory sessionFactory;
 
  public void addQuestion(FAQ faq) throws HibernateException
  {
    sessionFactory.getCurrentSession().save(faq);
  }
    
  public Set<FAQ> loadQuestion() throws HibernateException 
  {
  	Set<FAQ> result = null;
    Query query = sessionFactory.getCurrentSession().createQuery("from FAQ ORDER BY question ASC");
    result = new HashSet(query.list());
    return result;
  }
  
  public void updateFAQ(FAQ question) throws HibernateException 
  {
  	sessionFactory.getCurrentSession().update(question); 	 
  }
  
  public FAQ getQuestionById(String id) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();		  
  	return (FAQ) session.get(FAQ.class, Integer.parseInt(id));
  }
  
  public void remove(String id) throws HibernateException 
  {    	    	
  	Session session = sessionFactory.getCurrentSession();		  
  	session.delete(session.get(FAQ.class, Integer.parseInt(id)));    	
  }
  
  public void exist(String Id)
  {
  	
  }

}
