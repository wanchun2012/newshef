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
public class UEBPosition 
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column  
  private String positionName;
  
	@OneToMany(mappedBy="position", cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
  private Set<UEBSubPosition> subpostionCollection;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
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

	public Set<UEBSubPosition> getSubpostionCollection() 
	{
		return subpostionCollection;
	}

	public void setSubpostionCollection(Set<UEBSubPosition> subpostionCollection) 
	{
		this.subpostionCollection = subpostionCollection;
	}
	
}
