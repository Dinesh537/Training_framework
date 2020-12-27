package com.automation.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;

import com.google.common.collect.Table.Cell;

public class Utils {
	
	public static Properties prop = new Properties();
	
	public static Hashtable<String, Hashtable<String,  String>> h2TestName_TestParams = new Hashtable<String, Hashtable<String,  String>>();
	public static HashMap<String,String> hSystemSettings = new HashMap<String , String>(); 
	public static Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String,String>>();	   
	public static Map<String, String> dataMap = new HashMap<String, String>();

	public static void ReadParamters(ITestContext context) throws IOException
	{
		getSystemSettings();
		getEnvParams();
	}
	
	public static void getEnvParams() throws IOException
	{
		try {
		InputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
	    prop.load(ip);
		}
		catch(NullPointerException ne)
		{
			ne.printStackTrace();
		}
	}
	
	public static void getSystemSettings()
	{
		File file =new File(System.getProperty("user.dir"));
		hSystemSettings.put("userDir", System.getProperty("user.dir"));
		hSystemSettings.put("packageFolder", file+"\\external\\drivers\\");
		
	}
	public static void getTestParams(final ITestContext testContext)
	{
		System.out.println(testContext.getName());
		Hashtable<String ,String> hParams = new Hashtable<String, String>(testContext.getCurrentXmlTest().getAllParameters());
		h2TestName_TestParams.put(testContext.getName(), hParams);
		
	}
	
	public static void getDataFromExcel(final ITestContext testContext, String workBook, String worksheet) throws IOException, EncryptedDocumentException, InvalidFormatException
	{
		 FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+workBook+".xlsx"));
	
		 Workbook workbook = new XSSFWorkbook(file);

	        // Retrieving the number of sheets in the Workbook
	        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

	      
	        // 1. You can obtain a sheetIterator and iterate over it
	        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
	        System.out.println("Retrieving Sheets using Iterator");
	        Sheet sheet=null;
	        while (sheetIterator.hasNext()) {
	             sheet = sheetIterator.next();
	            System.out.println("=> " + sheet.getSheetName());
	        }
            int lastRow = sheet.getLastRowNum();
           
	     if(sheet.getSheetName().equalsIgnoreCase(worksheet))
	     {
	    	 for(int i=0; i<=lastRow; i++)
	    	 {
	    		   
	    		   Row row = sheet.getRow(i);
	    		   Cell valueCell = (Cell) row.getCell(1);
	    		   Cell keyCell = (Cell) row.getCell(0);
	    		   
	    		   String value = ((org.apache.poi.ss.usermodel.Cell) valueCell).getStringCellValue().trim();
	    		   String key = ((org.apache.poi.ss.usermodel.Cell) keyCell).getStringCellValue().trim();
	    		   
	    		   //Putting key & value in dataMap
	    		   dataMap.put(key, value);
	    		   excelFileMap.put(worksheet, dataMap);
	    		   
	    	 }
	     }
	        
	       
	       

	}

}
