package com.acs.newshef.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acs.newshef.model.UEBFormModel;
import com.acs.newshef.model.UEBModel;
import com.acs.newshef.model.UEBSubModel;
import com.acs.newshef.orm.UEBPosition;
import com.acs.newshef.orm.UEBSubPosition;
import com.acs.newshef.service.UEBServiceImpl;
import com.acs.newshef.service.VersionControlServiceImpl;

@Controller
public class UEBController 
{

	private static final Logger logger = LoggerFactory.getLogger(UEBController.class);
	
	@Autowired
    private UEBServiceImpl uebService;
  @Autowired
  private VersionControlServiceImpl versionService;
	private List<UEBModel> ueb = new ArrayList<UEBModel>();
		
	private void prepareUEBFormModel() 
	{
		ueb.clear();
		Set<UEBPosition> positionCollection = uebService.loadPositions();
		Iterator it = positionCollection.iterator();
		while(it.hasNext())
		{			
			UEBPosition position = (UEBPosition) it.next();
			ueb.add(new UEBModel(Integer.toString(position.getId()),
							position.getPositionName(),
							uebService.loadSubPositionCollection(Integer.toString(position.getId())))); 		 	
		}
	}
	
	@RequestMapping(value = "/ueb_position", method = RequestMethod.GET)
	public ModelAndView getUEBPos(ModelMap model) 
	{
   	prepareUEBFormModel();
    UEBFormModel uebForm = new UEBFormModel();     
    uebForm.setUebCollection(ueb);	
    model.addAttribute("UEBFormLength", Integer.toString(ueb.size()));	
    return new ModelAndView("ueb_position" , "uebForm", uebForm);
  }
	
	@RequestMapping(value = "/ueb_select", method = RequestMethod.GET)
	public ModelAndView getUEBSelectPos(ModelMap model) 
	{
	  prepareUEBFormModel();
    UEBFormModel uebForm = new UEBFormModel();  
    uebForm.setUebCollection(ueb);;    
    model.addAttribute("UEBFormLength", Integer.toString(ueb.size()));	
    return new ModelAndView("ueb_select" , "uebForm", uebForm);
  }
	 
	@RequestMapping(value = "/ueb_subposition", method = RequestMethod.GET)
	public ModelAndView getUEBSub(HttpServletRequest request, HttpServletResponse response, 
			 ModelMap model)  throws ServletException, IOException 
	{
	  prepareUEBFormModel();
    UEBModel uebForm = new UEBModel();  
      
    String uebId = request.getParameter("uebId");
    Iterator it = ueb.iterator();
    while(it.hasNext())
    {
  	  UEBModel positionModel = (UEBModel) it.next();
  	  if((positionModel.getId()).equals(uebId))
  	  {
  		  uebForm = positionModel;
  		  break;
  	  }
    }
    model.addAttribute("uebId", uebId);	    
    return new ModelAndView("ueb_subposition" , "uebForm", uebForm);
  }
	
	@RequestMapping(value = "/ueb_details", method = RequestMethod.GET)
	public ModelAndView getUEBDetails(HttpServletRequest request, HttpServletResponse response, 
			 ModelMap model)  throws ServletException, IOException 
	{  
     String uebId = request.getParameter("uebId");    
     model.addAttribute("uebId", uebId);	
     UEBSubPosition subpos = uebService.getSubPositionById(uebId);
     UEBSubModel uebsubForm = new UEBSubModel(Integer.toString(subpos.getId()), 
    		 													subpos.getSubpositionName(), 
    		 													subpos.getUebName(), subpos.getUebDescription());
     return new ModelAndView("ueb_details" , "uebForm", uebsubForm);
  }
	
