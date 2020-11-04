package com.grootantech.testCases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.testng.Assert;

import com.grootantech.utilities.WriteExcel;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ScreenshotCompare {

	public void comparescreenshot(File expected, File actual, int row) throws Exception {

		WriteExcel we = new WriteExcel();

		System.out.println("Expected Name : " + expected.getName() + " , actual Name : " + actual.getName());
		BufferedImage expectedImage = ImageIO.read(expected);
		BufferedImage actualImage = ImageIO.read(actual);
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);

		if (diff.hasDiff() == true) {
			System.out.println("Images are Not Same");
			we.addDataToReportSummary(
					new Object[] { "Screenshot Comparison - " + expected.getName(), "Screenshot Compared", "Fail" });
			System.out.println("Images are Not Same");
		} else {
			we.addDataToReportSummary(
					new Object[] { "Screenshot Comparison - " + expected.getName(), "Screenshot Compared", "Pass" });
			System.out.println("Images are Same");
		}
	}

}
