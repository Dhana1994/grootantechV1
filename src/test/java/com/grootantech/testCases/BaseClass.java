package com.grootantech.testCases;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.grootantech.pageObjects.HomePage;
import com.grootantech.utilities.WriteExcel;

public class BaseClass {

	public String baseURL = "https://www.grootan.com/";
	public static WebDriver driver;

	@BeforeTest
	public void setup() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\Workspace1\\grootantechV1\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		WriteExcel excel = new WriteExcel();
		excel.createSheet();

		new WriteExcel()
				.addDataToReportSummary(new Object[] { "Test Step No.", "Action", "Expected Output", "Actual Output" });
	}

	@AfterTest
	public void teardown() throws Exception {
		new WriteExcel().writeReport();
		driver.close();
	}

	public void captureScreen(WebDriver driver, String tname, String folderName) throws IOException, Exception {


		// WebElement widget =
		// driver.findElement(By.xpath("//div[@class='drift-conductor-item
		// drift-frame-chat drift-frame-chat-align-right drift-chat-open
		// with-transition']"));
		try {
			WebElement widget = driver.findElement(By.xpath("/html/body/div[2]"));

			System.out.println(widget.getAttribute("class"));
			if (widget.getAttribute("class").contains("drift-chat-open"))
			// if(driver.findElement(By.xpath("//div[@class='drift-conductor-item
			// drift-frame-chat drift-frame-chat-align-right drift-chat-open
			// with-transition']")).isDisplayed())
			{
				System.out.println("Widget Displayed");
				driver.switchTo().frame(1);
				driver.findElement(By.xpath("//*[@id=\"root\"]/main/button")).click();
				driver.switchTo().defaultContent();
			}
			System.out.println("Widgets closed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/" + folderName + "/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");

	}

	public void clickAction(WebDriver driver, String folderName) throws IOException, Exception {
		HomePage hp = new HomePage(driver);

		hp.ClickHome();
		captureScreen(driver, "Home", folderName);

		hp.ClickServices();
		captureScreen(driver, "Services", folderName);

		hp.ClickOpenSource();
		Thread.sleep(5000);
		captureScreen(driver, "OpenSource", folderName);

		hp.ClickBlog();
		captureScreen(driver, "Blog", folderName);

		hp.ClickTeam();
		captureScreen(driver, "Team", folderName);

		hp.ClickCareers();
		captureScreen(driver, "Careers", folderName);

		hp.ClickContactUs();
		Thread.sleep(5000);
		captureScreen(driver, "ContactUs", folderName);

	}
}
