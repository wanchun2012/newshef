package com.acs.newshef.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acs.newshef.dao.VersionControlDAOImpl;
import com.acs.newshef.orm.VersionControl;

@Service("versionService")
@Transactional
public class VersionControlServiceImpl
{
	@Autowired
  private VersionControlDAOImpl versionDAO;
	
	public void add(VersionControl v) throws HibernateException
	{
		versionDAO.add(v);;
  }
 
  public void updateVersion() throws HibernateException
  {
  	VersionControl v = getVersion();
  	v.setVersion(Integer.toString(Integer.parseInt(v.getVersion())+1));
  	versionDAO.update(v);
  }
  
  public void updateVersionChecklist() throws HibernateException
  {
  	VersionControl v = getVersion();
  	v.setVersionChecklist(Integer.toString(Integer.parseInt(v.getVersionChecklist())+1));
  	versionDAO.update(v);
  }
  
  public void updateVersionContact() throws HibernateException
  {
  	VersionControl v = getVersion();
  	v.setVersionContact(Integer.toString(Integer.parseInt(v.getVersionContact())+1));
  	versionDAO.update(v);
  }
  
  public void updateVersionFAQ() throws HibernateException
  {
  	VersionControl v = getVersion();
  	v.setVersionFAQ(Integer.toString(Integer.parseInt(v.getVersionFAQ())+1));
  	versionDAO.update(v);
  }
  
  public void updateVersionGoogleMap() throws HibernateException
  {
  	VersionControl v = getVersion();
  	v.setVersionGoogleMap(Integer.toString(Integer.parseInt(v.getVersionGoogleMap())+1));
  	versionDAO.update(v);
  }
  
  public void updateVersionLink() throws HibernateException
  {
  	VersionControl v = getVersion();
  	v.setVersionLink(Integer.toString(Integer.parseInt(v.getVersionLink())+1));
  	versionDAO.update(v);
  }
  
  public void updateVersionUEB() throws HibernateException
  {
  	VersionControl v = getVersion();
  
  	v.setVersionUEB(Integer.toString(Integer.parseInt(v.getVersionUEB())+1));
  	versionDAO.update(v);
  }
  
  public void updateVersionWelcomeTalk() throws HibernateException
  {
  	VersionControl v = getVersion();
  	v.setVersionWelcomeTalk(Integer.toString(Integer.parseInt(v.getVersionWelcomeTalk())+1));
  	versionDAO.update(v);
  }
   
  public VersionControl getVersion() throws HibernateException
  {
  	return versionDAO.getVersion();
  }
 
  
  
}
