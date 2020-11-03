package com.grootantech.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	public void writeExcel(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet(sheetName);

		Row row = sheet.getRow(0);
		int rowcount = 1;

		for (int i = 0; i < dataToWrite.length; i++) {
			// int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			Row newRow = sheet.createRow(rowcount);
			// for(int j = 0; j < row.getLastCellNum(); j++){

			Cell cell = newRow.createCell(0);

			cell.setCellValue(dataToWrite[i]);
			// }
			rowcount++;

			// Row newRow = sheet.createRow(rowCount+1);

		}

		inputStream.close();

		FileOutputStream outputStream = new FileOutputStream(file);

		workbook.write(outputStream);

		outputStream.close();

	}

}
