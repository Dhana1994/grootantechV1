package com.grootantech.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	public static HSSFSheet sheet ;
	public static HSSFWorkbook workbook ;
	private static List<Object[]> reportSummary = new ArrayList<Object[]>();
	String fileName = "TSR.xls";

	
	public void createSheet()
	{
		 workbook = new HSSFWorkbook();
		 sheet = workbook.createSheet("TSR");
	}

	public void addDataToReportSummary(Object[] data) {
		reportSummary.add(data);
	}
	
	public void writeJuniorEngineers(List<String> dataToWrite) throws IOException {

		Sheet sheet = workbook.createSheet("JuniorEngineers");
		sheet.createRow(0);
	//	Row row = sheet.getRow(0);
	int rowcount = 0;

		for (String data : dataToWrite) {
			//int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			Row newRow = sheet.createRow(rowcount);
			Cell cell = newRow.createCell(0);
			cell.setCellValue(data);
		rowcount++;
		}

		try {
			FileOutputStream out = new FileOutputStream(new File(fileName));
			workbook.write(out);
			out.close();
			System.out.println("Successfully saved Junior Engineers data to Excel File!!!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		workbook.close();
	}

	public void writeExcel(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {

		Sheet sheet = workbook.createSheet("JuniorEngineers");
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
		Sheet sheet1 = workbook.getSheet(sheetName);
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

	public void writeReport() throws Exception {
		
	//	Set<String> keyset = reportSummary.keySet();
		for (int i = 0 ; i < reportSummary.size(); i++) {
			//int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			Row row = sheet.createRow(i);
		//	Object[] objArr = reportMap.get(key);
			int cellnum = 0;
			if(i != 0) {
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue(i);
			}
			for (Object obj : reportSummary.get(i)) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date)
					cell.setCellValue((Date) obj);
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File(fileName));
			workbook.write(out);
			out.close();
			System.out.println("Successfully saved Test Summary Report to Excel File!!!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		workbook.close();

	}
}
