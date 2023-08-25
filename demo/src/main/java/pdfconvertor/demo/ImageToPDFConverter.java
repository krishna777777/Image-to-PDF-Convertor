package pdfconvertor.demo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class ImageToPDFConverter {
    public static void main(String[] args) {
        String inputImageDirectory = "C://Image/"; // Replace with the actual path
        // Replace with the desired output PDF path"
        String outputPdfPath = "C://Image/output.pdf";

        try {
            PDDocument combinedPdf = new PDDocument();

            File[] imageFiles = new File(inputImageDirectory).listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));

            int desiredHeightPx= 300;// Set you desired Height in Pixel here . 
            int desiredWidthPx = 600; // Set your desired width in pixels here

            if (imageFiles != null) {
                for (File imageFile : imageFiles) {
                    try {
                        PDDocument pdfDocument = new PDDocument();
                       
                        PDPage page = new PDPage();
                        pdfDocument.addPage(page);

                        PDImageXObject image = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), pdfDocument);

                       // float scalingFactor = (float) desiredWidthPx / image.getWidth();
                        //int desiredHeightPx = (int) (image.getHeight() * scalingFactor);

                        PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);
                        contentStream.drawImage(image, 0, 0, desiredWidthPx, desiredHeightPx);
                        contentStream.close();

                        combinedPdf.addPage(pdfDocument.getPage(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                combinedPdf.save(outputPdfPath);
                combinedPdf.close();

                System.out.println("PDF created successfully!");
            } else {
                System.out.println("No image files found in the directory.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
