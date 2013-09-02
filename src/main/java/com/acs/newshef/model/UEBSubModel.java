package com.acs.newshef.model;

public class UEBSubModel 
{
	
	private String id; 
  private String subpositionName; 
	private String uebName;
	private String uebDescription;
 
	private String edited;
  private String removed;
     
  public UEBSubModel() 
  {
  	
  }
    
  public UEBSubModel(String subpositionName, String uebName, String uebDescription ) 
  {
  	this.subpositionName = subpositionName;
  	this.uebName = uebName;
  	this.uebDescription = uebDescription;	 
  }
    
  public UEBSubModel(String id, String subpositionName, String uebName, String uebDescription ) 
  {
  	this.id = id;
  	this.subpositionName = subpositionName;
  	this.uebName = uebName;
  	this.uebDescription = uebDescription;
  }
    
  public UEBSubModel(String id, String subpositionName, String uebName, String uebDescription,  
    		String edited, String removed) 
  {
    this.id = id;
    this.subpositionName = subpositionName;
    this.uebName = uebName;
    this.uebDescription = uebDescription;
 
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
	
	public String getSubpositionName() 
	{
		return subpositionName;
	}
	
	public void setSubpositionName(String subpositionName) 
	{
		this.subpositionName = subpositionName;
	}
	
	public String getUebName() 
	{
		return uebName;
	}
	
	public void setUebName(String uebName) 
	{
		this.uebName = uebName;
	}
	
	public String getUebDescription() 
	{
		return uebDescription;
	}
	
	public void setUebDescription(String uebDescription) 
	{
		this.uebDescription = uebDescription;
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
