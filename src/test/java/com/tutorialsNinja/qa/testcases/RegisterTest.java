package com.tutorialsNinja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pageObjects.AccountSuccessPage;
import com.tutorialsNinja.qa.pageObjects.HomePage;
import com.tutorialsNinja.qa.pageObjects.RegisterPage;
import com.tutorialsNinja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage register;
	AccountSuccessPage accSuccPage;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		register = homepage.navigateToRegisterPage();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisterWithMandataryFeilds() {

		accSuccPage = register.registerWithMandatoryFeilds(propData.getProperty("firstName"),
				propData.getProperty("lastName"), Utilities.generateWirhTimeStamp(), propData.getProperty("telephone"),
				propData.getProperty("password"));
		String sucMsg = accSuccPage.getSuccessMsgText();
		Assert.assertEquals(sucMsg, propData.getProperty("accoutnCreateSucMsg"),
				propData.getProperty("accoutnCreateSucMsgAssertText"));

	}

	@Test(priority = 2)
	public void verifyRegisterWithAllFeilds() {
		accSuccPage = register.registerWithAllFeilds(propData.getProperty("firstName"),
				propData.getProperty("lastName"), Utilities.generateWirhTimeStamp(), propData.getProperty("telephone"),
				propData.getProperty("password"));
		String sucMsg = accSuccPage.getSuccessMsgText();
		Assert.assertEquals(sucMsg, propData.getProperty("accoutnCreateSucMsg"),
				propData.getProperty("accoutnCreateSucMsgAssertText"));

	}

	@Test(priority = 3)
	public void verifyAccountWithExistingEmail() {
		register.registerWithMandatoryFeilds(propData.getProperty("firstName"), propData.getProperty("lastName"),
				prop.getProperty("validEmail"), propData.getProperty("telephone"), propData.getProperty("password"));
		String sucMsg = register.getDuplicateEmailWarningMsgText();
		Assert.assertEquals(sucMsg, propData.getProperty("warningMsgAlreadyExitEmail"),
				propData.getProperty("warningMsgAlreadyExitEmailAssertText"));

	}

	@Test(priority = 4)
	public void verifyAccountWithOutFillingDetails() {

		register.clickOnContinue();

		Assert.assertEquals(register.getPrivacyPolicyWarningMsggText(), propData.getProperty("privacyPolicyMsg"),
				propData.getProperty("privacyPolicyMsgAssertText"));

		Assert.assertTrue(register.getFirstNameWarningMsgText().contains(propData.getProperty("firstNameMsg")),
				propData.getProperty("firstNameMsgAssertText"));

		String lastnameWarning = register.getLastNameWarningMsgText();
		Assert.assertTrue(lastnameWarning.contains(propData.getProperty("lastNameMsg")),
				propData.getProperty("lastNameMsgAssertText"));

		Assert.assertTrue(register.getEmailWarningMsgText().contains(propData.getProperty("emailMsg")),
				propData.getProperty("emailMsgAssertText"));

		Assert.assertTrue(register.getTelephoneWarningMsgText().contains(propData.getProperty("telephoneMsg")),
				propData.getProperty("telephoneMsgAssertText"));

		Assert.assertTrue(register.getPasswordWarningMsgText().contains(propData.getProperty("passwordMsg")),
				propData.getProperty("passwordMsgAssertText"));
		
	}

}
