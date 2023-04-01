package com.tutorialsNinja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LogInPage {
	
	WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy (id = "input-password")
	private WebElement passwordField;
	
	@FindBy (xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordMissmatchWarning;
	
	public LogInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterEmail(String email) {
		emailAddressField.clear();
		emailAddressField.sendKeys(email);
	}
	public void enterPassword(String password) {
		passwordField.clear();
		passwordField.sendKeys(password);
	}
	public AccountPage clickOnLogInButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	public String getEmailPasswordMissmatchWarningMsg() {
		return emailPasswordMissmatchWarning.getText();
	}
	
	public AccountPage logInAction(String email,String password) {
		emailAddressField.clear();
		emailAddressField.sendKeys(email);
		passwordField.clear();
		passwordField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
	}
	

}
