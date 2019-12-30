package com.selenium.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


import com.google.common.io.Files;

public class BaseClass implements ITestListener{

	
	public static WebDriver driver = null;
	public static Logger logger = Logger.getLogger("BaseClass");
	
	public static Map<String,String> locatorMap = new HashMap<String,String>();
	public static Map<String,String> testDataMap = new HashMap<String,String>();
	
	//public static Map<String,Object> hm = new HashMap<String,Object>();
	
	
	public static void writeLogs(String msg)
	{
		/*
		String s = "//input[@id='username']";
		
		Integer x = 20;
		
		hm.put("LoginUsername",x);
		
		String s1 = (String)hm.get("LoginUsername");
		
		driver.findElement(By.xpath(s1)).click();
		*/
		logger.info(msg);
		
	}
	
	public static void writeErrorLogs(Throwable t)
	{
		String s = Arrays.toString(t.getStackTrace());		
		String s1 = s.replaceAll(",", "\n");		
		logger.error(s1);
		
	}
	
	
	public static void launchBrowser(String url)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/utilities/chromedriver.exe");
		
		driver = new ChromeDriver();		
		
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public static void launchBrowser() throws IOException
	{
		writeLogs(" This method will run before every @Test ");
		String url = getConfigData("url");
		
		String browser = getConfigData("browser");
		
		if (browser.equalsIgnoreCase("chrome"))
		{
		
			System.setProperty("webdriver.chrome.driver", "./src/test/utilities/chromedriver.exe");		
			driver = new ChromeDriver();
		
		}
		
		else if (browser.equalsIgnoreCase("firefox"))
		{
		
			System.setProperty("webdriver.gecko.driver", "./src/test/utilities/geckodriver.exe");		
			driver = new FirefoxDriver();
		
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		
		driver.get(url);
	}
	
	// Adding a comment to test pollings
	
	@AfterMethod(alwaysRun=true)
	public static void closeBrowser()
	{
		writeLogs(" This method will run after every @Test ");
		
		driver.close();
	}
	
	// Adding another comment to test SCM Polling
	public static void captureScreenshot(String fileName)
	
	{		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("./src/test/results/"+fileName+".png");
			
		try {
			Files.copy(src, dest);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
	}
	
	public static String getConfigData(String key) throws IOException
	{
		String value = "";
		
		File f = new File("./src/test/data/config.properties");
		FileInputStream fis = new FileInputStream(f);
		
		Properties prop = new Properties();
		prop.load(fis);
		
		
		value = prop.getProperty(key);
		
		
		return value;	
		
	}
	
	public static String getLocatorData(String pageName, String elementName) throws IOException
	{
		String locator = "";
		String pageData ="";
		File f = new File("./src/test/data/locatordata.xlsx");		
		FileInputStream fio = new FileInputStream(f);
		
		// Creating the Object of the WorkBook
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		
		//Getting the work sheet
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		//To fetch the number of used rows in the current sheet
		int rows = ws.getLastRowNum();
		
		System.out.println(rows);
		
		for (int i=1;i<=rows;i++)
		{
			String page = ws.getRow(i).getCell(0).getStringCellValue();
			String element = ws.getRow(i).getCell(1).getStringCellValue();
			
			System.out.println(page  + "======="+element );
			
			/*
			if(pageName.equalsIgnoreCase(page) && elementName.equalsIgnoreCase(element))
			{
				 locator = ws.getRow(i).getCell(2).getStringCellValue();
				 pageData = ws.getRow(i).getCell(1).getStringCellValue();
				 
				 break;			
				 
			}	
			*/				
		}		
		wb.close();		
		return locator;
	}
	
	

	public static String getTestData(String pageName, String elementName) throws IOException
	{
		String data = "";		
		File f = new File("./src/test/data/testdata.xlsx");		
		FileInputStream fio = new FileInputStream(f);
		
		// Creating the Object of the WorkBook
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		
		//Getting the work sheet
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		//To fetch the number of used rows in the current sheet
		int rows = ws.getLastRowNum();		
		for (int i=1;i<=rows;i++)
		{
			String page = ws.getRow(i).getCell(0).getStringCellValue();
			String element = ws.getRow(i).getCell(1).getStringCellValue();
			
			if(pageName.equalsIgnoreCase(page) && elementName.equalsIgnoreCase(element))
			{
				data = ws.getRow(i).getCell(2).getStringCellValue();
				 break;			}					
		}		
		wb.close();		
		return data;
	}
	
	
	
	public static void enterTextInTextBox(WebElement element, String text)
	{
		element.sendKeys(text);
		
	}
	
	public static void clickWebElement(WebElement element)
	{
		element.click();
	}
	
	
	public static void writeResultsToFile(String testCaseName, String testCaseResult) throws IOException	
	{
		File f = new File("./src/test/results/results.txt");
		
		FileWriter fw = new FileWriter(f,true);
		
		fw.write(testCaseName+"---"+testCaseResult+"\r\n");
		
		fw.flush();
		fw.close();
		
		
	}
	
	@BeforeTest
	public static void beforeTest()
	{
		
		writeLogs(" This method will run before every testngxml Test");
	}
	
	
	@AfterTest
	public static void afterTest()
	{
		writeLogs(" This method will run after every testngxml  test ");
	}
	
	@BeforeClass
	public static void beforeClass()
	{
		writeLogs(" This method will run before every class");
	}
	
	@AfterClass
	public static void afterClass()
	{
		writeLogs(" This method will run after every class");
	}
	
	

	//***************TestNG Listerner methods**********************
	
	
	public void onFinish(ITestContext arg0) {
		writeLogs("****** This method will run after the suite*****");
		
	}


	
	public void onStart(ITestContext arg0) {
	
		writeLogs("****** This method will run before the suite*****");
		try {
			getAndStoreLocatorData();
			getAndStoreTestData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}


	
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	
		
	}


	
	public void onTestFailure(ITestResult arg0) {
		try {
			writeLogs("The test case by name "+arg0.getName()+" is getting completed");
			writeResultsToFile(arg0.getName(), "Failed!!");
			captureScreenshot(arg0.getName());
			writeErrorLogs(arg0.getThrowable());
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
	}


	
	public void onTestSkipped(ITestResult arg0) {
	
		
	}


	
	public void onTestStart(ITestResult arg0) {
		writeLogs("The test case by name "+arg0.getName()+" is getting started");
		
	}


	
	public void onTestSuccess(ITestResult arg0) {
		writeLogs("The test case by name "+arg0.getName()+" is Pass");
		try {
			writeResultsToFile(arg0.getName(), "Pass");
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void getAndStoreLocatorData() throws IOException {
		String xpath = "";

		File f = new File("./src/test/data/locatordata.xlsx");
		FileInputStream fio = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		XSSFSheet ws = wb.getSheet("Sheet1");

		int rows = ws.getLastRowNum();		

		for (int x = 1; x <= rows; x++) {			
			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();	
			xpath = ws.getRow(x).getCell(2).getStringCellValue();
			locatorMap.put(page+"$"+element, xpath);
				
			}	
		writeLogs("Locator hash Map ===" + locatorMap);

		wb.close();
		
	}
	
	public static void getAndStoreTestData() throws IOException {
		String data = "";

		File f = new File("./src/test/data/testdata.xlsx");
		FileInputStream fio = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		XSSFSheet ws = wb.getSheet("Sheet1");

		int rows = ws.getLastRowNum();	

		for (int x = 1; x <= rows; x++) {

			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();			
			data = ws.getRow(x).getCell(2).getStringCellValue();
			testDataMap.put(page+"$"+element, data);
			

		}
		
		writeLogs("TestData hash Map ===" + testDataMap);

		wb.close();
		
	}
	
	


}
