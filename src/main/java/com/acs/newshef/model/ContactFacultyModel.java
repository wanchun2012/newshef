package com.acs.newshef.model;

import java.util.List;


public class ContactFacultyModel 
{
	
	private String id;
  private String facultyName;
	private List<ContactDepartmentModel> departmentCollection;
  private String edited;
  private String removed;
  private String size;
  
  public ContactFacultyModel() 
  {
  	
  }
  
  public ContactFacultyModel(String facultyName) 
  {
  	this.facultyName = facultyName;
  	this.size = Integer.toString(0);
  	 
  }
  
  public ContactFacultyModel(String id, String facultyName, List<ContactDepartmentModel> departmentCollection) 
  {
  	this.id = id;
  	this.facultyName = facultyName;
  	this.departmentCollection = departmentCollection;  
  	this.size = Integer.toString(departmentCollection.size());
  }
    
	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	 	 
	public String getFacultyName()
	{
		return facultyName;
	}

	public void setFacultyName(String facultyName)
	{
		this.facultyName = facultyName;
	}

	public List<ContactDepartmentModel> getDepartmentCollection()
	{
		return departmentCollection;
	}

	public void setDepartmentCollection(
			List<ContactDepartmentModel> departmentCollection)
	{
		this.departmentCollection = departmentCollection;
	}

	public String getEdited() 
	{
		return edited;
	}
	
	public void setEdited(String edited) 
	{
		this.edited = edited;
	}
	
	public String getRemoved() 
	{
		return removed;
	}
	public void setRemoved(String removed) 
	{
		this.removed = removed;
	}
	
	public String getSize() 
	{
		return size;
	}
	
	public void setSize(String size) 
	{
		this.size = size;
	}
    
}
