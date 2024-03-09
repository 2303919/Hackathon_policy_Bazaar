package Utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class ReadExcel {

	public static FileInputStream file;
	public static XSSFSheet sheet;
	public static XSSFWorkbook workbook;
	public static String[] data1=new String[2];
	public static String[] data2=new String[2];
	
	public static String[] invalid_Data() throws IOException{
		
		file=new FileInputStream(".//testData/carInsurance.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet=workbook.getSheet("data");
		
		for(int i=0;i<2;i++) {
			if(i==1) {
				String cellData=(""+(long) sheet.getRow(2).getCell(i).getNumericCellValue());
				data2[i]=cellData;
			}
			else {
				String cellData=sheet.getRow(2).getCell(i).toString();
				data2[i]=cellData;
			}
			}
		workbook.close();
		file.close();
		return data2;
	}
	
	public static String[] valid_Data() throws IOException{
		
		file=new FileInputStream(".//testData/carInsurance.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet=workbook.getSheet("data");
		
		for(int i=0;i<2;i++) {
			if(i==1) {
				String cellData=(""+(long) sheet.getRow(3).getCell(i).getNumericCellValue());
				data1[i]=cellData;
			}
			else {
				String cellData=sheet.getRow(3).getCell(i).toString();
				data1[i]=cellData;
			}
			}
		workbook.close();
		file.close();
		return data1;
	}
}
