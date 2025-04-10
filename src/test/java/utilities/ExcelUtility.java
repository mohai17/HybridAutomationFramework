package utilities;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFCell cell;
    public static XSSFCellStyle cellStyle;
    public static String filePath;
    public static String sheetName;

    public ExcelUtility(String path, String name){
        filePath = path;
        sheetName = name;
    }

    public static int getNumberOfRow() throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();

        workbook.close();
        fi.close();

        return rowCount;
    }

    public static int getNumberOfCell() throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        int cellCount = sheet.getRow(1).getLastCellNum();

        workbook.close();
        fi.close();

        return cellCount-1;
    }

    public static String getCellData(int rowNumber, int cellNumber) throws IOException {

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

    public static void setCellData(int rowNumber, int cellNumber, String data) throws IOException {

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

    public static void setCellColorGreen(int rowNumber, int cellNumber) throws IOException {

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

    public static void setCellColorRed(int rowNumber, int cellNumber) throws IOException {

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

    public static void setCellColorYellow() throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

        cell.setCellStyle(cellStyle);

        fo = new FileOutputStream(filePath);
        workbook.write(fo);

        fo.close();
        workbook.close();
        fi.close();

    }


}
