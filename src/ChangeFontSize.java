/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitesh
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 class ChangeFontSize {
     public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
         
         String file = "c://temp//testpdf2.pdf";
         String html = "<!DOCTYPE html><html><head><style>table, th, td {border: 1px solid black;border-collapse: collapse;\n" +
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
         Document doc = new Document(PageSize.A6, 0,0,0,0);
         PdfWriter.getInstance(doc, new FileOutputStream(file));
         doc.open();
         doc.add(new Paragraph(html, new Font(BaseFont.createFont(), 12)));
         doc.close();
     }
}
