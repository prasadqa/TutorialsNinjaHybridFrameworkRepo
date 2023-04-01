package com.tutorialsNinja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class MyExtentReports {
	
	
	public static ExtentReports generateExtentReport() {
		ExtentReports extentReports = new ExtentReports();
		File sparkReportFile = new File(System.getProperty("user.dir")+ "/test-output/ExtentReport/ExtentReport.html");
		
		ExtentSparkReporter sparkrepoter = new ExtentSparkReporter(sparkReportFile);
		
		sparkrepoter.config().setTheme(Theme.DARK);
		sparkrepoter.config().setReportName("Tutorial ninja test automation reporter");
		sparkrepoter.config().setDocumentTitle("TN test results");
		sparkrepoter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		Properties prop = new Properties();
		File propFile = new File("/Users/challaprasad/Documents/Java-Selenium/tutorialsNinja/src/main/java/com/tutorialsNinja/qa/config/config.properties");
		FileInputStream propfileInput;
		try {
			propfileInput = new FileInputStream(propFile);
			prop.load(propfileInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		extentReports.attachReporter(sparkrepoter);
		extentReports.setSystemInfo("Application URL", prop.getProperty("URL"));
		extentReports.setSystemInfo("Browser name", prop.getProperty("browser"));
		extentReports.setSystemInfo("validEmail", prop.getProperty("validEmail"));
		extentReports.setSystemInfo("Valid Password", prop.getProperty("validPassword"));
		extentReports.setSystemInfo("OS name", System.getProperty("os.name"));
		extentReports.setSystemInfo("OS version", System.getProperty("os.version"));
		extentReports.setSystemInfo("user name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Java version", System.getProperty("java.version"));
		
		return extentReports;
		
	}

}
