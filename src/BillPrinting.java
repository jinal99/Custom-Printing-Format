/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.lowagie.text.DocumentException;
import com.sun.pdfview.PDFFile;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.xml.parsers.ParserConfigurationException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;
import com.sun.pdfview.PDFPrintPage;
import java.awt.Graphics;
import java.awt.print.Book;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import static javax.print.attribute.standard.OrientationRequested.LANDSCAPE;
import javax.swing.text.Document;

/**
 *
 * @author hitesh
 */
public class BillPrinting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, DocumentException, PrintException, FileNotFoundException, PrinterException {
                String str = "<!DOCTYPE html><html><head><style> *{ font-size: 200% !important;}table, th, td {border: 1px solid black;border-collapse: collapse;font-size: 200% !important\n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<table style=\"width:100%\'\n" +
"  <tr>\n" +
"    <th>Firstname</th>\n" +
"    <th>Lastname</th> \n" +
"    <th>Age</th>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td>Jill</td>\n" +
"    <td>Smith</td>\n" +
"    <td>50</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td>Eve</td>\n" +
"    <td>Jackson</td>\n" +
"    <td>94</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td>John</td>\n" +
"    <td>Doe</td>\n" +
"    <td>80</td>\n" +
"  </tr>\n" +
"</table>\n" +
"\n" +
"</body>\n" +
"</html>";

                String str1 = "<!DOCTYPE html>\n" +
"<html>\n" +
"<body>\n" +
"\n" +
"<p>I am normal</p>\n" +
"<p style=\"color:red;\'I am red</p>\n" +
"<p style=\"color:blue;\'I am blue</p>\n" +
"<p style=\"font-size:36px;\'I am big</p>\n" +
"\n" +
"</body>\n" +
"</html>";
        // here        
String generic_medical_info =  "<table style=' width: 100% ;'>"+
                
                    "<tr >"+
                        "<td >Schedule C.E.H.L</td>"+
                        "<td style='text-align: left'>"+
                          "<p style='margin:0 0 0 0;'>"+
                            "<i class='fa fa-plus fa-fw fa-lg' style='color:green;'></i> "+
                                "<b>"+"Medical Name"+"</b>"+
                            "<i class='fa fa-plus fa-fw fa-lg' style='color:green;'></i>"+
                          "</p>"+
                        "</td>"+
                        "<td ></td>"+
                    "</tr>"+
                
                    "<tr style=''>"+
                        "<td style=''><p style='margin:-0.4em 0 0 0;'><b>Cash Memo</b></p></td>"+
                        "<td style='text-align:left;'><p  style='margin:-0.4em 0 0 -1em;'><b>AND GENERAL STORES</b></p></td>"+
                        "<td style=''></td>"+
                    "</tr>"+
                    
                    "<tr style=''>"+                    
                        "<td colspan='3'>"+
                            "<p style='margin:0 0 0 0;font-size:10pt;'>"+"Medical Address"+"</p>"+
                        "</td>"+                       
                    "</tr>"+
                "</table>";

String bill_header = "<tr style='width: 100%;' >"+
    "<td style='border-bottom: 1px solid black;'>"+
        "<table style='margin:0 0 1em 0;width: 100%; cellspacing=\"0\" cellpadding=\"0\"'>"+                
                "<tr>"+
                    "<td>Pt. Name</td>"+
                    "<td>"+
                        "<b><label id='cust_name' >"+"cust_name"+"</label></b>"+
                    "</td>"+
                    "<td>Adress</td>"+
                    "<td>"+
                         "<b><label  id='patient_address'>"+"cust_address"+"</label></b>"+
                    "</td>"+
                    "<td>Bill No.</td>"+
                    "<td>"+
                        "<b><label id='patient_bill_no' >"+"bill_no"+"</label></b>"+
                    "</td>"+
                "</tr>"+

