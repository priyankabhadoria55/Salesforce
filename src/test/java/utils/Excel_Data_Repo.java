package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Excel_Data_Repo {

    public static String getCellData(String filePath, String sheetName, int rowNumber, String columnHeaderName) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0); // Assuming the first row is the header row

            // Find the column index for the given header name
            int columnIndex = -1;
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase(columnHeaderName)) {
                    columnIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (columnIndex == -1) {
                throw new IllegalArgumentException("Column header not found: " + columnHeaderName);
            }

            // Get the cell data from the specified row and column
            Row dataRow = sheet.getRow(rowNumber);
            if (dataRow != null) {
                Cell cell = dataRow.getCell(columnIndex);
                return cell != null ? cell.toString() : null;
            } else {
                return null; // Row does not exist
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateData(String filePath, String sheetName, int rowNumber, String columnHeaderName, String value) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0); // Assuming the first row is the header row

            // Find the column index for the given header name
            int columnIndex = -1;
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase(columnHeaderName)) {
                    columnIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (columnIndex == -1) {
                throw new IllegalArgumentException("Column header not found: " + columnHeaderName);
            }

            // Get the row or create it if it doesn't exist
            Row dataRow = sheet.getRow(rowNumber);
            if (dataRow == null) {
                dataRow = sheet.createRow(rowNumber);
            }

            // Get the cell or create it if it doesn't exist
            Cell cell = dataRow.getCell(columnIndex);
            if (cell == null) {
                cell = dataRow.createCell(columnIndex);
            }

            // Update the cell value
            cell.setCellValue(value);

            // Save the changes back to the file
            try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws FileNotFoundException {

        String filePath = "src\\test\\resources\\dataSource\\LiveU_Data.xlsx";
        String sheetName = "Customer_Data";

        // Get cell data
        String cellData = Excel_Data_Repo.getCellData(filePath, sheetName, 1, "Name");
        System.out.println("Cell Data: " + cellData);

        // Update cell data
        Excel_Data_Repo.updateData(filePath, sheetName, 1, "Name", "New Value");
        System.out.println("Cell updated successfully.");
    }
}