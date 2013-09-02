package com.acs.newshef.model;

public class WelcomeFormModel 
{
	private String welcomeText;
	
	public WelcomeFormModel()
	{
	 
	}
	
	public WelcomeFormModel(String welcomeText)
	{
		this.welcomeText = welcomeText;
	}
	
	public String getWelcomeText() {
		return welcomeText;
	}

	public void setWelcomeText(String welcomeText) {
		this.welcomeText = welcomeText;
	}
	
}
