package com.acs.newshef.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acs.newshef.model.ChecklistActivityModel;
import com.acs.newshef.model.ChecklistFormModel;
import com.acs.newshef.model.ChecklistGroupModel;
import com.acs.newshef.orm.ChecklistActivity;
import com.acs.newshef.orm.ChecklistGroup;
import com.acs.newshef.service.ChecklistServiceImpl;
import com.acs.newshef.service.VersionControlServiceImpl;

/*
 * This class aims at get checklist_group, checklist_activity, checklist_details pages.
 * As well as get the objects from the web pages and insert, update, remove relative tables 
 * in database.
 */

@Controller
public class ChecklistController 
{	
	private static final Logger logger = LoggerFactory.getLogger(ChecklistController.class);
	
  @Autowired
  private ChecklistServiceImpl checklistService;
    
  @Autowired
  private VersionControlServiceImpl versionService;
    
	private List<ChecklistGroupModel> checklistGroup = new ArrayList<ChecklistGroupModel>();
	
	//load all the data for checklist group and activity
	private void prepareChecklistFormModel() 
	{
		checklistGroup.clear();
		Set<ChecklistGroup> groupCollection = checklistService.loadGroups();
		Iterator it = groupCollection.iterator();
		while(it.hasNext())
		{			
			ChecklistGroup group = (ChecklistGroup) it.next();
			checklistGroup.add(new ChecklistGroupModel(Integer.toString(group.getId()),
												group.getGroupName(),
												checklistService.loadActivityCollection(Integer.toString(group.getId()))));		 	
		}
	}
	
	@RequestMapping(value = "/checklist_group", method = RequestMethod.GET)
	public ModelAndView getChecklistGroup(ModelMap model) 
	{
    prepareChecklistFormModel();
    ChecklistFormModel checklistForm = new ChecklistFormModel();     
    checklistForm.setGroupCollection(checklistGroup);
      	
    model.addAttribute("ChecklistFormLength", Integer.toString(checklistGroup.size()));	        
    return new ModelAndView("checklist_group" , "checklistForm", checklistForm);
  }
	
	@RequestMapping(value = "/checklist_activity", method = RequestMethod.GET)
	public ModelAndView getChecklistActivity(ModelMap model) 
	{
	   prepareChecklistFormModel();
     ChecklistFormModel checklistForm = new ChecklistFormModel();  
     checklistForm.setGroupCollection(checklistGroup);
      
     model.addAttribute("ChecklistFormLength", Integer.toString(checklistGroup.size()));	      
     return new ModelAndView("checklist_activity" , "checklistForm", checklistForm);
   }
	
	@RequestMapping(value = "/checklist_details", method = RequestMethod.GET)
	public ModelAndView getChecklistDetails(HttpServletRequest request, HttpServletResponse response, 
			 ModelMap model)  throws ServletException, IOException 
	{
	  prepareChecklistFormModel();
    ChecklistGroupModel groupForm = new ChecklistGroupModel();  
    
    // get activity collection depends on groupid
    String groupId = request.getParameter("groupId");
    Iterator it = checklistGroup.iterator();
    while(it.hasNext())
    {
  	  ChecklistGroupModel groupModel = (ChecklistGroupModel) it.next();
   	  if((groupModel.getId()).equals(groupId))
   	  {
   		  groupForm = groupModel;
   		  break;
   	  }
     }
    
     model.addAttribute("groupId", groupId);	     
     return new ModelAndView("checklist_details" , "groupForm", groupForm);
  }
	
