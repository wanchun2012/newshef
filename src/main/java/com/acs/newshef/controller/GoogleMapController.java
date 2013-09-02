package com.acs.newshef.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acs.newshef.model.GoogleMapFormModel;
import com.acs.newshef.model.GoogleMapModel;
import com.acs.newshef.orm.GoogleMap;
import com.acs.newshef.service.GoogleMapServiceImpl;
import com.acs.newshef.service.VersionControlServiceImpl;

@Controller
public class GoogleMapController 
{
	private static final Logger logger = LoggerFactory.getLogger(GoogleMapController.class);
	
  @Autowired
  private GoogleMapServiceImpl mapService;
  
  @Autowired
  private VersionControlServiceImpl versionService;
    
	private   List<GoogleMapModel> markers = new ArrayList<GoogleMapModel>();
  
	private void prepareGoogleMapModel() 
	{
		markers.clear();
		Set<GoogleMap> markerCollection = mapService.loadMarkers();
		Iterator it = markerCollection.iterator();
		while(it.hasNext())
		{
			GoogleMap marker = (GoogleMap) it.next(); 
			if(marker.isInsideView())
			{
				markers.add(new GoogleMapModel(Integer.toString(marker.getId()),marker.getLongitude(),marker.getLatitude(),
					 "1",marker.getTitle(),marker.getSnippet()));	
			}
			else
			{
				markers.add(new GoogleMapModel(Integer.toString(marker.getId()),marker.getLongitude(),marker.getLatitude(),
						 "0",marker.getTitle(),marker.getSnippet()));		
			}
		}
	}
	 
  @RequestMapping(value = "/googlemap", method = RequestMethod.GET)
  public ModelAndView get(ModelMap model) 
  {
    prepareGoogleMapModel();
    GoogleMapFormModel mapForm = new GoogleMapFormModel();
    mapForm.setMarkers(markers); 
		model.addAttribute("mapFormLength", Integer.toString(mapForm.getMarkers().size()));	
		return new ModelAndView("googlemap" , "mapForm", mapForm);
  }
    
  @RequestMapping(value = "/googlemap", method = RequestMethod.POST)
  public String save(@ModelAttribute("mapForm") GoogleMapFormModel mapForm) 
  {
  	try{
	    List<GoogleMapModel> markers = mapForm.getMarkers();
	    boolean change = false;
	    Iterator it = markers.iterator();
	    while(it.hasNext())
	    {
	    	GoogleMapModel mapModel = (GoogleMapModel) it.next();
	    	 
	    	if (mapModel.getId()==null)
	    	{
	      	GoogleMap marker = new GoogleMap();
	      	marker.setLatitude(mapModel.getLatitude());
	      	marker.setLongitude(mapModel.getLongitude());
	      	 
	      	if((mapModel.getInsideView()).equals("1"))
	      		marker.setInsideView(true);
	      	else
	      		marker.setInsideView(false);
	        	 	
	        marker.setTitle(mapModel.getTitle());
	        marker.setSnippet(mapModel.getSnippet());
	    		mapService.addMarker(marker);
	    		change = true;
	    	}
	    	else
	    	{
	    		if (mapModel.getRemoved().equals("1"))
	    		{
	    			mapService.remove(mapModel.getId());
	    			change = true;
	    		}
	    		else
	    		{
	      		if(mapModel.getEdited().equals("1"))
	      		{
	      			GoogleMap marker = mapService.getMarkerById(mapModel.getId());
	      			marker.setLatitude(mapModel.getLatitude());
	            marker.setLongitude(mapModel.getLongitude());
	            if((mapModel.getInsideView()).equals("1"))
	              marker.setInsideView(true);
	            else
	              marker.setInsideView(false);
	            marker.setTitle(mapModel.getTitle());
	            marker.setSnippet(mapModel.getSnippet());
	      			mapService.updateMarker(marker);
	      			change = true;
	      		}
	    		}
	    	}   	
	    }
	    if (change)
	    {
	    	versionService.updateVersionGoogleMap();
	    	versionService.updateVersion();
	    }
	    return "redirect:googlemap";
  	} catch(Exception e) {  
      return "redirect:error";
    }
  }
	 
}