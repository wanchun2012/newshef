package com.acs.newshef.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class VersionControl 
{
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column  
	private String version;
	
	@Column  
	private String versionChecklist;
	
	@Column  
	private String versionContact;
	
	@Column
	private String versionFAQ;
	
	@Column
	private String versionGoogleMap;
	
	@Column
	private String versionLink;
	
	@Column
	private String versionUEB;
	
	@Column
	private String versionWelcomeTalk;

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getVersionChecklist()
	{
		return versionChecklist;
	}

	public void setVersionChecklist(String versionChecklist)
	{
		this.versionChecklist = versionChecklist;
	}

	public String getVersionContact()
	{
		return versionContact;
	}

	public void setVersionContact(String versionContact)
	{
		this.versionContact = versionContact;
	}

	public String getVersionFAQ()
	{
		return versionFAQ;
	}

	public void setVersionFAQ(String versionFAQ)
	{
		this.versionFAQ = versionFAQ;
	}

	public String getVersionGoogleMap()
	{
		return versionGoogleMap;
	}

	public void setVersionGoogleMap(String versionGoogleMap)
	{
		this.versionGoogleMap = versionGoogleMap;
	}

	public String getVersionLink()
	{
		return versionLink;
	}

	public void setVersionLink(String versionLink)
	{
		this.versionLink = versionLink;
	}

	public String getVersionUEB()
	{
		return versionUEB;
	}

	public void setVersionUEB(String versionUEB)
	{
		this.versionUEB = versionUEB;
	}

	public String getVersionWelcomeTalk()
	{
		return versionWelcomeTalk;
	}

	public void setVersionWelcomeTalk(String versionWelcomeTalk)
	{
		this.versionWelcomeTalk = versionWelcomeTalk;
	}
	
}
