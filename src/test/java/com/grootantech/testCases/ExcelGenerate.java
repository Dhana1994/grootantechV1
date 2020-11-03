package com.grootantech.testCases;

import org.automationtesting.excelreport.Xl;


public class ExcelGenerate {
	public static void main(String[]args) throws Exception
	{
		Xl.generateReport("excel-report.xlsx");

	}

}
