package com.acs.newshef.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class LoginController 
{
 
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/logged", method = RequestMethod.GET)
	public String logged(ModelMap model, Principal principal)
	{
		logger.info("logged as "+principal.getName()+" with hash "+principal.hashCode());
		model.addAttribute("username", principal.getName());	
		return "logged";
	}
	
	@RequestMapping(value = "/fail", method = RequestMethod.GET)
	public String fail()
	{
		logger.info("failure");		
		return "fail";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Principal p)
	{
		logger.info("logout for "+p.getName());		
		return "redirect:j_spring_security_logout";
	}
 
}