package com.tutorialsNinja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pageObjects.HomePage;
import com.tutorialsNinja.qa.pageObjects.SearchResultPage;

public class SearchTest extends Base {
	
	public WebDriver driver;
	SearchResultPage searchRes;
	HomePage homePage;
	
	public SearchTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenURL(prop.getProperty("browser"));
		homePage = new HomePage(driver);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchingValidProduct() {
		
		homePage.enterTextSearchBox(propData.getProperty("productHP"));
		SearchResultPage searchRes =  homePage.clickOnsearchIcon();
		Assert.assertTrue(searchRes.isProductHPresultDisplayed(),"Product not displayed");
		
	}
	@Test(priority = 2)
	public void verifySearchingInValidProduct() {
		
		homePage.enterTextSearchBox(propData.getProperty("productHonda"));
		searchRes = homePage.clickOnsearchIcon();
//		Assert.assertEquals(searchRes.getNoProductText(),propData.getProperty("productNotFoudnWarningMsg"), propData.getProperty("productNotFoudnWarningMsgAssertText"));
		Assert.assertTrue(false);
	
	}
	
	@Test(priority = 3,dependsOnMethods = "verifySearchingInValidProduct")
	public void verifySearchingWithNoValue() {
		
		homePage.enterTextSearchBox("");
		searchRes = homePage.clickOnsearchIcon();
		Assert.assertEquals(searchRes.getNoProductText(),propData.getProperty("productNotFoudnWarningMsg"), propData.getProperty("productNotFoudnWarningMsgAssertText"));
		
	}

}
