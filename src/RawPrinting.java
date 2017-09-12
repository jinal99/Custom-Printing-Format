package test;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PageRanges;
 
 
 class RawPrinting implements Printable {
 
    private PrintService printService;
    private static String text;
  
    public static void main(String[] args) {
        
        RawPrinting lt = new RawPrinting();
        System.out.println("here1\u001B\u006A\u00D8here2");
        lt.printString("here1\u001B\u006A\u00D8here2");
        
    }
 
    public void printString(String input) {
 
        text = input;
        printService = findPrintService("EPSON LQ-50 ESC/P2");
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new PageRanges(1, 1));
        aset.add(new Copies(1));
         
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(new RawPrinting());
 
        try {
            
            printJob.setPrintService(printService);
            printJob.print(aset);
        } catch (PrinterException err) {
            System.err.println(err);
        }
    }
 
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        g.drawString(String.valueOf(this.text), 14, 14);
        return PAGE_EXISTS;
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
}
