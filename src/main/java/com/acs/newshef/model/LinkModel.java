package com.acs.newshef.model;

public class LinkModel 
{
	private String id;
  private String description;
  private String url;
  private String edited;
  private String removed;
  
  public LinkModel() 
  {
  	
  }
  
  public LinkModel(String description, String url) 
  {
  	this.description = description;
  	this.url = url;
  }
  
  public LinkModel(String id, String description, String url)
  {
  	this.id = id;
  	this.description = description;
  	this.url = url;
  }
  
  public LinkModel(String id, String description, String url, String edited, String removed)
  {
  	this.id = id;
  	this.description = description;
  	this.url = url;
  	this.edited = edited;
  	this.removed = removed;
  }

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getUrl() 
	{
		return url;
	}

	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
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
