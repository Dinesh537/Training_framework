package com.automation.library;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class Web {
	
	public static WebDriver wbLaunchBrowser(String sTestName , String sUrl)
	{
		WebDriver driver = null;
		String sBrowser = Utils.h2TestName_TestParams.get(sTestName).get("exePlatform");
		
		if(sBrowser.equalsIgnoreCase("chrome"))
		{
			driver = Helper.launchChrome();
			driver.get(sUrl);
			Reporter.log("Browser launched");
		}
		return driver;
	}
	
	public static void wbVerifyElementPresent(String sTestName,WebElement ele,String element, WebDriver lDriver)
	{
		boolean flag = false;
		try {
			long time = Long.parseLong((String) Utils.prop.get("Obj_timeout"));
			flag = Helper.waitForElement(lDriver, ele, time);
			Reporter.log(element +"found", flag);
		}
	catch(Exception e)
		{
		Reporter.log(element +"not found", flag);
		}
	}

	
	public static void wbType(String sTestName,WebElement ele,String element, String argument, WebDriver lDriver)
	{
		boolean flag = false;
		try {
			long time = Long.parseLong((String) Utils.prop.get("Obj_timeout"));
			flag = Helper.waitForElement(lDriver, ele, time);
			ele.sendKeys(argument);
			Assert.assertTrue(flag);
			Reporter.log(element +"found", flag);
		}
	catch(Exception e)
		{
		Reporter.log(element +"not found", flag);
		System.out.println(e);
		}
	
}
}
	
class Helper
{
	public static WebDriver launchChrome()
	{
		WebDriver driver =null;
		
		System.setProperty("webdriver.chrome.driver", Utils.hSystemSettings.get("packageFolder")+"chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	
	public static boolean waitForElement(WebDriver lDriver, WebElement ele, long sec)
	{
		
		try {

	WebDriverWait wait= new WebDriverWait(lDriver, sec);
	//wait.until(ExpectedConditions.visibilityOf(ele)).wait(sec);
	lDriver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	return true;
	}
	catch(Exception e)
	{
		
		return false;
	}
	}
}
