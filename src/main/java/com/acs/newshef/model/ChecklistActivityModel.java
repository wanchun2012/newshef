package com.acs.newshef.model;

public class ChecklistActivityModel 
{
	
	private String id;
	private String activityName;  
	private String details;
	private String responsiblePerson;
  private String edited;
  private String removed;
  
  public ChecklistActivityModel() 
  {
  	
  }
  
  public ChecklistActivityModel(String activityName, String details, String responsiblePerson) 
  {
  	this.activityName = activityName;
  	this.details = details;
  	this.responsiblePerson = responsiblePerson;
  }
  
  public ChecklistActivityModel(String id, String activityName, String details, String responsiblePerson) 
  {
  	this.id = id;
  	this.activityName = activityName;
  	this.details = details;
  	this.responsiblePerson = responsiblePerson;
  }
  
  public ChecklistActivityModel(String id, String activityName, String details, String responsiblePerson,
  		String edited, String removed) 
  {
  	this.id = id;
  	this.activityName = activityName;
  	this.details = details;
  	this.responsiblePerson = responsiblePerson;
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
