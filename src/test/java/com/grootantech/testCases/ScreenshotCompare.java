package com.grootantech.testCases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.testng.Assert;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ScreenshotCompare {
	
	public void comparescreenshot(File expected , File actual ) throws InterruptedException, IOException
	{
		
		System.out.println(expected.getName());
		System.out.println(actual.getName());
		BufferedImage expectedImage = ImageIO.read(expected);
		BufferedImage actualImage = ImageIO.read(actual);
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		
		 if(diff.hasDiff()==true)
	        {
	         System.out.println("Images are Not Same");
	        }
	        else {
	         System.out.println("Images are Same");
	        }
		
		//Assert.assertTrue(diff.hasDiff(),"Result of Image comparsion");
		//System.out.println("Images Compared Sucesfully");
	}

}