	@RequestMapping(value = "/ueb_subselect", method = RequestMethod.GET)
	public ModelAndView getUEBSelectSub(HttpServletRequest request, HttpServletResponse response,
			 ModelMap model)  throws ServletException, IOException 
	{
		prepareUEBFormModel();
    UEBModel uebForm = new UEBModel();  
    
    String uebId = request.getParameter("uebId");
    Iterator it = ueb.iterator();
    while(it.hasNext())
    {
  	  UEBModel positionModel = (UEBModel) it.next();
  	  if((positionModel.getId()).equals(uebId))
  	  {
  		  uebForm = positionModel;
  		  break;
  	  }
    }
    model.addAttribute("uebId", uebId);	    
    return new ModelAndView("ueb_subselect" , "uebForm", uebForm);
  }
	 
	@RequestMapping(value = "/ueb_position", method = RequestMethod.POST)
  public String saveUEBPos(@ModelAttribute("uebForm") UEBFormModel uebForm) 
	{
		try{
			boolean change = false;
	    List<UEBModel> positionCollection = uebForm.getUebCollection();
	     
	    Iterator it = positionCollection.iterator();
	    while(it.hasNext())
	    {
	    	UEBModel uebModel = (UEBModel) it.next();
	    	 
	    	if (uebModel.getId()==null)
	    	{
	    		UEBPosition position = new UEBPosition();
	        position.setPositionName(uebModel.getPositionName());	 
	    		uebService.addPosition(position);
	    		change = true;
	    	}
	    	else
	    	{
	    		if (uebModel.getRemoved().equals("1"))
	    		{
	    			uebService.removePosition(uebModel.getId());
	    	 		change = true;
	    		}
	    		else
	    		{
	      		if(uebModel.getEdited().equals("1"))
	      		{
	      			UEBPosition position = uebService.getPositionById(uebModel.getId());
	      			position.setPositionName(uebModel.getPositionName());
	      			 
	      			uebService.updatePosition(position);
	      	 		change = true;
	      		}
	    		}
	    	} 	
	    }
	     
	    if (change)
	    {
	    	versionService.updateVersionUEB();
	    	versionService.updateVersion();
	    } 	
	    	
	    return "redirect:/ueb_select";
		} catch(Exception e) {  
      return "redirect:error";
    }
  }
	
	@RequestMapping(value = "/ueb_subposition", method = RequestMethod.POST)
  public String saveUEBSub(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("uebForm") UEBModel uebForm, Map<String, Object> map) 
    				throws ServletException, IOException
  {	
		try{
 
			boolean change = false;
			List<UEBSubModel> subModelCollection = uebForm.getSubCollection();
			String selectIndex = request.getParameter("indexValue");
			map.put("uebId", selectIndex); 
			UEBPosition position = uebService.getPositionById(selectIndex);
			// check object existence before inserting, updating and deleting
	    if(position==null)
	    {
	    	return "redirect:error";
	    }
			
	    Iterator it = subModelCollection.iterator();
	    while(it.hasNext())
	    {
	    	UEBSubModel subModel = (UEBSubModel) it.next();
	    	
	    	if (subModel.getId()==null)
	    	{		 
	    		UEBSubPosition subposition = new UEBSubPosition();
	    		subposition.setSubpositionName(subModel.getSubpositionName()); 
	    		subposition.setPosition(position);
	        uebService.addSubPosition(subposition);
	    		//checklistService.updateGroups(group);     
	        change = true;
	    	}
	    	else
	    	{		 
	    		if (subModel.getRemoved().equals("1"))
	    		{
	    			uebService.removeSubPosition(subModel.getId());
	    			change = true;
	    		}
	    		else
	    		{
	      		if(subModel.getEdited().equals("1"))
	      		{		
	      			UEBSubPosition subposition = uebService.getSubPositionById(subModel.getId());
	      			subposition.setSubpositionName(subModel.getSubpositionName()); 
	            subposition.setPosition(position);
	      			uebService.updateSubPosition(subposition);
	      			change = true;
	      		}
	    		}       		 
	    	}        	
	    }
	    if (change)
	    {
	    	versionService.updateVersionUEB();
	    	versionService.updateVersion();
	    } 	
	    return "redirect:/ueb_subselect";
		} catch(Exception e) {  
      return "redirect:error";
    }
  }
	
