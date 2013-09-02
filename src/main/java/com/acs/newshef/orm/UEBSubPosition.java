package com.acs.newshef.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 
public class UEBSubPosition 
{
	@Id
	@Column 
	@GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
 
  @ManyToOne (fetch=FetchType.LAZY)
  @JoinColumn(name = "position_id")  
	private UEBPosition position;
    
	@Column  
  private String subpositionName;
	
	@Column
	private String uebName;
	
	@Column(columnDefinition = "TEXT")
	private String uebDescription;
	
	@Column(columnDefinition = "LONGBLOB")
	private byte[] content;
     
  @Column(name="content_type")
  private String contentType;

	@Column  
  private String imageURL;

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public UEBPosition getPosition() 
	{
		return position;
	}

	public void setPosition(UEBPosition position) 
	{
		this.position = position;
	}

	public String getSubpositionName() 
	{
		return subpositionName;
	}

	public void setSubpositionName(String subpositionName) 
	{
		this.subpositionName = subpositionName;
	}

	public String getUebName() 
	{
		return uebName;
	}

	public void setUebName(String uebName)
	{
		this.uebName = uebName;
	}

	public String getUebDescription() 
	{
		return uebDescription;
	}

	public void setUebDescription(String uebDescription) 
	{
		this.uebDescription = uebDescription;
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
