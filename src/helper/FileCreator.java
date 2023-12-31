package helper;

import model.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class FileCreator {
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

   public static void createExcelFile(List<Book> books, String fileName) {
      try (XSSFWorkbook workbook = new XSSFWorkbook()) {
         XSSFSheet sheet = workbook.createSheet("Library Books");

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
         String[] headers = {"Number", "Title", "Author", "ISBN", "Quantity In Stock", "Language", "Quantity Borrowed", "Quantity Lost"};
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

         // Create a chart in the sheet
         XSSFDrawing drawing = sheet.createDrawingPatriarch();
         XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 1, 10, 12, 25);

         XDDFChart chart = drawing.createChart(anchor);
         XDDFChartLegend legend = chart.getOrAddLegend();
         legend.setPosition(LegendPosition.RIGHT);

         XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
         XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
         leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

// Define the chart data sources
         XDDFDataSource<String> categories = XDDFDataSourcesFactory.fromStringCellRange(sheet, new CellRangeAddress(1, books.size(), 1, 1));
         XDDFNumericalDataSource<Double> borrowedValues = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, books.size(), 6, 6));
         XDDFNumericalDataSource<Double> lostValues = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, books.size(), 7, 7));

// Create a series for Quantity Borrowed
         XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
         XDDFChartData.Series borrowedSeries = data.addSeries(categories, borrowedValues); // Borrowed quantity
         borrowedSeries.setTitle("Quantity Borrowed", null);

// Create a series for Quantity Lost
         XDDFChartData.Series lostSeries = data.addSeries(categories, lostValues); // Lost quantity
         lostSeries.setTitle("Quantity Lost", null);

// Explicitly set the minimum and maximum values for the category axis
         bottomAxis.setMinimum(0); // Adjust as needed
         bottomAxis.setMaximum(books.size()); // Adjust as needed

         chart.plot(data);

         // Set chart title
         chart.setTitleText("Book Quantities - Borrowed vs Lost");
         // Save the workbook to a file
         String home = getDirectoryFromUser();
         if (home == null) {
            return;
         } else {
            File file = new File(home + "/" + fileName + ".xlsx");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
               workbook.write(fileOutputStream);
               System.out.println("File created successfully. You can find it in " + home + " folder.");
            } catch (IOException e) {System.out.println("Error creating file.");}
         }
      } catch (IOException e) {e.printStackTrace();}
   }
}







