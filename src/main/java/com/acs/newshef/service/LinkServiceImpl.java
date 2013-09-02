package com.acs.newshef.service;

import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acs.newshef.dao.LinkDAOImpl;
import com.acs.newshef.orm.Link;

@Service("linkService")
@Transactional
public class LinkServiceImpl 
{
	@Autowired
  private LinkDAOImpl linkDAO;
   
  public void addLink(Link link) throws HibernateException
  {
    linkDAO.addLink(link);
  }
  
  public Set<Link> loadLinks() throws HibernateException  
  {
    return linkDAO.loadLinks();
  }
  
  public void updateLink(Link link) throws HibernateException 
  {
    linkDAO.updateLink(link);
  }
  
  public Link getLinkById(String id) throws HibernateException 
  {
    return linkDAO.getLinkById(id);
  }
  
  public void remove(String id) throws HibernateException
  {
  	linkDAO.remove(id);
  }
}
