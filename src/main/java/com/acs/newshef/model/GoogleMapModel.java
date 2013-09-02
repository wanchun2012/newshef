package com.acs.newshef.model;


public class GoogleMapModel 
{
	private String id;
  private String longitude;
  private String latitude;
  private String insideView;
  private String title;
  private String snippet;
  private String edited;
  private String removed;
  
  public GoogleMapModel() 
  {
  	
  }
  
  public GoogleMapModel(String longitude, String latitude, String insideView,String title, String snippet) 
  {
  	this.longitude = longitude;
  	this.latitude = latitude;
  	this.insideView = insideView;
  	this.title = title;
  	this.snippet = snippet;
  }
  
  public GoogleMapModel(String id, String longitude, String latitude, String insideView,
  		String title, String snippet) 
  {
  	this.id = id;
  	this.longitude = longitude;
  	this.latitude = latitude;
  	this.insideView = insideView;
  	this.title = title;
  	this.snippet = snippet;
  }
  
  public GoogleMapModel(String id, String longitude, String latitude, String insideView, 
  		String title, String snippet, String edited, String removed) 
  {
  	this.id = id;
  	this.longitude = longitude;
  	this.latitude = latitude;
  	this.insideView = insideView;
  	this.title = title;
  	this.snippet = snippet;
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
	
	public String getLongitude() 
	{
		return longitude;
	}
	
	public void setLongitude(String longitude) 
	{
		this.longitude = longitude;
	}
	
	public String getLatitude() 
	{
		return latitude;
	}
	
	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}
	 
	public String getInsideView() 
	{
		return insideView;
	}

	public void setInsideView(String insideView) 
	{
		this.insideView = insideView;
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

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getSnippet() 
	{
		return snippet;
	}

	public void setSnippet(String snippet) 
	{
		this.snippet = snippet;
	}
    
}
