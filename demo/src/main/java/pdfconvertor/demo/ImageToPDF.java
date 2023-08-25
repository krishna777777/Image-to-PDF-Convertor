package pdfconvertor.demo;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.*;

import java.io.File;
import java.io.IOException;


public class ImageToPDF {
	
	public static void main(String[] args) {
	        String inputImageDirectory = "C://Image/"; // Replace with the actual path
	        // Replace with the desired output PDF path"
	        String outputPdfPath = "C://Image/output.pdf"; 

	        try {
	            PDDocument combinedPdf = new PDDocument();

	            File[] imageFiles = new File(inputImageDirectory).listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));

	            //int desiredWidth = 600;
	            
	            if (imageFiles != null) {
	                for (File imageFile : imageFiles) {
	                    try {
	                        PDDocument pdfDocument = new PDDocument();
	                        PDPage page = new PDPage();
	                        pdfDocument.addPage(page);

	                        PDImageXObject image = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), pdfDocument);
	                        PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);
	                        //contentStream.drawImage(image, 0, 0); // Draw the loaded image onto the page
	                        contentStream.drawImage(image, 0, 0, page.getMediaBox().getWidth(), page.getMediaBox().getHeight());
	                       // contentStream.drawImage(image, 0, 0, desiredWidth, scaledHeight);
	                        
	                        
	                        contentStream.close();

	                        combinedPdf.addPage(pdfDocument.getPage(0));
	                        //pdfDocument.close(); // Close the pdfDocument after adding the page
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



