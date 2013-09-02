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

import com.acs.newshef.model.ContactFacultyModel;
import com.acs.newshef.model.ContactDepartmentModel;
import com.acs.newshef.model.ContactFormModel;
import com.acs.newshef.orm.ContactFaculty;
import com.acs.newshef.orm.ContactDepartment;
import com.acs.newshef.service.ContactServiceImpl;
import com.acs.newshef.service.VersionControlServiceImpl;

/*
 * This class aims at get contact_department, contact_faculty, contact_details web pages
 * As well as get the objects from the web pages and insert, update, remove relative tables 
 * in database.
 */

@Controller
public class ContactController 
{
	
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
  @Autowired
  private ContactServiceImpl contactService;
    
  @Autowired
  private VersionControlServiceImpl versionService;
  
	private List<ContactFacultyModel> contactFaculty = new ArrayList<ContactFacultyModel>();
		
	private void prepareContactFormModel() 
	{
		contactFaculty.clear();
		Set<ContactFaculty> facultyCollection = contactService.loadFaculties();
		Iterator it = facultyCollection.iterator();
		while(it.hasNext())
		{			
			ContactFaculty faculty = (ContactFaculty) it.next();
			contactFaculty.add(new ContactFacultyModel(Integer.toString(faculty.getId()),
										 faculty.getFacultyName(),
										 contactService.loadDepartmentCollection(Integer.toString(faculty.getId())))); 		 	
		}
	}
	
	@RequestMapping(value = "/contact_faculty", method = RequestMethod.GET)
	public ModelAndView getContactFaculty(ModelMap model) 
	{
    prepareContactFormModel();
    ContactFormModel contactForm = new ContactFormModel();     
    contactForm.setFacultyCollection(contactFaculty);     	
    model.addAttribute("ContactFormLength", Integer.toString(contactFaculty.size()));	
    return new ModelAndView("contact_faculty" , "contactForm", contactForm);
  }
	
	@RequestMapping(value = "/contact_department", method = RequestMethod.GET)
	public ModelAndView getContactDepartment(ModelMap model) 
	{
	  prepareContactFormModel();
    ContactFormModel contactForm = new ContactFormModel();  
    contactForm.setFacultyCollection(contactFaculty);     
    model.addAttribute("ContactFormLength", Integer.toString(contactFaculty.size()));	
    return new ModelAndView("contact_department" , "contactForm", contactForm);
  }
	
	@RequestMapping(value = "/contact_details", method = RequestMethod.GET)
	public ModelAndView getContactDetails(HttpServletRequest request, HttpServletResponse response, 
			 ModelMap model)  throws ServletException, IOException 
	{
	  prepareContactFormModel();
    ContactFacultyModel facultyForm = new ContactFacultyModel();  
      
    String deptId = request.getParameter("deptId");
    Iterator it = contactFaculty.iterator();
    while(it.hasNext())
    {
    	ContactFacultyModel facultyModel = (ContactFacultyModel) it.next();
    	if((facultyModel.getId()).equals(deptId))
    	{
    	  facultyForm = facultyModel;
    		break;
    	}
    }
    model.addAttribute("deptId", deptId);	
    return new ModelAndView("contact_details" , "departmentForm", facultyForm);
  }
	