                "<tr >"+
                "<td>Dt. Name</td>"+
                   "<td>"+
                        "<b><label id='doctor_name'>"+"dr_name"+"</label></b>"+
                    "</td>"+
                    "<td>Address</td>"+
                    "<td>"+
                        "<b><label   id='doctor_address'> "+"doctor_address"+"</label></b>"+
                    "</td>"+
                    "<td>Bill Date</td>"+
                    "<td>"+
                        "<b><label   id='bill_date'> "+"date"+"</label></b>"+
                    "</td>"+
                "</tr>"+
        "</table>"+
    "</td>"+
    "<td ></td>"+
    "<td ></td>"+
"</tr>";

 String product_body ="<tr style='text-align:center'>"+
                     "<td><label id='lb_qty'> </label><p></p></td>"+
                   "<td><label id='lb_pack'> </label><p></p></td>"+
                   "<td><label id='lb_description'><p></p> </label></td>"+
                    "<td><label id='lb_mfg'></label><p></p> </td>"+
                    "<td><label id='lb_batch'></label><p></p> </td>"+
                    "<td><label id='lb_expiry_date'><p></p> </label></td>"+
                    "<td><label id='lb_mrp'></label><p></p> </td>"+
                   "<td><label id='lb_amount' > <p></p></label></td>"+
                  "</tr>"+

"<tr style='width: 100%;'>"+
    "<td >"+
        "<table style='font-family:arial, sans-serif, monospace !important; width: 100%;font-size:9pt cellpadding=\"0\" cellspacing=\"0\"'>"+
            "<tr>"+
                "<td style=''>TIN No.</td>"+
                "<td><b><label id='lb_vat_no'>ABCDEFG</label></b></td>"+
                "<td style=''>L.B.T No:</td>"+
                "<td><b><label id='lb_lbt_no;'>"+"lbt"+"</label></b></td>"+
               
            "</tr>"+
            
        "</table>"+
    "</td>"+
    
"</tr>"+


"<tr style='width: 100%;'>"+
    "<td>"+
        "<table style='font-family:arial, sans-serif, monospace !important; width: 100%;font-size:9pt cellpadding=\"0\" cellspacing=\"0\"'>"+
            "<tr>"+
             "<td><label>Subject to Thane Jurisdiction. Goods once sold out  will not be taken</label></td>"+
                 "<td></td>"+
                "<td>E. And O.</td>"+
                "<td>Regd. Pharmacist</td>"+
                
            "</tr>"+
                        
