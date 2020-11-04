package com.grootantech.testCases;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.grootantech.utilities.WriteExcel;

public class Screenshot extends BaseClass {

	@Test(description = "Opens the Website and captures screenshot for all the section", priority = 1)
	public void TC_Screenshot_1() throws Exception {

		WriteExcel we = new WriteExcel();
		try {
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//*[@id='root']/div/footer/div[1]/div/div[1]/button")).click();

			clickAction(driver, "Folder1");

			we.addDataToReportSummary(new Object[] { "Opens website", "Screenshot Captured", "Pass" });
		} catch (Exception e) {
			we.addDataToReportSummary(new Object[] { "Opens website", "Screenshot Captured", "Fail" });
			Assert.assertTrue(false);
		}
	}
}
