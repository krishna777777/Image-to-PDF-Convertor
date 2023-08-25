package pdfconvertor.demo;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class RemoveWhitespaceFromPDF {

    public static void main(String[] args) {
        String inputPdfPath = "C:\\Image\\output.pdf";
        String outputPdfPath = "C:\\Image\\output_withoutspace.pdf";

        try {
        	
           // PDDocument document = PDDocument.load(new File(inputPdfPath));

        	// input pdf file 
        	File file = new File(inputPdfPath);
        	
        	PDDocument document = Loader.loadPDF(file);
        	
            for (PDPage page : document.getPages()) {
                // Get the bounding box of the content in the page
                PDRectangle contentBoundingBox = page.getMediaBox(); // Adjust if needed

                // Crop the page to the content bounding box
                page.setCropBox(contentBoundingBox);
            }

            document.save(outputPdfPath);
            document.close();

            System.out.println("Whitespace removed and saved to " + outputPdfPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
