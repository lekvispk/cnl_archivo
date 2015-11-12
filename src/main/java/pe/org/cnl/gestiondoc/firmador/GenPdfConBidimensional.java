package pe.org.cnl.gestiondoc.firmador;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class GenPdfConBidimensional
{
  public static boolean Generar(EscrituraXML objSunarpXML, String strPdfInputPath, String strPdfOutputPath)
  {
    boolean blnGenerado = false;
    try {
      PdfReader reader = new PdfReader(new FileInputStream(strPdfInputPath));
      PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(strPdfOutputPath));

      BarcodePDF417 pdf417 = new BarcodePDF417();
      pdf417.setText(objSunarpXML.getTextoBidimensional());
      Image img = pdf417.getImage();

      float xscale = 2.0F;
      img.setAlignment(1);
      img.scalePercent(xscale * 100.0F, xscale * 100.0F);
      img.setAbsolutePosition((594.0F - (img.getWidth() * xscale)) / 2.0F, 842.0F - (img.getHeight() * xscale) - 10.0F);

      int total = reader.getNumberOfPages() + 1;
      System.out.println(" CANTIDAD DE HOJAS = " + total);
      for (int i = 1; i < total; ++i)
      {
        PdfContentByte over = stamper.getOverContent(i);
        over.addImage(img);
      }

      stamper.close();
      blnGenerado = true;
    } catch (Exception objE) {
      objE.printStackTrace();
      System.out.println("Error en generacion de pdf con Bidimensional: " + objE.getMessage());
    }

    return blnGenerado;
  }
}