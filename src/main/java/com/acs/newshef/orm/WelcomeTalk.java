package com.acs.newshef.orm;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity 
public class WelcomeTalk 
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
		
	@Column(columnDefinition = "TEXT")
  private String welcomeText;
	 
	@Column  
  private String welcomeImageName;

	@Column(columnDefinition = "LONGBLOB")
	private byte[] content;
     
  @Column(name="content_type")
  private String contentType;

	@Column  
  private String imageURL;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getWelcomeText() 
	{
		return welcomeText;
	}

	public void setWelcomeText(String welcomeText) 
	{
		this.welcomeText = welcomeText;
	}
 
	public String getWelcomeImageName() 
	{
		return welcomeImageName;
	}

	public void setWelcomeImageName(String welcomeImageName) 
	{
		this.welcomeImageName = welcomeImageName;
	}

	public byte[] getContent() 
	{
		return content;
	}

	public void setContent(byte[] content) 
	{
		this.content = content;
	}

	public String getContentType() 
	{
		return contentType;
	}

	public void setContentType(String contentType) 
	{
		this.contentType = contentType;
	}

	public String getImageURL() 
	{
		return imageURL;
	}

	public void setImageURL(String imageURL) 
	{
		this.imageURL = imageURL;
	}
	
}
