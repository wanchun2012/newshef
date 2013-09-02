package com.acs.newshef.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class FAQ 
{
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
  private String question;
  
	@Column(columnDefinition = "TEXT")
  private String answer;
	
	@Column 
  private String keyword;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
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

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}
	
	
}
