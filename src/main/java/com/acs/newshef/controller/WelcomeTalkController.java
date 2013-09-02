package com.acs.newshef.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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

import com.acs.newshef.model.WelcomeFormModel;
import com.acs.newshef.orm.WelcomeTalk;
import com.acs.newshef.service.VersionControlServiceImpl;
import com.acs.newshef.service.WelcomeTalkServiceImpl;

@Controller
public class WelcomeTalkController 
{
  @Autowired
  private WelcomeTalkServiceImpl welcomeService;
  
  @Autowired
  private VersionControlServiceImpl versionService;
    
	@RequestMapping(value = "/welcometalk", method = RequestMethod.GET)
	public ModelAndView getWelcomeTalk(ModelMap model)   
	{  	     
		WelcomeTalk wt = welcomeService.getUpdatedItem();
		WelcomeFormModel welcomeForm = new WelcomeFormModel(wt.getWelcomeText());
	    
		return new ModelAndView("welcometalk" , "welcomeForm", welcomeForm);
	}
		
	@RequestMapping(value = "/welcometalk", method = RequestMethod.POST)
	public String saveWelcomeTalk(HttpServletRequest request, HttpServletResponse response, 
	    		@ModelAttribute("welcomeForm") WelcomeFormModel welcomeForm,
	    		@RequestParam("file") MultipartFile file,
	    		Map<String, Object> map) throws ServletException, IOException
	{
  	try{
			String name = request.getParameter("txtValue");
			String checked = null;
	
			WelcomeTalk wt = welcomeService.getUpdatedItem();
				
			if(wt.getWelcomeText()==null)
				checked = "1";
			else
				checked = request.getParameter("checked");
			if(checked.equals("1"))
			{
				try
				{			        			    	 
	    		byte[] bFile =  file.getBytes();
	    		wt.setContent(bFile);     
	    		wt.setContentType(file.getContentType());
	    		wt.setWelcomeImageName(file.getName());
	    		wt.setImageURL(Properties.downloadWelcomeTalkImageUrl+"1.html");
				} 
				catch (IOException e) 
				{
			    e.printStackTrace();
			  }
			}     
		  wt.setWelcomeText(name);
		  welcomeService.update(wt);
		  
		  versionService.updateVersionWelcomeTalk();
		  versionService.updateVersion();
			     			      
		  return "redirect:/welcometalk";
  	}catch(Exception e) {  
      return "redirect:error";
    }
  }
 
	@RequestMapping("welcometalk/download/{documentId}")
	public String download(@PathVariable("documentId")
	            Integer documentId, HttpServletResponse response) 
	{         
    WelcomeTalk wt = welcomeService.getUpdatedItem();
    try 
    {
	    response.setHeader("Content-Disposition", "inline;filename=\"" +wt.getWelcomeImageName()+ "\"");
	    OutputStream out = response.getOutputStream();
	    response.setContentType(wt.getContentType());
	    InputStream in = new ByteArrayInputStream(wt.getContent()); 
	    IOUtils.copy(in, out);
	    out.flush();
	    out.close();  
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    } 
     
    return null;
	}
}
