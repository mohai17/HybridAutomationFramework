package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="loginData")
    public String[][] getData() throws IOException {

        String filePath = "./testData/LoginData.xlsx";
        String sheetName = "Sheet1";

        ExcelUtility excelUtility = new ExcelUtility(filePath);

        int rowCount = excelUtility.getNumberOfRow(sheetName);
        int cellCount = excelUtility.getNumberOfCell(sheetName,1);

        String[][] loginData = new String[rowCount][cellCount];

        for(int i=1; i<=rowCount; i++){
            for(int j=0; j<cellCount; j++){

                loginData[i-1][j] = excelUtility.getCellData(sheetName,i,j);

            }
        }

        return loginData;
    }

}
