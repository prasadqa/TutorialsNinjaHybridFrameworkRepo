package com.tutorialsNinja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;



public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 20;
	public static final int PAGELOAD_WAIT_TIME = 20;
	public static final String TESTDATA_EXCEL_PATH = "/Users/challaprasad/Documents/Java-Selenium/tutorialsNinja/src/main/java/com/tutorialsNinja/qa/testdata/ninjaTestData.xlsx";
	static XSSFWorkbook workBook;

	public static String generateWirhTimeStamp() {

		Date data = new Date();
		String dateWith = data.toString().replace(" ", "_").replace(":", "_");
		String email = "ravi" + dateWith + "@gmail.com";
		return email;

	}
	
	public static Object[][] getDataFromExcel(String sheetname) {

		File excelFile = new File(TESTDATA_EXCEL_PATH);
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workBook = new XSSFWorkbook(fisExcel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workBook.getSheet(sheetname);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];
		
		for(int i = 0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j =0;j<cols;j++) {
				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();
				
				switch (celltype) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;	
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				default:
					break;
					
				}
			}
		}
		return data;

	}
	
	public static String captureScreenShot(WebDriver driver,String testName) {
		
		File scrScreen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destScreenShot = System.getProperty("user.dir")+"/ScreenShots/"+testName+".png";
		try {
			FileHandler.copy(scrScreen, new File(destScreenShot));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return destScreenShot;
		
	}

}
