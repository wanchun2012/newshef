package com.acs.newshef.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class GoogleMap 
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column  
  private String longitude;
  
	@Column  
  private String latitude;

	@Column  
  private boolean insideView;
	
	@Column 
	private String title;
	
	@Column 
	private String snippet;

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
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

	public boolean isInsideView() 
	{
		return insideView;
	}

	public void setInsideView(boolean insideView) 
	{
		this.insideView = insideView;
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