	@RequestMapping(value = "/contact_faculty", method = RequestMethod.POST)
  public String saveContactFaculty(@ModelAttribute("contactForm") ContactFormModel contactForm) 
	{
		try{
			boolean change = false;
	    List<ContactFacultyModel> facultyCollection = contactForm.getFacultyCollection();
	         
	    Iterator it = facultyCollection.iterator();
	    while(it.hasNext())
	    {
	    	ContactFacultyModel facultyModel = (ContactFacultyModel) it.next();
	     
	    	if (facultyModel.getId()==null)
	    	{
	    		ContactFaculty department = new ContactFaculty();
	    		department.setFacultyName(facultyModel.getFacultyName());       	 
	    		contactService.addFaculty(department);
	    		change = true;
	    	}
	    	else
	    	{
	    		if (facultyModel.getRemoved().equals("1"))
	    		{
	    			contactService.removeFaculty(facultyModel.getId());
	    			change = true;
	    		}
	    		else
	    		{
	      		if(facultyModel.getEdited().equals("1"))
	      		{
	      			ContactFaculty department = contactService.getFacultyById(facultyModel.getId());
	      			department.setFacultyName(facultyModel.getFacultyName());   			 
	      			contactService.updateFaculty(department);
	      			change = true;
	      		}
	    		}
	    	}
	    }
	         
	    if (change)
	    {
	    	versionService.updateVersionContact();
	    	versionService.updateVersion();
	    } 	
	    
	    return "redirect:/contact_department";
		} catch(Exception e) {  
      return "redirect:error";
    }
  }
	 
	@RequestMapping(value = "/contact_details", method = RequestMethod.POST)
  public String saveContactDetails(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("departmentForm") ContactFacultyModel departmentForm) throws ServletException, IOException
  {
		try{
			boolean change = false;
			List<ContactDepartmentModel> departmentModelCollection = departmentForm.getDepartmentCollection();
			String selectIndex = request.getParameter("indexValue");
			System.out.println("group"+selectIndex);
			ContactFaculty faculty = contactService.getFacultyById(selectIndex);
			// check object existence before inserting, updating and deleting
	    if(faculty==null)
	    {
	    	return "redirect:error";
	    }
	    
	    Iterator it = departmentModelCollection.iterator();
	    while(it.hasNext())
	    {
	    	ContactDepartmentModel departmentModel = (ContactDepartmentModel) it.next();
	
	    	if (departmentModel.getId()==null)
	    	{	 
	    		ContactDepartment department = new ContactDepartment();
	    		department.setDepartmentName(departmentModel.getDepartmentName());
	    		department.setEmail(departmentModel.getEmail()); 
	    		department.setPhone(departmentModel.getPhone());     
	    		department.setFaculty(faculty);
	        contactService.addDepartment(department);
	        change = true;
	    		//checklistService.updateGroups(group);        		 
	    	}
	    	else
	    	{
	    		if (departmentModel.getRemoved().equals("1"))
	    		{
	    			contactService.removeDepartment(departmentModel.getId());
	    			change = true;
	    		}
	    		else
	    		{
	      		if(departmentModel.getEdited().equals("1"))
	      		{
	      			ContactDepartment department = contactService.getDepartmentById(departmentModel.getId());
	      			department.setDepartmentName(departmentModel.getDepartmentName());
	          	department.setEmail(departmentModel.getEmail()); 
	          	department.setPhone(departmentModel.getPhone()); 			 
	      			contactService.updateDepartment(department);
	      			change = true;
	      		}
	    		}       		 
	    	}        	
	    }
	      
	    if (change)
	    {
	    	versionService.updateVersionContact();
	    	versionService.updateVersion();
	    }
	    
	    return "redirect:/contact_faculty";
		} catch(Exception e) {  
      return "redirect:error";
    }
  }
	 
	@RequestMapping(value = "/contact_department", method = RequestMethod.POST)
  public String saveContactDepartment(HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws ServletException, IOException 
  {
		try{
			String selectIndex = request.getParameter("indexValue");
			prepareContactFormModel();
			ContactFacultyModel facultyForm = null;
			Iterator it = contactFaculty.iterator();
			while(it.hasNext())
			{
			  ContactFacultyModel facultyModel = (ContactFacultyModel) it.next();
				if(facultyModel.getId().equals(selectIndex))
				{
					facultyForm = facultyModel;
				  break;
				}
			}
			map.put("deptId", selectIndex); 
		  map.put("departmentForm", facultyForm); 
		
		  return "redirect:/contact_details";	  
		} catch(Exception e) {  
      return "redirect:error";
    }
  }

}
