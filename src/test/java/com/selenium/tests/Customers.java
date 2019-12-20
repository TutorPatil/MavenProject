package com.selenium.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.base.BaseClass;
import com.selenium.library.CommonLibrary;

public class Customers extends BaseClass{
	
	
	

	//@Test
	public static void createCustomer_001() throws Exception
	{
		
		String userName = getTestData("Login", "UserNameValid_EditBox");
		String password = getTestData("Login", "Password_EditBox");
		
		String customerName = "AutoCustomer_"+System.currentTimeMillis();
		
		//launchBrowser();		
		
		boolean result = CommonLibrary.launchLoginToActiTime(userName, password);
		
		// Creating new customer	
		
		CommonLibrary.selectModule("Tasks");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(getLocatorData("Tasks", "ProjectsAndCustomer_Link"))).click();
		
		driver.findElement(By.xpath(getLocatorData("Tasks", "CreateCustomer_Button"))).click();
		
		driver.findElement(By.xpath(getLocatorData("Customer", "CustomerName_EditBox"))).sendKeys(customerName);
				
		driver.findElement(By.xpath(getLocatorData("Customer", "CreateCustomer_Button"))).click();
				
		String successMessage = driver.findElement(By.xpath(getLocatorData("Customer", "SuccessMessage_Text"))).getText();
		
		boolean result1 = successMessage.contains(customerName);
		
		Assert.assertTrue(result1, "The customer cold not be created");
		 
				
		
	}
	

}
