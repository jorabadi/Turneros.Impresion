package turneros.Impresion.post;

import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobEvent;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import turneros.Common.entidades.Turno;

import com.turneros.string.StringFormatter;

public class PostPrinterDriver {
	private final byte[] PRINT_AND_FEED_PAPER = new byte[]{0x0A};
	public static void print(String template, Object params) {
		 try {
			 	String printerData = StringFormatter.format(template, params);
			 	ByteArrayOutputStream printData = new ByteArrayOutputStream();
			 	byte[] bAlignment = {27,97,1};
			 	printData.write(bAlignment);
			 	byte[] bFontSize = {8,77,2,65};
			 	printData.write(bFontSize);
			 	printData.write(printerData.getBytes("UTF-8"));
			 	
			 	byte[] bFeed = {27,100,7};
			 	printData.write(bFeed);
			 	byte[] bCut = {29, 86, 48, 25};
			 	printData.write(bCut);
	            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

	            // Find the default service
	            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
	            System.out.println(service);

	            Doc doc= new SimpleDoc(printData.toByteArray(), flavor, null);
	         // Create the print job
	            DocPrintJob job = service.createPrintJob();
	            
	            
	            PrintJobWatcher pjDone=new PrintJobWatcher(job);

	            // Print it
	            job.print(doc, null);
	            pjDone.waitForDone();
	            printData.close();
	        } catch (PrintException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void main(String[] args) {
		
		String template = 
		"******   EMPRESA      	****\n"+
		"***************************\n"+
		"****Fecha: ***************\n"+
		"***************************\n"+
		"***************************\n"+
		"***************************\n"+
		"***** 	    Turno:      ****\n"+
		"***       ${turno}       **\n"+
		"****                     **\n"+
		"***************************\n"+
		"***************************\n"+
		"***************************\n"+
		"***************************\n"+
		"***************************\n"+
		"***************************\n"+
		"***************************\n"+
		"***************************\n"+
		"***************************\n";
		Turno turno = new Turno("12","2",1,new Date());
		
		PostPrinterDriver.print(template, turno);
	}
}
