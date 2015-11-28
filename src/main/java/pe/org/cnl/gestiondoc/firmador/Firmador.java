package pe.org.cnl.gestiondoc.firmador;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.Enumeration;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

public class Firmador
{
  private KeyStore ks = null;
  private String strTempPath;
  private EscrituraXML objSunarpXML;
  private String notarioCert;

  public static void main(String[] arg)
  {
    try
    {
      EscrituraXML xml = new EscrituraXML();
      xml.setAnioTramite("2012");
      xml.setCodigoActo("022255");
      xml.setCodigoNotaria("N00003");
      xml.setCuo("0222112222");
      xml.setFecha("02/02/2012");
      xml.setHashId("eccbc87e4b5ce2fe28308fd9f2a7baf3");
      xml.setKardex("K3337-10-NC");
      xml.setNumeroTramite("022645");

      Firmador f = new Firmador("F:\\firma_05_2012\\");
      f.cargaCertificados();
      f.setNotarioCert("Pedro German Nunez Palomino");

      f.setObjSunarpXML(xml);

      f.firmar("F:\\firma_05_2012\\K3337-10-NC.pdf");

      System.out.println("-TERMINA EJECUCION-");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public Firmador()
  {
  }

  public Firmador(String temp)
  {
    try
    {
      this.strTempPath = temp;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getNotarioCert()
  {
    return this.notarioCert; }

  public void setNotarioCert(String notario) {
    this.notarioCert = notario; }

  public String getStrTempPath() {
    return this.strTempPath; }

  public void setStrTempPath(String strTempPath) {
    this.strTempPath = strTempPath;
  }

  public void firmar(String rutaPdf) {
    try {
      byte[] pdf = IOUtils.toByteArray(new FileInputStream(rutaPdf));
      System.out.println("RTF CARGADO, llamo a firmador");
      Signer sg = new SimpleSigner();
      byte[] firmaParte = sg.firmar(pdf, this.notarioCert, this.ks, null, "SHA1withRSA");
      System.out.println("Firmador invocado aunque nose para que lo uso ");
      System.out.println(firmaParte.toString());

      String strSha1 = GeneradorHash.getSha1FromFilePath(rutaPdf);
      getObjSunarpXML().setHashId(strSha1);

      String PdfWithBidTempPath = this.strTempPath + "/" + getObjSunarpXML().getKardex() + "_bid.pdf";
      boolean blnPdfBidGenerado = GenPdfConBidimensional.Generar(getObjSunarpXML(), rutaPdf, PdfWithBidTempPath);

      if (blnPdfBidGenerado) {
        System.out.println("Firma en PDF lista");
        System.out.println("debo grabar el PDF en la BD o mandarlo a mostrar.");
        System.out.println(" " + PdfWithBidTempPath);
      } else {
        System.out.println("Error");
        System.out.println("Documento bidimensional no pudo ser generado, revisar servicios soffice...");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public byte[] firmar() {
    try {
      byte[] pdf = this.objSunarpXML.getArchivo();
      System.out.println(" - PDF CARGADO, llamo a firmador " + this.notarioCert);
      Signer sg = new SimpleSigner();
      byte[] firmaParte = sg.firmar(pdf, this.notarioCert, this.ks, null, "SHA1withRSA");
      System.out.println(" - Firmador invocado aunque nose para que lo uso ");
      System.out.println(firmaParte.toString());

      InputStream input = new ByteArrayInputStream(pdf);
      String ruta = Utiles.guardaImagenEnDiscoTemp(input);
      String strSha1 = GeneradorHash.getSha1FromFilePath(ruta);
      getObjSunarpXML().setHashId(strSha1);

      String PdfWithBidTempPath = getObjSunarpXML().getKardex() + "_bid.pdf";
      boolean blnPdfBidGenerado = GenPdfConBidimensional.Generar(getObjSunarpXML(), ruta, PdfWithBidTempPath);

      if (blnPdfBidGenerado) {
        System.out.println(" - Firma en PDF lista");
        System.out.println(" - debo grabar el PDF en la BD o mandarlo a mostrar.");
        System.out.println(" -  " + PdfWithBidTempPath);
        return IOUtils.toByteArray(new FileInputStream(PdfWithBidTempPath));
      }
      System.out.println(" - Error");
      System.out.println(" - Documento bidimensional no pudo ser generado, revisar servicios soffice...");
      return null;
    }
    catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }

  /**
   * Este metodo es el que firma con el certificado y agrega datos de  seguridad al PDF
   * @return
   */
  public byte[] firmarConCertificado() {
	  System.out.println("recibo PDF y devuelvo un nuevo PDF firmado");
		try {
			byte[] pdf = this.objSunarpXML.getArchivo();
			KeyStore ks = this.ks;
			pintarAlias();
			//ks.load(new FileInputStream(PATH+"cherreracred.p12"), "cherrerap12".toCharArray());
			String alias = this.notarioCert ;
			PrivateKey key = (PrivateKey)ks.getKey(alias, "cherrerap12".toCharArray());
			Certificate[] chain = ks.getCertificateChain(alias);
			PdfReader reader = new PdfReader( pdf );
			
			String nombre = this.objSunarpXML.getNombreArchivo();
			nombre = nombre.substring(0, nombre.length()-4 );
			nombre = nombre + "_" + new Date().getTime()+".pdf";
			
			//String nombrePdfFirmado = nombre;
			String PdfWithBidTempPath = getObjSunarpXML().getKardex() + "_bid.pdf";
			
			FileOutputStream fout = new FileOutputStream( PdfWithBidTempPath );
			
			/* COLOCAR RECTANGULO CON FIRMA VISIBLE */
			PdfStamper stp = PdfStamper.createSignature(reader, fout, '\0');			
			PdfSignatureAppearance sap = stp.getSignatureAppearance();
			sap.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
			sap.setReason("Soy el autor del Documento");
			sap.setLocation("Lima - Peru");
			//sap.setImage(Image.getInstance( PATH+"logo.gif"));
			sap.setVisibleSignature(new Rectangle(400, 700, 550, 750), 1, null);
			
			
			ExternalSignature es = new PrivateKeySignature(key, DigestAlgorithms.SHA256 , null);
		    ExternalDigest digest = new BouncyCastleDigest();
		    MakeSignature.signDetached(sap, digest, es, chain, null, null, null, 0, CryptoStandard.CMS);
		    
		    
		    System.out.println("Fin de la firma. OK");
		    return IOUtils.toByteArray(new FileInputStream(PdfWithBidTempPath));
			  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
  }
  
  public void cargaCertificados(){
    try{
      System.gc();
      System.out.println(" - Cargando certificados ...");
      KeyStoreGenerator ksg = new KeyStoreGenerator();
      this.ks = ksg.crearKeyStore(0);
      System.out.println(" - Almacen de llaves cargado ...");
      pintarAlias();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void pintarAlias(){
	  System.out.println("pintarAlias");
	  try {
		  Enumeration<String> enumAlias = this.ks.aliases();
	      while (enumAlias.hasMoreElements()) {
	        String ali = enumAlias.nextElement();
	        if (this.ks.isKeyEntry(ali))
	          System.out.println(ali);
	      }	
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  public boolean Generar(SunarpXML objSunarpXML, String strPdfInputPath, String strPdfOutputPath) {
    System.out.println(" - inicio genera");
    boolean blnGenerado = false;
    try {
      System.out.println(" - inicio carga pdf");
      PdfReader reader = new PdfReader(new FileInputStream(strPdfInputPath));
      PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(strPdfOutputPath));
      System.out.println(" - inicio cre instancia pdf");
      BarcodePDF417 pdf417 = new BarcodePDF417();
      System.out.println(" - llena datos pdf");
      pdf417.setText(objSunarpXML.getTextoBidimensional());

      System.out.println(" - inicio obtiene inagen");
      Image img = pdf417.getImage();

      System.out.println("  - IMAGEN ");

      float xscale = 2.0F;
      img.setAlignment(1);
      img.scalePercent(xscale * 100.0F, xscale * 100.0F);
      img.setAbsolutePosition((594.0F - (img.getWidth() * xscale)) / 2.0F, 842.0F - (img.getHeight() * xscale) - 10.0F);

      System.out.println(" - copio imagen en disco");
      Utiles.guardaImagenEnDisco(img.getRawData(), "", "F:\\logs\\");
      System.out.println(" - fin copio imagen en disco");

      int total = reader.getNumberOfPages() + 1;
      for (int i = 1; i < total; ++i) {
        PdfContentByte under = stamper.getUnderContent(i);
        under.addImage(img);
      }
      System.out.println("  - stamper cloe ");
      stamper.close();
      blnGenerado = true;
      System.out.println("  - genrado " + blnGenerado);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(" - Error en generacion de pdf con Bidimensional: " + e.getMessage());
    }

    return blnGenerado;
  }

  public EscrituraXML getObjSunarpXML()
  {
    return this.objSunarpXML;
  }

  public void setObjSunarpXML(EscrituraXML objSunarpXML)
  {
    this.objSunarpXML = objSunarpXML;
  }
}