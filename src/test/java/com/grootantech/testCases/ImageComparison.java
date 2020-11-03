package com.grootantech.testCases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

	@Test
	public void compareImage() throws Exception {
		driver.get("https://www.grootan.com/team");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@id='root']/div/footer/div[1]/div/div[1]/button")).click();

		WebElement CTOImageElement = driver
				.findElement(By.xpath("//div[@id='root']/div/section[2]//div[1]/div[1]/img"));
		Screenshot CTOImageScreenshot = new AShot().takeScreenshot(driver, CTOImageElement);

		ImageIO.write(CTOImageScreenshot.getImage(), "png",
				new File("D://Workspace1//grootantechV1//Images//CTO&Co-Founder.png"));

		WebElement HRImageElement = driver.findElement(By.xpath("//div[@id='root']//section[2]//div[1]/div[2]/img"));
		Screenshot HRImageScreenshot = new AShot().takeScreenshot(driver, HRImageElement);
		ImageIO.write(HRImageScreenshot.getImage(), "png",
				new File("D://Workspace1//grootantechV1//Images//HR-Manager.png"));

		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(CTOImageScreenshot, HRImageScreenshot);

		if (diff.hasDiff()) {
			System.out.println("Images are Not Same");
		} else {
			System.out.println("Images are Same");
		}

		Assert.assertFalse(diff.hasDiff(), "Result of Image comparsion");
		System.out.println("Images Compared Sucesfully");

	}

	@Test
	public void getDesignationReport() throws Exception {

		List<String> name=new ArrayList<String>();
		List<WebElement> rows = driver
				.findElements(By.xpath("//div[@id='root']/div/section[2]/div/div/div/div/div/div"));
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> columns = rows.get(i).findElements(By.cssSelector("div"));
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
		WriteExcel we = new WriteExcel();
		we.writeExcel("D:\\Workspace1\\grootantechV1","TSR.xlsx","JuniorEngineers",name.toArray(new String[name.size()]));
	}

}
