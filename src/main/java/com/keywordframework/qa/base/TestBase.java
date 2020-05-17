package com.keywordframework.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public WebDriver driver;
	public Properties prop;

	public WebDriver init_Driver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().version("2.40").setup();
			if (prop.getProperty("headless").equals("yes")) {
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--headless");
				driver = new ChromeDriver(opt);

			} else {
				driver = new ChromeDriver();

			}
		}
		return driver;

	}
	
	public Properties init_properties()
	{
		prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream("F:\\WorkSpace\\TESTProject\\KeyWordDrivenFramework\\src\\main"
					+ "\\java\\com\\keywordframework\\qa\\config\\config.properties");
			
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
	}

}
