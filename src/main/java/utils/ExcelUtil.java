package utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    public static Object[][] getTestData(String filePath, String sheetName) {
        Object[][] data=null;

        try {
            FileInputStream fis=new FileInputStream(filePath);
            Workbook wb=new XSSFWorkbook(fis);
            Sheet sheet=wb.getSheet(sheetName);
            
            int rows=sheet.getPhysicalNumberOfRows();
            int cols=sheet.getRow(0).getPhysicalNumberOfCells();

            data=new Object[rows-1][cols];

            for (int i=1;i<rows;i++) {
                for (int j=0;j<cols;j++) {
                    if (sheet.getRow(i)==null || sheet.getRow(i).getCell(j)==null) {
                        data[i-1][j]="";
                    } else {
                        data[i-1][j]=sheet.getRow(i).getCell(j).toString();
                    }
                }
            }
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}