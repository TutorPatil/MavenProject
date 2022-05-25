package com.selenium.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.base.BaseClass;
import com.selenium.library.CommonLibrary;

public class Login extends BaseClass{	
	

	@Test
	public static void login_001() throws Exception
	{				
		String userName = testDataMap.get("Login$UserName_EditBox");		
		String password = testDataMap.get("Login$Password_EditBox");
		
		boolean result = CommonLibrary.launchLoginToActiTime(userName, password);
		Assert.assertTrue(result, "fail" );
	
	}
	
	
	@Test
	public static void login_002() throws Exception
	{				
		String userName = testDataMap.get("Login$UserName_EditBox");		
		String password = testDataMap.get("Login$Password_EditBox");
		
		boolean result = CommonLibrary.launchLoginToActiTime(userName, password);
		Assert.assertTrue(result, "fail" );
	
	}
	

	

}
