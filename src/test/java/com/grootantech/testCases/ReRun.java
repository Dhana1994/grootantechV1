package com.grootantech.testCases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.grootantech.pageObjects.HomePage;
import com.grootantech.utilities.WriteExcel;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ReRun extends BaseClass {

	@Test(description = "Reruns Suite and Comapare screenshot with already captured screenshots", priority = 2)
	public void TC_ScreenshotCompare_2() throws Exception {
		WriteExcel we = new WriteExcel();
		try {
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			clickAction(driver, "Folder2");

			we.addDataToReportSummary(new Object[] { "Reruns Suite", "Screenshot Captured", "Pass" });
		} catch (Exception e) {
			we.addDataToReportSummary(new Object[] { "Reruns Suite", "Screenshot Captured", "Fail" });
			// Assert.assertTrue(false);
		}

		compareScreenshot();
	}

	public void compareScreenshot() throws Exception {
		File[] folderLists1 = new File("D:\\Workspace1\\grootantechV1\\Folder1").listFiles();
		File[] folderLists2 = new File("D:\\Workspace1\\grootantechV1\\Folder2").listFiles();

		for (int i = 0, row = 3; i < folderLists1.length; i++, row++) {
			new ScreenshotCompare().comparescreenshot(folderLists1[i], folderLists2[i], row);
		}
	}

}
