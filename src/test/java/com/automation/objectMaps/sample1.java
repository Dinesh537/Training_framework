package com.automation.objectMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class sample1 {
	
	@FindAll({@FindBy(xpath="//input[@name='q']")})
	public WebElement Search_txt;
	
	@FindAll({@FindBy(xpath="(//input[@value='Google Search456'])[1]")})
	public WebElement Search_btn;
	
	WebDriver lDriver;
	
	public sample1(WebDriver lDriver)
	{
		this.lDriver = lDriver;
	}
	

}
