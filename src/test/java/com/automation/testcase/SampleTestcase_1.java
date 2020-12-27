package com.automation.testcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.library.Utils;
import com.automation.library.Web;
import com.automation.listeners.ExtentReporterNG;
import com.automation.objectMaps.sample1;

@Listeners(ExtentReporterNG.class)
public class SampleTestcase_1 {
	
	@BeforeClass
	public void setup(final ITestContext testcontext) throws IOException
	{
	Utils.ReadParamters(testcontext);	
	Utils.getTestParams(testcontext);
		}
	
	@Test
	public void test(final ITestContext testcontext)
	{
		String sTestName = testcontext.getName();
		try
		{
			WebDriver driver = Web.wbLaunchBrowser(sTestName, "https:\\www.google.com");
			sample1 sample = new sample1(driver);
			PageFactory.initElements(driver, sample);
			
			Web.wbType(sTestName, sample.Search_txt, "sample.Search_txt", "Selenium", driver);
			
		}
		catch(Exception e)
		{
			}
	}
	
	@AfterClass
	public void tearDown()
	{
		
	}

}
