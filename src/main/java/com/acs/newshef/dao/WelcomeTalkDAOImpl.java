package com.acs.newshef.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acs.newshef.orm.WelcomeTalk;

@Repository("welcomeDAO")
public class WelcomeTalkDAOImpl 
{
	@Autowired
  private SessionFactory sessionFactory;
   
  public void add(WelcomeTalk wt) throws HibernateException
  {
    Session session = sessionFactory.getCurrentSession();
    session.save(wt);
  }
    
  public void update(WelcomeTalk wt) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();
    session.update(wt);
  }
    
  public WelcomeTalk get(String id) throws HibernateException
  {
    Session session = sessionFactory.getCurrentSession();
    return (WelcomeTalk)session.get(WelcomeTalk.class, Integer.parseInt(id));
  }
 
  public void remove(String id) throws HibernateException
  {
    Session session = sessionFactory.getCurrentSession();
    session.delete((WelcomeTalk)session.get(WelcomeTalk.class, Integer.parseInt(id)));
  }

  public WelcomeTalk getUpdatedItem() throws HibernateException
  {
    Session session = sessionFactory.getCurrentSession();
    List<WelcomeTalk> welcomes = null;
  
    welcomes = (List<WelcomeTalk>)session.createQuery("from WelcomeTalk").list();
 
    return welcomes.get(0);
	}
    
  public boolean checkTableEmpty() throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();
  	return session.createQuery("select 1 from WelcomeTalk").setFetchSize(1).list().isEmpty();
  }
  
}
