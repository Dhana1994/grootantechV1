package com.grootantech.testCases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.grootantech.pageObjects.HomePage;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_002 extends BaseClass {
	
	
	@Test()
	public void TC_ScreenshotCompare_2() throws InterruptedException, IOException
	{
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().deleteAllCookies();
		//driver.findElement(By.xpath("//*[@id='root']/div/footer/div[1]/div/div[1]/button")).click();

		clickAction(driver, "Folder2");
		compareScreenshot();

}

	public void compareScreenshot() throws InterruptedException, IOException {
		File[] folder_lists_1 = new File("D:\\Workspace1\\grootantechV1\\Folder1").listFiles();
		File[] folder_lists_2 = new File("D:\\Workspace1\\grootantechV1\\Folder2").listFiles();

		
	for(int i = folder_lists_1.length - 1; i>=0 ;i-- )
		{
			new ScreenshotCompare().comparescreenshot(folder_lists_1[i],folder_lists_2[i] );
	}
		}
	

}
