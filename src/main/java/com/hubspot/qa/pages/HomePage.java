package com.hubspot.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hubspot.qa.util.Constants;

public class HomePage extends BasePage{
	
	
//	@FindBy(xpath = "//h1[text()='Sales Dashboard']")
//	WebElement homePageHeader;
	
//	@FindBy(xpath = "//span[@class='account-name']")
//	WebElement accountName;
	
	@FindBy(id = "nav-primary-contacts-branch")
	WebElement contactsTabLink;
	
	@FindBy(id = "nav-secondary-contacts")
	WebElement contactsLink;
	
	By homePageHeader = By.xpath("//h1[text()='Sales Dashboard']");
	By accountName = By.xpath("//span[contains(@class,'account-name')]");
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getHomePageTitle(){
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.titleIs(Constants.HOME_PAGE_TITLE));
		return driver.getTitle();
	}
	
	public String checkHomePageHeader(){
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.presenceOfElementLocated(homePageHeader));
		
		if(driver.findElement(homePageHeader).isDisplayed()){
			return driver.findElement(homePageHeader).getText();
		}
		return null;
		
	}
	
	public String getLoggedInAccountName(){
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(accountName));
		return driver.findElement(accountName).getText();
	}
	
	public void clickContacts(){
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(contactsTabLink));
		contactsTabLink.click();
		
		wait.until(ExpectedConditions.visibilityOf(contactsLink));
		contactsLink.click();
	}
	
	public ContactsPage goToContactsPage(){
		clickContacts();
		return new ContactsPage(driver);
	}

}
