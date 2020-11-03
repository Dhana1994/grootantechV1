package com.grootantech.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Screenshot extends BaseClass {

	@Test()
	public void TC_Screenshot_1() throws InterruptedException, IOException

	{

		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// ***Accept Cookies***
		// Set <Cookie> c1= driver.manage().getCookies();
		// System.out.println(c1);
		
		//driver.manage().deleteAllCookies();

		driver.findElement(By.xpath("//*[@id='root']/div/footer/div[1]/div/div[1]/button")).click();

		clickAction(driver, "Folder1");
		
		
	}

}
