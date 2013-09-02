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
public class ChecklistGroup 
{
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column  
  private String groupName;
  
	@OneToMany(mappedBy="group", cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
  private Set<ChecklistActivity> activityCollection;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getGroupName() 
	{
		return groupName;
	}

	public void setGroupName(String groupName) 
	{
		this.groupName = groupName;
	}

	public Set<ChecklistActivity> getActivityCollection() 
	{
		return activityCollection;
	}

	public void setActivityCollection(Set<ChecklistActivity> activityCollection) 
	{
		this.activityCollection = activityCollection;
	}
	
}
