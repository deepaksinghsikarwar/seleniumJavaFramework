package utils.dataprovider;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class DataProviderUtils {

    @DataProvider(name = "loginPageData")
    public Object[][] loginPageData(){
        return new Object[][]{
                {"data","value"}
        };
    }

    @DataProvider(name="excelDataProvider")
    public Object[][] getExcelFileData() throws IOException {
        String filePath = Paths.get(System.getProperty("user.dir")+"/src/test/java/testdata","TestData.xlsx").toString();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("Sheet1");
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rowCount-1][colCount];
            for(int i=1; i<rowCount;i++){
                Row row = sheet.getRow(i);
                for(int j=0;j<colCount;j++){
                    Cell c = row.getCell(j);
                    data[i-1][j] = c.getStringCellValue();
                }
            }

            workbook.close();
            return data;
        }

    }
}
