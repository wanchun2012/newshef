package com.acs.newshef.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acs.newshef.model.VersionFormModel;
import com.acs.newshef.orm.VersionControl;
import com.acs.newshef.service.VersionControlServiceImpl;

@Controller
public class VersionController
{
	@Autowired
  private VersionControlServiceImpl versionService;
    
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public ModelAndView getVersion(ModelMap model)   
	{  	     
		VersionControl v = versionService.getVersion();
		VersionFormModel versionForm = new VersionFormModel(v.getVersion(), v.getVersionChecklist(),
				v.getVersionContact(), v.getVersionFAQ(),
				v.getVersionGoogleMap(), v.getVersionLink(),
				v.getVersionUEB(), v.getVersionWelcomeTalk());
		
		return new ModelAndView("version" , "versionForm", versionForm);
	}
}
