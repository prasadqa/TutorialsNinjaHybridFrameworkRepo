package com.tutorialsNinja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	
	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement productHPresult; 
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductText; 
	
	public SearchResultPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	

	public boolean isProductHPresultDisplayed() {
	
		return productHPresult.isDisplayed();
		
	}
	
	public String getNoProductText() {
		
		return noProductText.getText();
		
	}

}
