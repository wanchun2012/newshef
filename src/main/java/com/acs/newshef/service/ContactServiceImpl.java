package com.acs.newshef.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acs.newshef.dao.ContactDAOImpl;
import com.acs.newshef.model.ContactDepartmentModel;
import com.acs.newshef.orm.ContactFaculty;
import com.acs.newshef.orm.ContactDepartment;

@Service("contactService")
@Transactional
public class ContactServiceImpl 
{
	@Autowired
  private ContactDAOImpl contactDAO;
	
	public void addFaculty(ContactFaculty faculty) throws HibernateException
	{
		contactDAO.addFaculty(faculty);
  }
    
  public void addDepartment(ContactDepartment department) throws HibernateException
  {
  	contactDAO.addDepartment(department);
  }
  
  public Set<ContactFaculty> loadFaculties() throws HibernateException 
  {
  	return contactDAO.loadFaculties();
  }
  
  public List<ContactDepartmentModel> loadDepartmentCollection(String facultytId) throws HibernateException 
  {
  	List<ContactDepartmentModel> departmentModelCollection = new ArrayList();
  	Set<ContactDepartment> departmentCollection = contactDAO.getFacultyById(facultytId).getDepartmentCollection();
  	Iterator it = departmentCollection.iterator();
  	while (it.hasNext())
  	{
  		ContactDepartment department = (ContactDepartment) it.next();
  		
  		departmentModelCollection.add(new ContactDepartmentModel(  
  				Integer.toString(department.getId()), department.getDepartmentName(), department.getEmail(), department.getPhone()));
  	 
  	}
  	return departmentModelCollection;
  }
  
  public void updateFaculty(ContactFaculty faculty) throws HibernateException 
  {
  	contactDAO.updateFaculty(faculty); 
  }
  
  public void updateDepartment(ContactDepartment department) throws HibernateException 
  {
  	contactDAO.updateDepartment(department);
  }
  
  public ContactFaculty getFacultyById(String id) throws HibernateException
  {  
  	return contactDAO.getFacultyById(id);
  }
  
  public ContactDepartment getDepartmentById(String id) throws HibernateException
  {	  
  	return contactDAO.getDepartmentById(id);
  }
  
  public void removeDepartment(String id) throws HibernateException 
  {    	
  	contactDAO.removeDepartment(id);
  }
  
  public void removeFaculty(String id) throws HibernateException 
  {    		
  	contactDAO.removeFaculty(id);
  }

}
