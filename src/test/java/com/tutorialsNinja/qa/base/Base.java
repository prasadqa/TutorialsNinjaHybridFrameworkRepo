package com.tutorialsNinja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsNinja.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties propData;

	public Base() {

		prop = new Properties();
		File file = new File("/Users/challaprasad/Documents/Java-Selenium/tutorialsNinja/src/main/java/com/tutorialsNinja/qa/config/config.properties");

		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		propData = new Properties();
		File fileData = new File("/Users/challaprasad/Documents/Java-Selenium/tutorialsNinja/src/main/java/com/tutorialsNinja/qa/testdata/testdata.properties");

		FileInputStream fisData;
		try {
			fisData = new FileInputStream(fileData);
			propData.load(fisData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	

	}

	public WebDriver initializeBrowserAndOpenURL(String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {

			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {

			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_WAIT_TIME));
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));

		return driver;
	}

}
