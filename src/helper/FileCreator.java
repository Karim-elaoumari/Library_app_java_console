package helper;


import model.Book;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.swing.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class FileCreator {
   public static void createExcelFile(List<Book> books, String fileName) {
      try (Workbook workbook = new HSSFWorkbook()) {
         Sheet sheet = workbook.createSheet("Library Books");

         // Create a title style
         CellStyle titleStyle = workbook.createCellStyle();
         Font titleFont = workbook.createFont();
         titleFont.setBold(true);
         titleFont.setFontHeightInPoints((short) 14);
         titleStyle.setFont(titleFont);

         // Create a header style
         CellStyle headerStyle = workbook.createCellStyle();
         headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
         headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
         Font headerFont = workbook.createFont();
         headerFont.setBold(true);
         headerStyle.setFont(headerFont);

         // Create the header row
         Row headerRow = sheet.createRow(0);
         String[] headers = {"Number", "Title", "Author", "ISBN", "Quantity", "Language", "Quantity Borrowed", "Quantity Lost"};
         for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
         }

         for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(i + 1);
            dataRow.createCell(1).setCellValue(book.getTitle());
            dataRow.createCell(2).setCellValue(book.getAutor().getName());
            dataRow.createCell(3).setCellValue(book.getIsbn());
            dataRow.createCell(4).setCellValue(book.getQuantity());
            dataRow.createCell(5).setCellValue(book.getLanguage());
            dataRow.createCell(6).setCellValue(book.getQuantityBorrowed());
            dataRow.createCell(7).setCellValue(book.getQuantityLosted());
         }
         for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
         }
//         String home = System.getProperty("user.home");
         String home = getDirectoryFromUser();
         if (home == null) {
            return;
         }else{
            File file = new File(home + "/" + fileName + ".xls");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
               workbook.write(fileOutputStream);
               System.out.println("File created successfully. You can find it in "+home+" folder.");
               fileOutputStream.close();
            }catch (IOException e) {
               System.out.println("Error creating file.");
            }
         }
         workbook.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   public static String getDirectoryFromUser() {
      System.out.println("Please select a directory");

      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int returnValue = fileChooser.showOpenDialog(null);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
         System.out.println("Directory selected");
         return fileChooser.getSelectedFile().getAbsolutePath();
      }
       System.out.println("No directory selected");
      return null;
   }
}
