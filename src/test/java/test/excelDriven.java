package test;
import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDriven {
	
	public static String getData(String attribute) throws IOException {
		   String value="null";	
		   FileInputStream fis= new FileInputStream("C:\\Users\\mahes\\eclipse-workspace\\Automatesalesforce\\testData.xlsx");
           XSSFWorkbook workbook = new XSSFWorkbook(fis);
           XSSFSheet sheet = workbook.getSheetAt(0);
           int rowcount= sheet.getLastRowNum();
           //System.out.println(rowcount);
            for(int i=0;i<=rowcount;i++) {
           if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(attribute)) {
        	   value= sheet.getRow(i).getCell(1).getStringCellValue(); 
        	   break;
        	   }
        }
		return value;          
		
        
	}


}
