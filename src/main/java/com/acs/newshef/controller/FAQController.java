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

import com.acs.newshef.model.FAQFormModel;
import com.acs.newshef.model.FAQModel; 
import com.acs.newshef.orm.FAQ;
import com.acs.newshef.service.FAQServiceImpl;
import com.acs.newshef.service.VersionControlServiceImpl;
 

@Controller
public class FAQController 
{
	private static final Logger logger = LoggerFactory.getLogger(FAQController.class);
	
  @Autowired
  private FAQServiceImpl faqService;
    
  @Autowired
  private VersionControlServiceImpl versionService;
  
	private   List<FAQModel> questions = new ArrayList<FAQModel>();
 
	private void prepareFAQModel() 
	{
		questions.clear();
		Set<FAQ> questionCollection = faqService.loadQuestion();
		Iterator it = questionCollection.iterator();
		while(it.hasNext())
		{
			FAQ question = (FAQ) it.next(); 
			questions.add(new FAQModel(Integer.toString(question.getId()),question.getQuestion(), question.getAnswer(), question.getKeyword()));				
		}
	}
	 
  @RequestMapping(value = "/faq", method = RequestMethod.GET)
  public ModelAndView get(ModelMap model) 
  {
   	prepareFAQModel();
    FAQFormModel faqForm = new FAQFormModel();
    faqForm.setQuestions(questions); 
		model.addAttribute("faqFormLength", Integer.toString(faqForm.getQuestions().size()));	
		return new ModelAndView("faq" , "faqForm", faqForm);
  }
    
  @RequestMapping(value = "/faq", method = RequestMethod.POST)
  public String save(@ModelAttribute("faqForm") FAQFormModel faqForm)
  { 
  	try{
  		
	  	boolean change = false;
	  	List<FAQModel> questions = faqForm.getQuestions();
	         
	    Iterator it = questions.iterator();
	    while(it.hasNext())
	    {
	    	FAQModel faqModel = (FAQModel) it.next();
	    	 
	    	if (faqModel.getId()==null)
	    	{
	        FAQ faq = new FAQ();
	        faq.setQuestion(faqModel.getQuestion());
	        faq.setAnswer(faqModel.getAnswer());
	        faq.setKeyword(faqModel.getKeyword());
	    		faqService.addQuestion(faq);
	    		change = true;
	    	}
	    	else
	    	{
	    		if (faqModel.getRemoved().equals("1"))
	    		{
	    			faqService.remove(faqModel.getId());
	    			change = true;
	    		}
	    		else
	    		{
	      		if(faqModel.getEdited().equals("1"))
	      		{
	      			FAQ faq = faqService.getQuestionById(faqModel.getId());
	      			faq.setQuestion(faqModel.getQuestion());
	      			faq.setAnswer(faqModel.getAnswer());
	      			faq.setKeyword(faqModel.getKeyword());
	      			faqService.updateQuestion(faq);
	      			change = true;
	      		}
	    		}
	    	}   	
	    }
	    
	    if (change)
	    {
	    	versionService.updateVersionFAQ();
	    	versionService.updateVersion();
	    }
	        
	    return "redirect:faq";
	    
  	} catch(Exception e) {  
      return "redirect:error";
    }
  } 
}