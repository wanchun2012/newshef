package com.acs.newshef.model;

import java.util.List;

public class ChecklistFormModel 
{
	private List<ChecklistGroupModel> groupCollection;
	 

	public List<ChecklistGroupModel> getGroupCollection() 
	{
		return groupCollection;
	}

	public void setGroupCollection(
			List<ChecklistGroupModel> groupCollection) 
	{
		this.groupCollection = groupCollection;
	}

}
