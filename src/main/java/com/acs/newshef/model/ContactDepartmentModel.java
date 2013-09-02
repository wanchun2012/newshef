package com.acs.newshef.model;


public class ContactDepartmentModel 
{
  private String id; 
  private String departmentName;
  private String email;
  private String phone;
  private String edited;
  private String removed;
  
  public ContactDepartmentModel() 
  {
  	
  }
  
  public ContactDepartmentModel(String departmentName, String email, String phone) 
  {
  	this.departmentName = departmentName;
  	this.email = email;
  	this.phone = phone;
  }
  
  public ContactDepartmentModel(String id, String departmentName, String email, String phone) 
  {
  	this.id = id;
  	this.departmentName = departmentName;
  	this.email = email;
  	this.phone = phone;
  }
  
  public ContactDepartmentModel(String id, String departmentName, String email, String phone,
  		String edited, String removed) 
  {
  	this.id = id;
  	this.departmentName = departmentName;
  	this.email = email;
  	this.phone = phone;
  	this.edited = edited;
  	this.removed = removed;
  }

	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	 
	public String getDepartmentName()
	{
		return departmentName;
	}

	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getPhone() 
	{
		return phone;
	}
	
	public void setPhone(String phone) 
	{
		this.phone = phone;
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
    
    
}
