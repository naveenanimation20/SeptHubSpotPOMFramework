package com.hubspot.qa.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hubspot.qa.pages.BasePage;
import com.hubspot.qa.pages.HomePage;
import com.hubspot.qa.pages.LoginPage;
import com.hubspot.qa.util.Constants;

public class HomePageTest {

	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;

	@BeforeMethod
	public void setUp() {

		basePage = new BasePage();
		driver = basePage.init();
		prop = basePage.init_properties();
		loginPage = new LoginPage(driver);
		driver.get(prop.getProperty("url"));
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=1)
	public void homePageHeaderTest(){
		Assert.assertEquals(homePage.checkHomePageHeader(), Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=2)
	public void homePageTitleTest(){
		String title = homePage.getHomePageTitle();
		System.out.println("home page title is: "+ title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=3)
	public void accountNameTest(){
		String accountName = homePage.getLoggedInAccountName();
		System.out.println("Logged in User Account Name is :"+ accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}
	

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
