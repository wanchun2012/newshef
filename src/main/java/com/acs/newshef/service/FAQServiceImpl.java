package com.acs.newshef.service;

import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acs.newshef.dao.FAQDAOImpl;
import com.acs.newshef.orm.FAQ;
 
@Service("faqService")
@Transactional
public class FAQServiceImpl 
{
	@Autowired
  private FAQDAOImpl faqDAO;
   
  public void addQuestion(FAQ question) throws HibernateException
  {
    faqDAO.addQuestion(question);
  }
  
  public Set<FAQ> loadQuestion() throws HibernateException  
  {
    return faqDAO.loadQuestion();
  }
  
  public void updateQuestion(FAQ question) throws HibernateException  
  {
    faqDAO.updateFAQ(question);;
  }
  
  public FAQ getQuestionById(String id) throws HibernateException
  {
    return faqDAO.getQuestionById(id);
  }
  
  public void remove(String id) throws HibernateException
  {
  	faqDAO.remove(id);
  }
}
