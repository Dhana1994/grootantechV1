package com.grootantech.testCases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.grootantech.utilities.WriteExcel;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ImageComparison extends BaseClass {

	@Test(description = "Image Comparison of CTO & HR", priority = 3)
	public void compareImage() throws Exception {

		WriteExcel we = new WriteExcel();

		try {
			driver.get("https://www.grootan.com/team");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


			WebElement CTOImageElement = driver
					.findElement(By.xpath("//div[@id='root']/div/section[2]//div[1]/div[1]/img"));
			Screenshot CTOImageScreenshot = new AShot().takeScreenshot(driver, CTOImageElement);

			ImageIO.write(CTOImageScreenshot.getImage(), "png",
					new File("D://Workspace1//grootantechV1//Images//CTO&Co-Founder.png"));

			WebElement HRImageElement = driver
					.findElement(By.xpath("//div[@id='root']//section[2]//div[1]/div[2]/img"));
			Screenshot HRImageScreenshot = new AShot().takeScreenshot(driver, HRImageElement);
			ImageIO.write(HRImageScreenshot.getImage(), "png",
					new File("D://Workspace1//grootantechV1//Images//HR-Manager.png"));

			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(CTOImageScreenshot, HRImageScreenshot);

			if (diff.hasDiff()) {
				System.out.println("Images are Not Same");
				throw new Exception("Images are Not Same");
			} else {
				System.out.println("Images are Same");
			}

			System.out.println("Images Compared Sucesfully");
			we.addDataToReportSummary(new Object[] {"Image Comparison", "Image Compared", "Pass" });
		} catch (Exception e) {
			e.printStackTrace();
			we.addDataToReportSummary(new Object[] {"Image Comparison", "Image Compared", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Writing Junior Engineer Names in Excel", priority = 4)
	public void getDesignationReport() throws Exception {

		WriteExcel we = new WriteExcel();
		try {

			List<String> name = new ArrayList<String>();
			List<WebElement> rows = driver
					.findElements(By.xpath("//div[@id='root']/div/section[2]/div/div/div/div/div/div"));
			for (WebElement row : rows) {
				List<WebElement> columns = row.findElements(By.cssSelector("div"));
				System.out.println(columns.size());

				for (WebElement column : columns) {
					try {
						WebElement jobTitle = column.findElement(By.cssSelector("h5"));
						if (jobTitle.getText().equalsIgnoreCase("Junior Engineer")) {
							WebElement nameElement = column.findElement(By.cssSelector("h3"));
							System.out.println(nameElement.getText());
							name.add(nameElement.getText());

						}
					} catch (Exception e) {
						System.out.println("Element not present");
					}
				}

			}
			System.out.println(name.size());
			
			we.writeJuniorEngineers(name);

			we.addDataToReportSummary(new Object[] {"Junior Engineer Names", "Written", "Pass" });
		} catch (Exception e) {
			we.addDataToReportSummary(new Object[] {"Junior Engineer Names", "Written", "Fail" });
			Assert.assertTrue(false);
		}
	}
}
