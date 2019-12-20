package com.selenium.library;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.base.BaseClass;

public class CommonLibrary extends BaseClass{
	
		
	public static boolean launchLoginToActiTime(String username, String password) throws Exception
	
	{
		//driver.findElement(By.xpath(getLocatorData("Login", "UserName_EditBox"))).sendKeys(username);
		
		driver.findElement(By.xpath(locatorMap.get("Login$UserName_EditBox"))).sendKeys(username);
		
		driver.findElement(By.xpath(locatorMap.get("Login$Password_EditBox"))).sendKeys(password);
		
		driver.findElement(By.xpath(locatorMap.get("Login$Ok_Button"))).click();
				
		WebElement logoutLink =	driver.findElement(By.xpath(locatorMap.get("Home$Logout_Link")));
			
		boolean result = logoutLink.isDisplayed();
			
		return result;
		
	}
	
	
	
	

	public static void selectModule(String moduleName) throws IOException
	{
		String module = getLocatorData("Home", "TabName_Menu");		
		String[] modules = module.split("#");		
		String moduleXpath = modules[1].replace("--TEXTTOREPLACE--", moduleName);		
		driver.findElement(By.xpath(moduleXpath)).click();
		
	}
	

}