	@RequestMapping(value = "/checklist_group", method = RequestMethod.POST)
  public String saveGroup(@ModelAttribute("checklistForm") ChecklistFormModel checklistForm) 
	{
		try{
			boolean change = false;
	    List<ChecklistGroupModel> groupCollection = checklistForm.getGroupCollection();
	         
	    Iterator it = groupCollection.iterator();
	    while(it.hasNext())
	    {
	      ChecklistGroupModel groupModel = (ChecklistGroupModel) it.next();
	
	      if (groupModel.getId()==null)
	      {
	        ChecklistGroup group = new ChecklistGroup();
	        group.setGroupName(groupModel.getGroupName());            	 
	        checklistService.addChecklistGroup(group);
	        change = true;
	      }
	      else
	      {
	      	if (groupModel.getRemoved().equals("1"))
					{
						checklistService.removeGroup(groupModel.getId());
						change = true;
					}
					else
					{
						if(groupModel.getEdited().equals("1"))
						{
							ChecklistGroup group = checklistService.getGroupById(groupModel.getId());
							group.setGroupName(groupModel.getGroupName());					 
							checklistService.updateGroups(group);
							change = true;
						}
					}
	      } 	
	    }
	    
	    // update checklist version and the server version
			if (change)
			{
				versionService.updateVersionChecklist();
				versionService.updateVersion();
			} 		
	    
	    return "redirect:/checklist_activity";
		}catch(Exception e) {  
      return "redirect:error";
    }
  }
	 
	@RequestMapping(value = "/checklist_details", method = RequestMethod.POST)
  public String saveActivity(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("groupForm") ChecklistGroupModel groupForm) throws ServletException, IOException
  {
		try{
			boolean change = false;
			List<ChecklistActivityModel> activityModelCollection = groupForm.getActivityCollection();
			String selectIndex = request.getParameter("indexValue");
		 
			ChecklistGroup group = checklistService.getGroupById(selectIndex);
			// check object existence before inserting, updating and deleting
	    if(group==null)
	    {
	    	return "redirect:error";
	    }
			
	    Iterator it = activityModelCollection.iterator();
	    while(it.hasNext())
	    {
	      ChecklistActivityModel activityModel = (ChecklistActivityModel) it.next();
	
	      if (activityModel.getId()==null)
	      {        		 
	        ChecklistActivity activity = new ChecklistActivity();
	        activity.setActivityName(activityModel.getActivityName());
	        activity.setDetails(activityModel.getDetails());
	        activity.setResponsiblePerson(activityModel.getResponsiblePerson());
	        activity.setGroup(group);
	      	checklistService.addChecklistActivity(activity);
	      	change = true;
	        //checklistService.updateGroups(group);        		 
	    	}
	    	else
	    	{ 		 
	    		if (activityModel.getRemoved().equals("1"))
	    		{
	    			checklistService.removeActivity(activityModel.getId());
	    			change = true;
	    		}
	    		else
	    		{
	      		if(activityModel.getEdited().equals("1"))
	      		{
	      			ChecklistActivity activity = checklistService.getActivityById(activityModel.getId());
	      			activity.setActivityName(activityModel.getActivityName());
	      			activity.setDetails(activityModel.getDetails());
	      			activity.setResponsiblePerson(activityModel.getResponsiblePerson());    			 
	      			checklistService.updateActivities(activity);
	      			change = true;
	      		}
	    		}       		 
	      }        	
	    }	      
	    
	    // update checklist version and the server version
	    if (change)
	    {
	    	versionService.updateVersionChecklist();
	    	versionService.updateVersion();
	    } 	
	    
	    return "redirect:/checklist_group";
		}catch(Exception e) {  
      return "redirect:error";
    }
  }
	 
	@RequestMapping(value = "/checklist_activity", method = RequestMethod.POST)
  public String selectActivity(HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws ServletException, IOException 
  {
		try{
			String selectIndex = request.getParameter("indexValue");
			prepareChecklistFormModel();
			ChecklistGroupModel groupForm = null;
			Iterator it = checklistGroup.iterator();
			while(it.hasNext())
			{
				ChecklistGroupModel groupModel = (ChecklistGroupModel) it.next();
				if(groupModel.getId().equals(selectIndex))
				{
					groupForm = groupModel;
					break;
				}
			}
			map.put("groupId", selectIndex); 
		  map.put("groupForm", groupForm); 
		    
		  return "redirect:/checklist_details";
		} catch(Exception e) {  
      return "redirect:error";
    }
  }
	
}
