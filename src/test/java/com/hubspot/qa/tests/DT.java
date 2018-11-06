package com.hubspot.qa.tests;

import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DT implements ITest {

	private ThreadLocal<String> testName = new ThreadLocal<>();

	@DataProvider(name = "role")
	public static Object[][] roles() {
		return new Object[][] { { "Developer" }, { "Team Lead" }, { "QA" }, { "Business Analyst" }, { "DevOps Eng" },
				{ "PMO" } };
	}

	// 1. Verify create staff invitations (Doctor) with valid authToken and
	// parameters (Positive scenario)
	@Test(dataProvider = "role")
	public void createUser(String role) {

	}

	@BeforeMethod
	public void BeforeMethod(Method method, Object[] testData) {
		testName.set(method.getName() + "_" + testData[0]);
	}

	@Override
	public String getTestName() {
		return testName.get();
	}

}
