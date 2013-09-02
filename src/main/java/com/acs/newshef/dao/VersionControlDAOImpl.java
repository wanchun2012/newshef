package com.acs.newshef.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acs.newshef.orm.VersionControl;

@Repository("versionDAO")
public class VersionControlDAOImpl
{
	@Autowired
  private SessionFactory sessionFactory;
   
  public void add(VersionControl v) throws HibernateException
  {
    Session session = sessionFactory.getCurrentSession();
    session.save(v);
  }
    
  public void update(VersionControl v) throws HibernateException
  {
  	Session session = sessionFactory.getCurrentSession();
    session.update(v);
  }
  
  public VersionControl getVersion() throws HibernateException
  {
    Session session = sessionFactory.getCurrentSession();
    List<VersionControl> v = null;
 
    v = (List<VersionControl>)session.createQuery("from VersionControl").list();
   
    return v.get(0);
	}
}
