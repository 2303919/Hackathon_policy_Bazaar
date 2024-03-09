package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelUtility {
	
	String path;
	static FileInputStream fileIn;
	static FileOutputStream fileOut;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public ExcelUtility(String path) 
	{
		this.path=path;
	}
	
	public static void travelDetails(List<WebElement> plans, List<WebElement> amount) throws IOException
	{
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\excel_output\\travel.xls");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		//sheet.createRow(t).createCell(0).setCellValue(data);
		
		//create a font with bold style
		HSSFFont font=workbook.createFont();
		font.setBold(true);
				
		//create a cell style with the bold font
		HSSFCellStyle style=workbook.createCellStyle();
		style.setFont(font);
				
		HSSFRow row0 = sheet.createRow(0);
		HSSFCell col0=row0.createCell(0);
		col0.setCellValue("COMPANY");
		col0.setCellStyle(style);
		
		HSSFCell col1=row0.createCell(1);
		col1.setCellValue("AMOUNT");
		col1.setCellStyle(style);
		
		
		for(int i=1; i<=3;i++) {
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(plans.get(i-1).getText());
			row.createCell(1).setCellValue(amount.get(i-1).getText());
		}
		
		workbook.write(file);
		workbook.close();
		file.close();
	}
	public static void carDetail(WebElement quote) {

		try {
		    fileIn = new FileInputStream(".\\excel_output\\car.xlsx");
		} catch (FileNotFoundException e) {
			fileIn = null;
		}

		if(fileIn == null) {
			workbook = new XSSFWorkbook();
		}
		else {
			try {
				workbook = new XSSFWorkbook(fileIn);
			} catch (IOException e) {
				System.out.println("Error creating worbook");
			}
		}
		
		try {
			sheet = workbook.createSheet("car_quote");
		}
		catch (Exception e) {
			sheet = workbook.getSheet("car_quote");
		}
		
		//create a font with bold style
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		
		//create a cell style with the bold font
		XSSFCellStyle style=workbook.createCellStyle();
		style.setFont(font);
		
		XSSFRow row0=sheet.createRow(0);
		XSSFCell col= row0.createCell(0);
		col.setCellValue("CAR QUOTES");
		col.setCellStyle(style);
		
		
		XSSFRow row1=sheet.createRow(1);
		XSSFCell cell=row1.createCell(0);
		cell.setCellValue(quote.getText());
		sheet.autoSizeColumn(0);

		if(fileIn != null) {
			try {
				fileIn.close();
			} catch (IOException e) {
				System.out.println("Error closing file");
			}
		}
		try {
			fileOut = new FileOutputStream(".\\excel_output\\car.xlsx");
			workbook.write(fileOut);
			workbook.close();
			fileOut.close();
		} catch (IOException e) {
		    
		}
	} 
	public static void healthDetails(List<WebElement> details) {
		
		try {
		    fileIn = new FileInputStream(".\\excel_output\\health.xlsx");
		} catch (FileNotFoundException e) {
			fileIn = null;
		}

		if(fileIn == null) {
			workbook = new XSSFWorkbook();
		}
		else {
			try {
				workbook = new XSSFWorkbook(fileIn);
			} catch (IOException e) {
				System.out.println("Error creating worbook");
			}
		}
		
		try {
			sheet = workbook.createSheet("health_list");
		}
		catch (Exception e) {
			sheet = workbook.getSheet("health_list");
		}
		
		//create a font with bold style
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		
		//create a cell style with the bold font
		XSSFCellStyle style=workbook.createCellStyle();
		style.setFont(font);
		
		XSSFRow row0=sheet.createRow(0);
		XSSFCell col= row0.createCell(0);
		col.setCellValue("HEALTH INSURANCE DETAILS");
		col.setCellStyle(style);
		
		for(int i=1;i<=details.size();i++) {
		XSSFRow row1=sheet.createRow(i);
		row1.createCell(0).setCellValue(details.get(i-1).getText());
		}

		if(fileIn != null) {
			try {
				fileIn.close();
			} catch (IOException e) {
				System.out.println("Error closing file");
			}
		}
		try {
			fileOut = new FileOutputStream(".\\excel_output\\health.xlsx");
			workbook.write(fileOut);
			workbook.close();
			fileOut.close();
		} catch (IOException e) {
			
		}
	} 
}
