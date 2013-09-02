package com.acs.newshef.model;

public class FAQModel 
{
	
	private String id;
  private String question;  
  private String answer;
  private String keyword;
  private String edited;
  private String removed;
  
  public FAQModel() 
  {
  	
  }
  
  public FAQModel(String question, String answer, String keyword) 
  {
  	this.question = question;
  	this.answer = answer;
  	this.keyword = keyword;
  }
  
  public FAQModel(String id, String question, String answer, String keyword)
  {
  	this.id = id;
  	this.question = question;
  	this.answer = answer;
  	this.keyword = keyword;
  }
  
  public FAQModel(String id, String question, String answer, String keyword, String edited, String removed)
  {
  	this.id = id;
  	this.question = question;
  	this.answer = answer;
  	this.keyword = keyword;
  	this.edited = edited;
  	this.removed = removed;
  }
    
	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getQuestion() 
	{
		return question;
	}
	
	public void setQuestion(String question) 
	{
		this.question = question;
	}
	
	public String getAnswer() 
	{
		return answer;
	}
	
	public void setAnswer(String answer) 
	{
		this.answer = answer;
	}
	
	public String getEdited() 
	{
		return edited;
	}
	
	public void setEdited(String edited) 
	{
		this.edited = edited;
	}
	
	public String getRemoved() 
	{
		return removed;
	}
	
	public void setRemoved(String removed) 
	{
		this.removed = removed;
	}

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}
	
	
  
}
