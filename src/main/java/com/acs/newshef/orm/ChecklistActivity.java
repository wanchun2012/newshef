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
public class ChecklistActivity 
{
	
	@Id
	@Column 
	@GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
 
  @ManyToOne (fetch=FetchType.LAZY)
  @JoinColumn(name = "group_id")  
	private ChecklistGroup group;
    
  @Column  
  private String activityName;
	
	@Column(columnDefinition = "TEXT")
  private String details;
	
	@Column  
  private String responsiblePerson;

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public ChecklistGroup getGroup() 
	{
		return group;
	}

	public void setGroup(ChecklistGroup group) 
	{
		this.group = group;
	}

	public String getActivityName() 
	{
		return activityName;
	}

	public void setActivityName(String activityName) 
	{
		this.activityName = activityName;
	}

	public String getDetails() 
	{
		return details;
	}

	public void setDetails(String details) 
	{
		this.details = details;
	}

	public String getResponsiblePerson() 
	{
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) 
	{
		this.responsiblePerson = responsiblePerson;
	}

}
