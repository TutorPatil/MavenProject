package com.selenium.dataproviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestDataProvider {
	
	@DataProvider(name = "logindata")
	public static Object[][] dataProviderForLogin() throws IOException
	{
		//Object[][] obj = {{"admin","manager"},{"admin","manager"}};
		/*
		Object[][] obj = new String[3][2];
		
		obj[0][0] = "admin";
		obj[0][1] = "manager";
		obj[1][0] = "admin";
		obj[1][1] = "manager";
		obj[2][0] = "admin";
		obj[2][1] = "manager";
		
		return obj;
		
		*/
		
		Object[][] obj = readDataAndReturn("LoginData");
	
		return obj;
	}
	
	
	@DataProvider(name = "projectData")
	public static Object[][] dataProviderForProjects() throws IOException
	{		
		
		Object[][] obj = readDataAndReturn("ProjectsData");
	
		return obj;
	}

	
	public static Object[][] readDataAndReturn(String sheetName) throws IOException
	{
		
		File f = new File("./src/test/data/testdata.xlsx");		
		FileInputStream fio = new FileInputStream(f);
		
		// Creating the Object of the WorkBook
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		
		//Getting the work sheet
		XSSFSheet ws = wb.getSheet(sheetName);
		
		//To fetch the number of used rows in the current sheet
		int rows = ws.getLastRowNum();	
		int columns = ws.getRow(0).getLastCellNum();
		
		System.out.println("rows"+rows);
		System.out.println("columns"+columns);
		
		
		Object[][] obj = new String[rows +1][columns];
		
		for (int i=0;i<=rows;i++)
		{	
			for(int j=0;j<columns;j++)
			{
				
				obj[i][j] = ws.getRow(i).getCell(j).getStringCellValue();
			
			/*
			String username = ws.getRow(i).getCell(0).getStringCellValue();
			String password = ws.getRow(i).getCell(1).getStringCellValue();
			
			obj[i][0] = username;
			obj[i][1] = password;
			*/
			}
					
		}	
			wb.close();	
			
		return obj;	
		
		
	}
	
	
	
	
}
