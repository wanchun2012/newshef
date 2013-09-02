package com.acs.newshef.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.acs.newshef.dao.UEBDAOImpl;
import com.acs.newshef.model.UEBSubModel;
import com.acs.newshef.orm.UEBPosition;
import com.acs.newshef.orm.UEBSubPosition;

@Service("uebService")
@Transactional
public class UEBServiceImpl 
{
	
	@Autowired
  private UEBDAOImpl uebDAO;
	
	public void addPosition(UEBPosition position) throws HibernateException
	{
		uebDAO.addPosition(position);;
  }
    
	public void addSubPosition(UEBSubPosition subposition) throws HibernateException
	{
		uebDAO.addSubPosition(subposition);;
  }
    
  public Set<UEBPosition> loadPositions() throws HibernateException 
  {
  	return uebDAO.loadPositions();
  }
  
  public List<UEBSubModel> loadSubPositionCollection(String id) throws HibernateException 
  {
  	List<UEBSubModel> subpositionModelCollection = new ArrayList();
  	Set<UEBSubPosition> subpositionCollection = uebDAO.getPositionById(id).getSubpostionCollection();
  	Iterator it = subpositionCollection.iterator();
  	while (it.hasNext())
  	{
  		UEBSubPosition subposition = (UEBSubPosition) it.next();
  		
  		subpositionModelCollection.add(new UEBSubModel(  
  		Integer.toString(subposition.getId()), subposition.getSubpositionName(), subposition.getUebDescription(), 
  		subposition.getUebName()));
  	 
  	}
  	return subpositionModelCollection;
  }
  
  public void updatePosition(UEBPosition position) throws HibernateException 
  {
  	uebDAO.updatePosition(position); 
  }
  
  public void updateSubPosition(UEBSubPosition subposition) throws HibernateException 
  {
  	uebDAO.updateSubPosition(subposition); 
  }
  
  public UEBPosition getPositionById(String id) throws HibernateException
  { 	  
  	return uebDAO.getPositionById(id);
  }
  
  public UEBSubPosition getSubPositionById(String id) throws HibernateException
  {
  	return uebDAO.getSubPositionById(id);
  }
  
  public void removePosition(String id) throws HibernateException
  {    		
  	uebDAO.removePosition(id);
  }
  
  public void removeSubPosition(String id) throws HibernateException
  {    	
  	uebDAO.removeSubPosition(id);
  }

}
