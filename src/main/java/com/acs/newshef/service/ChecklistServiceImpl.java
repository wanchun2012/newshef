package com.acs.newshef.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acs.newshef.dao.ChecklistDAOImpl;
import com.acs.newshef.dao.LinkDAOImpl;
import com.acs.newshef.model.ChecklistActivityModel;
import com.acs.newshef.orm.ChecklistActivity;
import com.acs.newshef.orm.ChecklistGroup;
import com.acs.newshef.orm.Link;

@Service("checklistService")
@Transactional
public class ChecklistServiceImpl 
{
	@Autowired
  private ChecklistDAOImpl checklistDAO;
	
	public void addChecklistGroup(ChecklistGroup group) throws HibernateException
	{
    checklistDAO.addChecklistGroup(group);
  }
    
  public void addChecklistActivity(ChecklistActivity activity) throws HibernateException
  {
  	checklistDAO.addChecklistActivity(activity);
  }
  
  public Set<ChecklistGroup> loadGroups() throws HibernateException
  {
  	return checklistDAO.loadGroups();
  }
  
  public List<ChecklistActivityModel> loadActivityCollection(String groupId) throws HibernateException 
  {
  	List<ChecklistActivityModel> activityModelCollection = new ArrayList();
  	Set<ChecklistActivity> activityCollection = checklistDAO.getGroupById(groupId).getActivityCollection();
  	Iterator it = activityCollection.iterator();
  	while (it.hasNext())
  	{
  		ChecklistActivity activity = (ChecklistActivity) it.next();
  		
  		activityModelCollection.add(new ChecklistActivityModel(
  				Integer.toString(activity.getId()), activity.getActivityName(), 
  				activity.getDetails(), activity.getResponsiblePerson()));
  	 
  	}
  	return activityModelCollection;
  }
    
  public void updateGroups(ChecklistGroup group) throws HibernateException 
  {
  	checklistDAO.updateGroups(group); 
  }
  
  public void updateActivities(ChecklistActivity activity) throws HibernateException 
  {
  	checklistDAO.updateActivities(activity);
  }
  
  public ChecklistGroup getGroupById(String id) throws HibernateException
  {	  
  	return checklistDAO.getGroupById(id);
  }
  
  public ChecklistActivity getActivityById(String id) throws HibernateException
  {
  	return checklistDAO.getActivityById(id);
  }
  
  public void removeGroup(String id) throws HibernateException 
  {    		
  	checklistDAO.removeGroup(id);	
  }
  
  public void removeActivity(String id) throws HibernateException 
  {    	
  	checklistDAO.removeActivity(id);
  }

}
