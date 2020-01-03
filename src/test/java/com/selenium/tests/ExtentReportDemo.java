package com.selenium.tests;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportDemo {

	public static void main(String[] args) {
		
		
		System.out.println(" Starting the Report Demo");
		
		File f = new File("./src/test/results/ereport.html");
		
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(f);
		
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		
		ExtentTest logger = extent.createTest("SampleExtentReportTest");
		
		logger.log(Status.INFO, " Starting to Log in to Extent Report");
			
		logger.log(Status.INFO, " Starting to Log second to Extent Report");
		
		logger.log(Status.PASS, "The Test Case is Pass");
		
		extent.flush();
		
		
	}

}
