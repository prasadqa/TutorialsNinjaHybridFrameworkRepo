package com.tutorialsNinja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	//Objects
    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropDown;
	
    @FindBy(linkText = "Login")
    private WebElement login;
    
    @FindBy (linkText = "Register")
    private WebElement registerButton;
    
    @FindBy (xpath = "//div[@class='input-group']/input")
    private WebElement searchTextBox;
    
    @FindBy (xpath = "//div[@id='search']/descendant::button")
    private WebElement searchIcon;
    
   
    
    
	
	public HomePage(WebDriver driver) {
	
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions
	public void clickOnMyAccount() {
		myAccountDropDown.click();
	}
	public LogInPage clickOnLogin() {
		login.click();
		return new LogInPage(driver);
	}
	public RegisterPage clickOnRegisterButton()
	{
		registerButton.click();
		return new RegisterPage(driver);
	}
	
	public void enterTextSearchBox(String item) {
		
		searchTextBox.clear();
		searchTextBox.sendKeys(item);
		
	}
	public SearchResultPage clickOnsearchIcon() {
		
		searchIcon.click();
		return new SearchResultPage(driver);
		
	}
	public RegisterPage navigateToRegisterPage()
	{
		myAccountDropDown.click();
		registerButton.click();
		return new RegisterPage(driver);
	}
}

