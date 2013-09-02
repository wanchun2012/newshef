package com.acs.newshef.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

 
@Entity 
public class ContactDepartment 
{
	
	@Id
	@Column 
	@GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
 
  @ManyToOne (fetch=FetchType.EAGER)
  @JoinColumn(name = "faculty_id")  
	private ContactFaculty faculty;
    
	@Column  
  private String departmentName;
	
	@Column  
    private String email;
	
	@Column  
    private String phone;

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	public ContactFaculty getFaculty()
	{
		return faculty;
	}

	public void setFaculty(ContactFaculty faculty)
	{
		this.faculty = faculty;
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
	

}
