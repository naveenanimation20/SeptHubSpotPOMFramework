package com.hubspot.qa.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hubspot.qa.pages.BasePage;
import com.hubspot.qa.pages.LoginPage;
import com.hubspot.qa.util.Constants;

public class LoginTest {
	
	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	
	@BeforeMethod
	public void setUp(){
		
		basePage = new BasePage();
		driver = basePage.init();
		prop = basePage.init_properties();
		loginPage = new LoginPage(driver);
		driver.get(prop.getProperty("url"));
		
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void loginTest(){
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	

}
