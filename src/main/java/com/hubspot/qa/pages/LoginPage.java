package com.hubspot.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
	
	//1. WebElements -- in the form page factory
	//2. Actions -- Methods (Features)
	
	//@CacheLookup
	@FindBy(id = "username")
	WebElement userName;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "loginBtn")
	WebElement loginButton;
	
	//to initialize page objects
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getLoginPageTitle(){
		return driver.getTitle();
	}
	
	public HomePage login(String username, String pwd){
		userName.clear();
		userName.sendKeys(username);
		password.clear();
		password.sendKeys(pwd);
		loginButton.click();
		
		return new HomePage(driver);
	}
	
	public void loginLogoImageVerify(){
		System.out.println("loginLogoImageVerify");
	}

}
