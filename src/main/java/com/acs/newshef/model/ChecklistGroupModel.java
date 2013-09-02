package com.acs.newshef.model;

import java.util.List;

public class ChecklistGroupModel 
{
	
	private String id;
  private String groupName;
	private List<ChecklistActivityModel> activityCollection;
  private String edited;
  private String removed;
  private String size;
  
  public ChecklistGroupModel() 
  {
  	
  }
  
  public ChecklistGroupModel(String groupName) 
  {
  	this.groupName = groupName;
  	this.size = Integer.toString(0); 
  }
  
  public ChecklistGroupModel(String id, String groupName, List<ChecklistActivityModel> activityCollection) 
  {
  	this.id = id;
  	this.groupName = groupName;
  	this.activityCollection = activityCollection;  
  	this.size = Integer.toString(activityCollection.size());
  }
    
	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
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
	
	public List<ChecklistActivityModel> getActivityCollection() 
	{
		return activityCollection;
	}

	public void setActivityCollection(
			List<ChecklistActivityModel> activityCollection) 
	{
		this.activityCollection = activityCollection;
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
