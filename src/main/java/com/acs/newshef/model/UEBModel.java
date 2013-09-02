package com.acs.newshef.model;

import java.util.List;

public class UEBModel 
{
	private String id;
  private String positionName;
	private List<UEBSubModel> subCollection;
  private String edited;
  private String removed;
  private String size;
  
  public UEBModel() 
  {
  	
  }
  
  public UEBModel(String positionName) 
  {
  	this.positionName = positionName;
  	this.size = Integer.toString(0);
  }
  
  public UEBModel(String id, String positionName, List<UEBSubModel> subCollection) 
  {
  	this.id = id;
  	this.positionName = positionName;
  	this.subCollection = subCollection;  
  	this.size = Integer.toString(subCollection.size());
  }
    
	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getPositionName() 
	{
		return positionName;
	}
	
	public void setPositionName(String positionName) 
	{
		this.positionName = positionName;
	}
	
	public List<UEBSubModel> getSubCollection() 
	{
		return subCollection;
	}
	
	public void setSubCollection(List<UEBSubModel> subCollection) 
	{
		this.subCollection = subCollection;
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