	@RequestMapping(value = "/ueb_select", method = RequestMethod.POST)
  public String saveUEBSelectPos(HttpServletRequest request, HttpServletResponse response,
  		Map<String, Object> map) throws ServletException, IOException 
  {
		try{
			String selectIndex = request.getParameter("indexValue");
		  prepareUEBFormModel();
			UEBModel uebForm = null;
			Iterator it = ueb.iterator();
			while(it.hasNext())
			{
			  UEBModel positionModel = (UEBModel) it.next();
				if(positionModel.getId().equals(selectIndex))
				{
				  uebForm = positionModel;
					break;
				}
			}
			map.put("uebId", selectIndex); 
		  map.put("uebForm", uebForm); 	
		  return "redirect:/ueb_subposition";  
		} catch(Exception e) {  
      return "redirect:error";
    }
  }
	
	@RequestMapping(value = "/ueb_subselect", method = RequestMethod.POST)
  public String saveUEBSelectSub(HttpServletRequest request, 
  		HttpServletResponse response,Map<String, Object> map) throws ServletException, IOException 
  {
		 try {
			 String selectIndex = request.getParameter("indexValue");
			 prepareUEBFormModel();
		 
	     UEBSubPosition subpos = uebService.getSubPositionById(selectIndex);
	     UEBSubModel uebsubForm = new UEBSubModel(Integer.toString(subpos.getId()), subpos.getSubpositionName(), 
			 subpos.getUebName(), subpos.getUebDescription());
	  
			 map.put("uebId", selectIndex); 
		   map.put("uebForm", uebsubForm);      
		   return "redirect:/ueb_details";  
		 }  catch(Exception e) {  
	      return "redirect:error";
	   }
  }
	
	@RequestMapping(value = "/ueb_details", method = RequestMethod.POST)
  public String saveUEBDetails(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("uebForm") UEBSubModel uebForm,
    		@RequestParam("file") MultipartFile file,
    		Map<String, Object> map) throws ServletException, IOException
  {
		try{
			boolean change = false;
			String selectIndex = request.getParameter("indexValue");
			String name = request.getParameter("nameValue");
			String description = request.getParameter("descriptionValue");
	
			UEBSubPosition subposition = uebService.getSubPositionById(selectIndex);
			// check object existence before inserting, updating and deleting
	    if(subposition==null)
	    {
	    	return "redirect:error";
	    }
	    
			subposition.setUebName(name); 
			subposition.setUebDescription(description);
			System.out.println("subposition.setSubpositionName" + name);
			System.out.println("subposition.setUebDescription"+ description);
	 
			try 
			{			        			 
	  		byte[] bFile =  file.getBytes();
	  		subposition.setContent(bFile);            
	  		subposition.setContentType(file.getContentType());
	  		subposition.setImageURL(Properties.downloadUEBImageUrl+subposition.getId()+".html");
			} 
			catch (IOException e) {
		    e.printStackTrace();
		  }
	         
			uebService.updateSubPosition(subposition);
			change = true;   	
			if (change)
	    {
	    	versionService.updateVersionUEB();
	    	versionService.updateVersion();
	    } 	
			return "redirect:/ueb_position";
		} catch(Exception e) {  
      return "redirect:error";
    }
  }
	
	@RequestMapping("ueb/download/{uebId}")
  public String download(@PathVariable("uebId")
            Integer uebId, HttpServletResponse response) 
	{        
    UEBSubPosition sub = uebService.getSubPositionById(Integer.toString(uebId));
    try 
    {
      response.setHeader("Content-Disposition", "inline;filename=\"" +sub.getId()+ "\"");
      OutputStream out = response.getOutputStream();
      response.setContentType(sub.getContentType());
      InputStream in = new ByteArrayInputStream(sub.getContent()); 
      IOUtils.copy(in, out);
      out.flush();
      out.close();
    } 
    catch (IOException e) {
        e.printStackTrace();
    } 
    return null;
	}
	
}
