package com.tutorialsNinja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameText;

	@FindBy(id = "input-lastname")
	private WebElement lastNameText;

	@FindBy(id = "input-email")
	private WebElement emailText;

	@FindBy(id = "input-telephone")
	private WebElement phoneNumberText;

	@FindBy(id = "input-password")
	private WebElement passwordText;

	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmText;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement privacyCheckBox;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement subscribeCheckBox;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarningMsg;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarningMsg;

	@FindBy(xpath = "//input[@id='input-firstname'] /following-sibling::div")
	private WebElement firstNameWarningMsg;

	@FindBy(xpath = "//input[@id='input-lastname'] /following-sibling::div")
	private WebElement lastNameWarningMsg;

	@FindBy(xpath = "//input[@id='input-email'] /following-sibling::div")
	private WebElement emailWarningMsg;

	@FindBy(xpath = "//input[@id='input-telephone'] /following-sibling::div")
	private WebElement telephoneWarningMsg;

	@FindBy(xpath = "//input[@id='input-password'] /following-sibling::div")
	private WebElement passwordWarningMsg;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enterFirstName(String firstName) {

		firstNameText.clear();
		firstNameText.sendKeys(firstName);

	}

	public void enterLastName(String LastName) {

		lastNameText.clear();
		lastNameText.sendKeys(LastName);

	}

	public void enterEmail(String email) {

		emailText.clear();
		emailText.sendKeys(email);

	}

	public void enterPhoneNumber(String phoneNumber) {

		phoneNumberText.clear();
		phoneNumberText.sendKeys(phoneNumber);

	}

	public void enterPassword(String password) {

		passwordText.clear();
		passwordText.sendKeys(password);

	}

	public void enterConfirmPassword(String confirmPassword) {

		passwordConfirmText.clear();
		passwordConfirmText.sendKeys(confirmPassword);

	}

	public void clickOnPrivacyCheckBox() {

		privacyCheckBox.click();

	}

	public AccountSuccessPage clickOnContinue() {

		continueButton.click();
		return new AccountSuccessPage(driver);

	}

	public void clickOnSubscribeCheckBox() {

		subscribeCheckBox.click();

	}

	public String getDuplicateEmailWarningMsgText() {

		return duplicateEmailWarningMsg.getText();

	}

	public String getPrivacyPolicyWarningMsggText() {

		return privacyPolicyWarningMsg.getText();

	}

	public String getFirstNameWarningMsgText() {

		return firstNameWarningMsg.getText();

	}

	public String getLastNameWarningMsgText() {

		return lastNameWarningMsg.getText();

	}

	public String getEmailWarningMsgText() {

		return emailWarningMsg.getText();

	}

	public String getTelephoneWarningMsgText() {

		return telephoneWarningMsg.getText();

	}

	public String getPasswordWarningMsgText() {

		return passwordWarningMsg.getText();

	}
	
	public AccountSuccessPage registerWithMandatoryFeilds(String firstName,String lastName,String email,String phoneNumber,String password ) {
		
		firstNameText.clear();
		firstNameText.sendKeys(firstName);
		lastNameText.clear();
		lastNameText.sendKeys(lastName);
		emailText.clear();
		emailText.sendKeys(email);
		phoneNumberText.clear();
		phoneNumberText.sendKeys(phoneNumber);
		passwordText.clear();
		passwordText.sendKeys(password);
		passwordConfirmText.clear();
		passwordConfirmText.sendKeys(password);
		privacyCheckBox.click();
		
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
public AccountSuccessPage registerWithAllFeilds(String firstName,String lastName,String email,String phoneNumber,String password ) {
		
		firstNameText.clear();
		firstNameText.sendKeys(firstName);
		lastNameText.clear();
		lastNameText.sendKeys(lastName);
		emailText.clear();
		emailText.sendKeys(email);
		phoneNumberText.clear();
		phoneNumberText.sendKeys(phoneNumber);
		passwordText.clear();
		passwordText.sendKeys(password);
		passwordConfirmText.clear();
		passwordConfirmText.sendKeys(password);
		subscribeCheckBox.click();
		privacyCheckBox.click();
	
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}

}

