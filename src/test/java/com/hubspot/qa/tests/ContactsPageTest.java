package com.hubspot.qa.tests;

import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hubspot.qa.pages.BasePage;
import com.hubspot.qa.pages.ContactsPage;
import com.hubspot.qa.pages.HomePage;
import com.hubspot.qa.pages.LoginPage;
import com.hubspot.qa.util.TestUtil;

public class ContactsPageTest implements ITest {

	public WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public ContactsPage contactsPage;

	private ThreadLocal<String> testName = new ThreadLocal<>();

	@BeforeMethod
	public void setUp(Method method, Object[] testData) {
		testName.set(method.getName() + "_" + testData[0]);
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init();
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	}

	@Override
	public String getTestName() {
		return testName.get();
	}

	@DataProvider(name = "getContactsTestData")
	public Object[][] getContactsTestData() {
		return TestUtil.getTestData("contact");
	}

	@Test(dataProvider = "getContactsTestData")
	public void createContactTest(String email, String firstName, String lastName, String jobTitle) {
		contactsPage.createNewContact(email, firstName, lastName, jobTitle);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
