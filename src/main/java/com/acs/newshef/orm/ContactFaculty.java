package com.acs.newshef.orm;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity 
public class ContactFaculty 
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column  
  private String facultyName;
  
	@OneToMany(mappedBy="faculty", cascade=CascadeType.ALL,fetch=FetchType.LAZY)  
  private Set<ContactDepartment> departmentCollection;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
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

	public Set<ContactDepartment> getDepartmentCollection()
	{
		return departmentCollection;
	}

	public void setDepartmentCollection(
			Set<ContactDepartment> departmentCollection)
	{
		this.departmentCollection = departmentCollection;
	}
	
}
