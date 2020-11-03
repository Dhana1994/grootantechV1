package com.grootantech.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.grootantech.pageObjects.HomePage;

public class BaseClass {


		public String baseURL ="https://www.grootan.com/";
		public static WebDriver driver;
		
		@BeforeTest
		public void setup()
		{
			System.setProperty("webdriver.chrome.driver","D:\\Workspace1\\grootantechV1\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			
			driver.manage().window().maximize();
				
		}
		
		@AfterTest
		public void teardown()
		{
			driver.close();
		}
		
		public void captureScreen(WebDriver driver, String tname , String folderName) throws IOException 
		{
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File(System.getProperty("user.dir") + "/" + folderName + "/" + tname + ".png");
			FileUtils.copyFile(source, target);
			System.out.println("Screenshot taken");

	    }
		
		public void clickAction(WebDriver driver, String folderName) throws IOException {
			HomePage hp = new HomePage(driver);

			hp.ClickHome();
			captureScreen(driver, "Home", folderName);

			hp.ClickServices();
			captureScreen(driver, "Services", folderName);

			hp.ClickOpenSource();
			captureScreen(driver, "OpenSource", folderName);

			hp.ClickBlog();
			captureScreen(driver, "Blog",folderName);

			hp.ClickTeam();
			captureScreen(driver, "Team", folderName);

			hp.ClickCareers();
			captureScreen(driver, "Careers", folderName);

			hp.ClickContactUs();
			captureScreen(driver, "ContactUs",folderName);

		}
	}
		
		
		
	


