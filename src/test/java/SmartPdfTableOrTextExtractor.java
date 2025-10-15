//import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//import technology.tabula.ObjectExtractor;
//import technology.tabula.Page;
//import technology.tabula.Table;
//import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
//import technology.tabula.RectangularTextContainer;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//public class SmartPdfTableOrTextExtractor {
//
//    public static void main(String[] args) throws IOException {
//        File pdfFile = new File("C:\\Users\\ashwanis\\OneDrive - 360 Logica Software Testing Company\\Desktop\\11-B672N2-328-26544580-01072021.pdf");
//        PDDocument document = Loader.loadPDF(pdfFile);
//        try {
//            int totalPages = document.getNumberOfPages();
//
//            ObjectExtractor extractor = new ObjectExtractor(document);
//            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
//            PDFTextStripper textStripper = new PDFTextStripper();
//
//            for (int pageNum = 1; pageNum <= totalPages; pageNum++) {
//                System.out.println("\n================== PAGE " + pageNum + " ==================\n");
//
//                // Try extracting with Tabula
//                Page tabulaPage = extractor.extract(pageNum);
//                List<Table> tables = sea.extract(tabulaPage);
//
//                if (tables != null && !tables.isEmpty()) {
//                    System.out.println("[📊 Table Detected - Extracted Table Data]");
//                    for (Table table : tables) {
//                        for (List<RectangularTextContainer> row : table.getRows()) {
//                            for (RectangularTextContainer cell : row) {
//                                System.out.print(cell.getText().trim() + " | ");
//                            }
//                            System.out.println();
//                        }
//                    }
//                } else {
//                    // Fallback: Extract raw text with spacing preserved
//                    System.out.println("[📄 No Table Detected - Extracting Full Text]");
//                    textStripper.setStartPage(pageNum);
//                    textStripper.setEndPage(pageNum);
//                    textStripper.setSortByPosition(true); // maintain formatting
//                    String rawText = textStripper.getText(document);
//                    System.out.println(rawText);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
