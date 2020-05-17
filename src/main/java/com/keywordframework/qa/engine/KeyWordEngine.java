package com.keywordframework.qa.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.keywordframework.qa.base.TestBase;

public class KeyWordEngine {

	TestBase testbase;
	public WebDriver driver;
	public WebElement element;
	public Properties prop;

	public static Workbook wb;
	public static Sheet sheet;

	public final String SCENARIO_SHEET_PATH = "F:\\WorkSpace\\TESTProject\\KeyWordDrivenFramework\\src"
			+ "\\main\\java\\com\\keywordframework\\qa\\scenarios\\hubspot_scenarios.xlsx";

	public void startExecution(String SheetName) {

		FileInputStream fis = null;
		String locatorName = null;
		String locatorValue = null;

		try {
			fis = new FileInputStream(SCENARIO_SHEET_PATH);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet = wb.getSheet(SheetName);

		int k = 0;

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
			String locatorColvaule = sheet.getRow(i + 1).getCell(k + 1).toString().trim();

			if (!locatorColvaule.equalsIgnoreCase("NA")) {
				locatorName = locatorColvaule.split("=")[0].trim();
				locatorValue = locatorColvaule.split("=")[1].trim();
			}

			String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
			String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

			switch (action) {
			case "open browser":

				testbase = new TestBase();
				prop = testbase.init_properties();
				if (value.isEmpty() || value.equalsIgnoreCase("NA")) {

					driver = testbase.init_Driver(prop.getProperty("browser"));

				} else
					driver = testbase.init_Driver(value);

				break;

			case "enter url":
				if (value.isEmpty() || value.equalsIgnoreCase("NA")) {

					driver.get(prop.getProperty("url"));
					
					Thread.sleep(7000);
				} else
					driver.get(value);
				
				Thread.sleep(7000);
				
				break;

			case "quit":

				driver.quit();

				break;

			default:
				break;
			}

			switch (locatorName) {
			case "id":
				
			 element =driver.findElement(By.id(locatorValue));
			
			if(action.equalsIgnoreCase("sendkeys")) {
				element.clear();
				element.sendKeys(value);
				}
			else if(action.equalsIgnoreCase("click")) {
				element.click();
				}
			locatorName = null;
				break;
				
			case "linkText":
				
				element =driver.findElement(By.linkText(locatorValue));	
				element.click();
				locatorName = null;
				break;
			case "xpath":
				
				 element =driver.findElement(By.xpath(locatorValue));
				
				if(action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
					}
				else if(action.equalsIgnoreCase("click")) {
					element.click();
					}
				locatorName = null;
					break;
			default:
				break;
			}
		}
			catch(Exception e)
			{
				}
			}

	}

}
