package utilities;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFCell cell;
    public XSSFCellStyle cellStyle;
    public String filePath;


    public ExcelUtility(String path){
        filePath = path;

    }

    public int getNumberOfRow(String sheetName) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();

        workbook.close();
        fi.close();

        return rowCount;
    }

    public int getNumberOfCell(String sheetName, int rowNumber) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        int cellCount = sheet.getRow(rowNumber).getLastCellNum();

        workbook.close();
        fi.close();

        return cellCount;
    }

    public String getCellData(String sheetName, int rowNumber, int cellNumber) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        String cellData;

        try {
            cell = sheet.getRow(rowNumber).getCell(cellNumber);
            cellData = cell.toString();
        } catch (Exception e) {
            cellData = "";
        }

        workbook.close();
        fi.close();

        return cellData;
    }

    public void setCellData(String sheetName, int rowNumber, int cellNumber, String data) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        cell.setCellValue(data);

        fo = new FileOutputStream(filePath);
        workbook.write(fo);

        fo.close();
        workbook.close();
        fi.close();


    }

    public void setCellColorGreen(String sheetName, int rowNumber, int cellNumber) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());

        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        cell.setCellStyle(cellStyle);

        fo = new FileOutputStream(filePath);
        workbook.write(fo);

        fo.close();
        workbook.close();
        fi.close();

    }

    public void setCellColorRed(String sheetName, int rowNumber, int cellNumber) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());

        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        cell.setCellStyle(cellStyle);

        fo = new FileOutputStream(filePath);
        workbook.write(fo);

        fo.close();
        workbook.close();
        fi.close();

    }

    public void setCellColorYellow(String sheetName, int rowNumber, int cellNumber) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        cell.setCellStyle(cellStyle);

        fo = new FileOutputStream(filePath);
        workbook.write(fo);

        fo.close();
        workbook.close();
        fi.close();

    }


}
