package com.tutorialsNinja.qa.testcases;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;


import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pageObjects.AccountPage;
import com.tutorialsNinja.qa.pageObjects.HomePage;
import com.tutorialsNinja.qa.pageObjects.LogInPage;
import com.tutorialsNinja.qa.utils.Utilities;

public class LoginTest extends Base {

	public WebDriver driver;
	LogInPage login;
	AccountPage accountPage;
	
	public LoginTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		login = homePage.clickOnLogin();
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider(name = "validDataSuppliers")
	public Object[][] dataSupply() {
		
		Object[][] data = Utilities.getDataFromExcel("Login");          ////{{"amotooricap9@gmail.com","12345"},{"amotooricap1@gmail.com","12345"},{"amotooricap3@gmail.com","12345"}};
		return data;
		
	}

	@Test(priority = 0,dataProvider = "validDataSuppliers" )
	public void varifyLoginWithValidCredentialsTest(String email,String password) {
		AccountPage accountPage = login.logInAction(email,password);
		Assert.assertTrue(accountPage.getEditInformationAccountPage());

	}

	@Test(priority = 1)
	public void varifyLoginWithInValidCredentialsTest() {
		login.logInAction(Utilities.generateWirhTimeStamp(), propData.getProperty("invalidPassword"));
		String warningsMsg = login.getEmailPasswordMissmatchWarningMsg();
		String expectedWarningMsg = propData.getProperty("accountWaring");
		Assert.assertTrue(warningsMsg.contains(expectedWarningMsg), propData.getProperty("expMassageInAssertText"));

	}

	@Test(priority = 2)
	public void varifyLoginWithValidEmailandInValidPassword() {
		
		login.logInAction(prop.getProperty("validEmail"), propData.getProperty("invalidPassword"));
		String warningsMsg = login.getEmailPasswordMissmatchWarningMsg();
		String expectedWarningMsg = propData.getProperty("accountWaring");
		Assert.assertTrue(warningsMsg.contains(expectedWarningMsg), propData.getProperty("expMassageInAssertText"));

	}

	@Test(priority = 3)
	public void varifyLoginWithInValidEmailandValidPassword() {
		
		login.logInAction(Utilities.generateWirhTimeStamp(), prop.getProperty("validPassword"));
		String warningsMsg = login.getEmailPasswordMissmatchWarningMsg();
		String expectedWarningMsg = propData.getProperty("accountWaring");
		Assert.assertTrue(warningsMsg.contains(expectedWarningMsg), propData.getProperty("expMassageInAssertText"));
		

	}

	@Test(priority = 4,dependsOnMethods = {"varifyLoginWithInValidEmailandValidPassword"})
	public void varifyLoginWithoutCredentials() {
		login.logInAction("","");
		String warningsMsg = login.getEmailPasswordMissmatchWarningMsg();
		String expectedWarningMsg = propData.getProperty("accountWaring");
		Assert.assertTrue(warningsMsg.contains(expectedWarningMsg), propData.getProperty("expMassageInAssertText"));

	}

}
