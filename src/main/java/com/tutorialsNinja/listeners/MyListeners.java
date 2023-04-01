package com.tutorialsNinja.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsNinja.qa.utils.MyExtentReports;
import com.tutorialsNinja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		
		extentReports = MyExtentReports.generateExtentReport();
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+" started executing" );
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.PASS, result.getName()+" executed succeessfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(Utilities.captureScreenShot(driver, result.getName()));
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " got skiped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReports.flush();
		
		String pathOfTheReports = System.getProperty("user.dir")+"/test-output/ExtentReport/ExtentReport.html";
		File path = new File(pathOfTheReports);
		
		try {
			Desktop.getDesktop().browse(path.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
