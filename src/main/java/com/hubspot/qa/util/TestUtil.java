package com.hubspot.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.hubspot.qa.pages.BasePage;

public class TestUtil extends BasePage{

	public static Workbook book;
	public static Sheet sheet;

	public static String TESTDATA_SHEET_PATH = "/Users/NaveenKhunteta/Documents/workspace/SeptPOMSeries/src"
			+ "/main/java/com/hubspot/qa/testdata/HubSpotTestData.xlsx";

	public static Object[][] getTestData(String sheetName) {

		FileInputStream file = null;

		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}

		return data;

	}

	public void takeScreenshot() {
		String screenshot = "test";
		// 1. convert webdriver ref to TakeScreenshot:
		TakesScreenshot scrShot = (TakesScreenshot) driver;

		// 2. call getScreenshotAs method to create a file:
		File src = scrShot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("/Users/NaveenKhunteta/Documents/workspace/"
					+ "SeptPOMSeries/screenshots/"+screenshot+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
