package com.acs.newshef.model;

public class VersionFormModel
{
	private String version;
	private String versionChecklist;
	private String versionContact;
	private String versionFAQ;
	private String versionGoogleMap;
	private String versionLink;
	private String versionUEB;
	private String versionWelcomeTalk;
	
	public VersionFormModel()
	{
		
	}
	
	public VersionFormModel(String version, String versionChecklist, 
			String versionContact, String versionFAQ,
			String versionGoogleMap, String versionLink,
			String versionUEB, String versionWelcomeTalk)
	{
		this.version = version;
		this.versionChecklist = versionChecklist;
		this.versionContact = versionContact;
		this.versionGoogleMap = versionGoogleMap;
		this.versionFAQ = versionFAQ;
		this.versionGoogleMap = versionGoogleMap;
		this.versionLink = versionLink;
		this.versionUEB = versionUEB;
		this.versionWelcomeTalk = versionWelcomeTalk;
	}
	
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
