package com.acs.newshef.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acs.newshef.model.LinkFormModel;
import com.acs.newshef.model.LinkModel;
import com.acs.newshef.orm.Link;
import com.acs.newshef.service.LinkServiceImpl;
import com.acs.newshef.service.VersionControlServiceImpl;
 

@Controller
public class LinkController 
{
	private static final Logger logger = LoggerFactory.getLogger(LinkController.class);
	
  @Autowired
  private LinkServiceImpl linkService;
  
  @Autowired
  private VersionControlServiceImpl versionService;
    
	private   List<LinkModel> links = new ArrayList<LinkModel>();
  
	private void prepareLinkModel() 
	{
		links.clear();
		Set<Link> linkCollection = linkService.loadLinks();
		Iterator it = linkCollection.iterator();
		while(it.hasNext())
		{
			Link link = (Link) it.next(); 
			links.add(new LinkModel(Integer.toString(link.getId()),link.getDescription(), link.getUrl()));				
		}
	}
	 
  @RequestMapping(value = "/link", method = RequestMethod.GET)
  public ModelAndView get(ModelMap model) 
  {
   	prepareLinkModel();
  	logger.debug("add something");
    LinkFormModel linkForm = new LinkFormModel();
    linkForm.setLinks(links);
    model.addAttribute("linkFormLength", Integer.toString(linkForm.getLinks().size()));		 
    return new ModelAndView("link" , "linkForm", linkForm);
  }
    
  @RequestMapping(value = "/link", method = RequestMethod.POST)
  public String save(@ModelAttribute("linkForm") LinkFormModel linkForm) 
  {
  	try{
	    boolean change = false;
	    List<LinkModel> links = linkForm.getLinks();
	         
	    Iterator it = links.iterator();
	    while(it.hasNext())
	    {
	    	LinkModel linkmodel = (LinkModel) it.next();
	
	    	if (linkmodel.getId()==null)
	    	{
	        Link link = new Link();
	        link.setDescription(linkmodel.getDescription());
	        link.setUrl(linkmodel.getUrl());
	    		linkService.addLink(link);
	    		change = true;
	    	}
	    	else
	    	{
	    		if (linkmodel.getRemoved().equals("1"))
	    		{
	    			linkService.remove(linkmodel.getId());
	    			change = true;
	    		}
	    		else
	    		{
	      		if(linkmodel.getEdited().equals("1"))
	      		{
	      			Link link = linkService.getLinkById(linkmodel.getId());
	      			link.setDescription(linkmodel.getDescription());
	      			link.setUrl(linkmodel.getUrl());
	      			linkService.updateLink(link);
	      			change = true;
	      		}
	    		}
	    	} 	
	    }
	    
	    if (change)
	    {
	    	versionService.updateVersionLink();
	    	versionService.updateVersion();
	    }      
	    return "redirect:link";
  	} catch(Exception e) {  
      return "redirect:error";
    }
  } 
}
