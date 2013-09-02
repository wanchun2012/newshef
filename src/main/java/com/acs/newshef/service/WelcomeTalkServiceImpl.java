package com.acs.newshef.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acs.newshef.dao.WelcomeTalkDAOImpl;
import com.acs.newshef.orm.WelcomeTalk;

@Service("welcomeService")
@Transactional
public class WelcomeTalkServiceImpl 
{
	@Autowired
  private WelcomeTalkDAOImpl welcomeDAO;
	
	public void add(WelcomeTalk wt) throws HibernateException
	{
		welcomeDAO.add(wt);
  }
    
  public WelcomeTalk get(String id) throws HibernateException
  {
    return welcomeDAO.get(id);
  }
 
  public void update(WelcomeTalk wt) throws HibernateException
  {
  	welcomeDAO.update(wt);
  }
  
  public void remove(String id) throws HibernateException
  {
    welcomeDAO.remove(id);
  }

  public WelcomeTalk getUpdatedItem() throws HibernateException
  {
  	return welcomeDAO.getUpdatedItem();
  }
    
  public boolean checkTableEmpty() throws HibernateException
  {
  	return welcomeDAO.checkTableEmpty();
  }
	
}