        "</table>"+
    "</td>"+
    
"</tr>";

//--------------------------------------------------------------------------------------------------------------------------------------------
String to_print = "<html>\n" +
"<head>\n" +
"<style>\n" +
"*{\n" +
"font-size:100%\n" +
"}\n" +
"</style>\n" +
"</head><table style='font-family:arial, sans-serif, monospace !important;'>"
        + "<tr style='height:1.5cm !important;margin:0 0 0 0; width: 100%'>"
        + "<td style='width: 100%'><table style=' width: 100% ;'>"
        + "<tr ><td >Schedule C.E.H.L</td><td style='text-align: left'>"
        + "<p style='margin:0 0 0 0;'><i class='fa fa-plus fa-fw fa-lg' style='color:green;'>"
        + "</i> <b>Medical Name</b><i class='fa fa-plus fa-fw fa-lg' style='color:green;'></i></p></td>"
        + "<td ></td></tr><tr style=''><td style=''><p style='margin:-0.4em 0 0 0;'><b>Cash Memo</b></p></td>"
        + "<td style='text-align:left;'><p  style='margin:-0.4em 0 0 -1em;'><b>AND GENERAL STORES</b></p></td>"
        + "<td style=''></td></tr><tr style=''>"
        + "<td colspan='3'><p style='margin:0 0 0 0;font-size:10pt;'>Medical Address</p></td></tr></table></td>"
        + "<td></td><td></td></tr><tr style='width: 100%;' ><td style='border-bottom: 1px solid black;'>"
        + "<table style='margin:0 0 1em 0;width: 100%; cellspacing=\"0\" cellpadding=\"0\"'><tr><td>Pt. Name</td>"
        + "<td><b><label id='cust_name' >cust_name</label></b></td>"
        + "<td>Adress</td><td><b><label  id='patient_address'>cust_address</label></b></td>"
        + "<td>Bill No.</td><td><b><label id='patient_bill_no' >bill_no</label></b></td>"
        + "</tr><tr ><td>Dt. Name</td><td><b><label id='doctor_name'>dr_name</label></b>"
        + "</td><td>Address</td><td><b><label   id='doctor_address'> doctor_address</label>"
        + "</b></td><td>Bill Date</td><td><b><label   id='bill_date'> date</label></b></td></tr></table></td>"
        + "<td ></td><td ></td></tr><tr style=' width: 100%;'><td>"
        + "<table style='font-family:arial, sans-serif, monospace !important;width: 100%;text-align: right' cellpadding='0' cellspacing='0' >"
        + "<tr style='font-family:arial, sans-serif, monospace !important;' >"
        + "<td style='border-bottom: 1px solid black;'>Qty</td>"
        + "<td style='border-bottom: 1px solid black;'>Pack</td>"
        + "<td style='border-bottom: 1px solid black;'>Description</td>"
        + "<td style='border-bottom: 1px solid black;'>Mfg</td>"
        + "<td style='border-bottom: 1px solid black;'>Batch</td>"
        + "<td style='border-bottom: 1px solid black;'>Exp</td>"
        + "<td style='border-bottom: 1px solid black;'>Mrp</td>"
        + "<td style='border-bottom: 1px solid black;'>Amt</td>"
        + "</tr><tr style='font-family:arial, sans-serif, monospace !important;' >"
        + "<td>"
        + "Qty1</td>"
        + "<td >Pack1</td>"
        + "<td >Description1</td>"
        + "<td >Mfg1</td>"
        + "<td >Batch1</td>"
        + "<td >Exp1</td>"
        + "<td >Mrp1</td>"
        + "<td >Amt1</td>"
        + "</tr><tr style='font-family:arial, sans-serif, monospace !important;' >"
        + "<td >Qty2</td>"
        + "<td >Pack2</td>"
        + "<td >Description2</td>"
        + "<td>Mfg2</td>"
        + "<td>Batch2</td>"
        + "<td>Exp2</td>"
        + "<td >Mrp2</td>"
        + "<td >Amt2</td></tr>"
        + "<tr style='font-family:arial, sans-serif, monospace !important;' >"
        + "<td >Qty3</td>"
        + "<td >Pack3</td>"
        + "<td >Description3</td>"
        + "<td >Mfg3</td>"
        + "<td >Batch3</td>"
        + "<td >Exp3</td>"
        + "<td >Mrp3</td>"
        + "<td >Amt3</td>"
        + "</tr><tr style='font-family:arial, sans-serif, monospace !important;' >"
        + "<td >Qty4</td><td >Pack4</td>"
        + "<td >Description4</td><td >Mfg4</td>"
        + "<td >Batch4</td><td >Exp4</td>"
        + "<td >Mrp4</td><td >Amt4</td>"
        + "</tr><tr style='font-family:arial, sans-serif, monospace !important;' ><td >Qty5</td><td >Pack5</td><td >Description5</td><td >Mfg5</td><td >Batch5</td><td >Exp5</td><td >Mrp5</td><td>Amt5</td></tr><tr style='font-family:arial, sans-serif, monospace !important;' ><td >Qty6</td><td >Pack6</td><td >Description6</td><td >Mfg6</td><td >Batch6</td><td>Exp6</td><td >Mrp6</td><td >Amt6</td></tr><tr style='font-family:arial, sans-serif, monospace !important;' ><td >Qty7</td><td >Pack7</td><td >Description7</td><td >Mfg7</td><td >Batch7</td><td >Exp7</td><td >Mrp7</td><td >Amt7</td></tr><tbody style='text-align:center;word-wrap:break-word;font-size:11pt'><tr style='text-align:center'><td><label id='lb_qty'> </label><p></p></td><td><label id='lb_pack'> </label><p></p></td><td><label id='lb_description'><p></p> </label></td><td><label id='lb_mfg'></label><p></p> </td><td><label id='lb_batch'></label><p></p> </td><td><label id='lb_expiry_date'><p></p> </label></td><td><label id='lb_mrp'></label><p></p> </td><td><label id='lb_amount' > <p></p></label></td></tr><tr style='width: 100%;'><td ><table style='font-family:arial, sans-serif, monospace !important; width: 100%;font-size:9pt cellpadding=\"0\" cellspacing=\"0\"'><tr><td style=''>TIN No.</td><td><b><label id='lb_vat_no'>ABCDEFG</label></b></td><td style=''>L.B.T No:</td><td><b><label id='lb_lbt_no;'>lbt</label></b></td></tr></table></td></tr><tr style='width: 100%;'><td><table style='font-family:arial, sans-serif, monospace !important; width: 100%;font-size:9pt cellpadding=\"0\" cellspacing=\"0\"'><tr><td><label>Subject to Thane Jurisdiction. Goods once sold out  will not be taken</label></td><td></td><td>E. And O.</td><td>Regd. Pharmacist</td></tr></table></td></tr></tbody></table></td><td></td><td ></td></tr><tr style='width: 100%;' ><td><table style=' font-family:arial, sans-serif, monospace; !important;width: 100%;margin:0 0 0 0; cellpadding=\"0\"> cellspacing=\"0\"'> <tr><td style='font-size:9pt;border-bottom: 1px solid black;'>PLEASE CONSULT YOUR DOCTOR BEFORE USING MEDICINE</td><td > Total</td><td ><b><label id='lb_total_amount;'>total_amount</label></b></td></tr></table></td></tr>bill_footer</table></html>";



                
OutputStream os = new FileOutputStream(new File("c://temp//testpdf4.pdf"));

ITextRenderer renderer = new ITextRenderer();
renderer.setDocumentFromString(to_print);
renderer.layout();
renderer.createPDF(os);

os.close();
PrintPdf("c://temp//testpdf4.pdf" , "EPSON LQ-50 ESC/P2");
    }
    
    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }

    private static void PrintPdf(String filePath, String PrinterName) throws FileNotFoundException, IOException, PrinterException
    {
        //get the file in the program
        FileInputStream fileInputStream = new FileInputStream(filePath);
        
        // number of input bytes available in the InputStream
        byte[] pdfContent = new byte[fileInputStream.available()];
        
        // parameter 1: the destination to read from, parameter 2: start offset in destination array at which the data is written
        // parameter 3:number of bytes to read
            fileInputStream.read(pdfContent, 0, fileInputStream.available());
            
        // load the buffer with byte array,  modifications to the buffer will cause the array to be modified and vice versa         
            ByteBuffer buffer = ByteBuffer.wrap(pdfContent);
            
            //Creates a new PDFRenderer, uploads a document
             PDFFile pdfFile = new PDFFile(buffer);

             //creates a new PDFPrintPage object for the particular PDFFile 
            PDFPrintPage pages = new PDFPrintPage(pdfFile);

            //  Creates and returns a PrinterJob. 
            //PrinterJob is the file or set of files that have been submitted to be printed
            PrinterJob printJob = PrinterJob.getPrinterJob();

            PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
            
            Paper paper = new Paper();

            //paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
            paper.setSize(11*72, 7.2*72);
            paper.setImageableArea(16.5, 16.5, 11*72, 7.2*72);//first parameter height second parameter width
            pageFormat.setPaper(paper);
            //printJob.setPrintable(new BillPrinting(), pageFormat);
            Book book = new Book();
            
            book.append(pages, pageFormat, pdfFile.getNumPages());

            printJob.setPageable(book);

            PrintService[] printServices = PrinterJob.lookupPrintServices();

            for (int count = 0; count < printServices.length; ++count) {

                  if (PrinterName.equalsIgnoreCase(printServices[count].getName())) {

                        printJob.setPrintService(printServices[count]);

                        break;

                  }

            }

            printJob.print();

      }



 }


 

        
      
    
    
