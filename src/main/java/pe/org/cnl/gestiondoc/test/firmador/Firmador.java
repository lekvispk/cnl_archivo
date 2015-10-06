package pe.org.cnl.gestiondoc.test.firmador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.util.Enumeration;

import org.apache.commons.io.IOUtils;

import pe.org.cnl.gestiondoc.firmador.EscrituraXML;
import pe.org.cnl.gestiondoc.util.Utiles;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class Firmador {

	private KeyStore ks = null;
	private String strTempPath;
	private EscrituraXML objSunarpXML;	
	private String notarioCert;
	
	public static void main(String[] arg){
		try {
			
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
			
			f.setNotarioCert( "Pedro German Nunez Palomino" );	
			
			f.setObjSunarpXML( xml );
			
			f.firmar( "F:\\firma_05_2012\\K3337-10-NC.pdf" );
			
			System.out.println("-TERMINA EJECUCION-");
			
			/*f.Generar(xml, "F:\\logs\\intro.pdf", "F:\\logs\\salida.pdf");*/			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Firmador(){
		try {
			cargaCertificados();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Firmador( String temp ){
		try {
			cargaCertificados();
			this.strTempPath = temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public EscrituraXML getObjSunarpXML() {
		return objSunarpXML;
	}
	public void setObjSunarpXML(EscrituraXML objSunarpXML) {
		this.objSunarpXML = objSunarpXML;
	}
	public String getNotarioCert() {
		return notarioCert;
	}
	public void setNotarioCert(String notario) {
		this.notarioCert = notario;
	}
	public String getStrTempPath() {
		return strTempPath;
	}
	public void setStrTempPath(String strTempPath) {
		this.strTempPath = strTempPath;
	}

	public void firmar(String rutaPdf){
		try {
			
			byte[] pdf = IOUtils.toByteArray(new FileInputStream(rutaPdf));
			System.out.println( "RTF CARGADO, llamo a firmador" );
			Signer sg = new SimpleSigner();
			byte[] firmaParte = sg.firmar( pdf , this.notarioCert , ks, null, "SHA1withRSA");
			System.out.println( "Firmador invocado aunque nose para que lo uso " );
			System.out.println( firmaParte.toString()  );
			
			String strSha1 = GeneradorHash.getSha1FromFilePath( rutaPdf );
			objSunarpXML.setHashId( strSha1 );
			 
			String PdfWithBidTempPath = strTempPath + "/" + objSunarpXML.getKardex() + "_bid.pdf";
		    boolean blnPdfBidGenerado = GenPdfConBidimensional.Generar(objSunarpXML, rutaPdf, PdfWithBidTempPath);
		    
		    if (blnPdfBidGenerado) {
		    	  System.out.println("Firma en PDF lista");
		    	  System.out.println("debo grabar el PDF en la BD o mandarlo a mostrar.");
		    	  System.out.println(" " + PdfWithBidTempPath );
		    }else {
		        System.out.println("Error");
		        System.out.println("Documento bidimensional no pudo ser generado, revisar servicios soffice...");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void cargaCertificados(){
		try {
			System.gc();
			System.out.println("Cargando certificados ...");
			KeyStoreGenerator ksg = new KeyStoreGenerator();
		    ks = ksg.crearKeyStore(0);
		    System.out.println("Almacen de llaves cargado ...");
		    Enumeration enumAlias = ks.aliases();
		    System.out.println("Almacen de llaves Elementos ...");
		    while (enumAlias.hasMoreElements()) {
		    	String ali = (String)enumAlias.nextElement();
			    if (ks.isKeyEntry(ali)) {
			    	System.out.println(ali);
			    }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean Generar(EscrituraXML objSunarpXML, String strPdfInputPath, String strPdfOutputPath){
		System.out.println("inicio genera");
	    boolean blnGenerado = false;
	    try {
	    	System.out.println("inicio carga pdf");
	      PdfReader reader = new PdfReader(new FileInputStream(strPdfInputPath));
	      PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(strPdfOutputPath));
	      System.out.println("inicio cre instancia pdf");
	      BarcodePDF417 pdf417 = new BarcodePDF417();
	      System.out.println("llena datos pdf");
	      pdf417.setText(objSunarpXML.getTextoBidimensional());
	      
	      System.out.println("inicio obtiene inagen");
	      Image img = pdf417.getImage();
	      /*
	      System.out.println("copio imagen en disco");
	      Utiles.guardaImagenEnDisco(pdf417.getOutBits() , "", "F:\\logs\\");
	      System.out.println("fin copio imagen en disco");
	      */
	      
	      System.out.println(" IMAGEN ");
	      
	      float xscale = 2.0F;
	      img.setAlignment(1);
	      img.scalePercent(xscale * 100.0F, xscale * 100.0F);
	      img.setAbsolutePosition((594.0F - img.getWidth() * xscale) / 2.0F, 842.0F - img.getHeight() * xscale - 10.0F);

	      
	      System.out.println("copio imagen en disco");
	      Utiles.guardaImagenEnDisco( img.getRawData() , "", "F:\\logs\\");
	      System.out.println("fin copio imagen en disco");
	      
	      
	      
	      int total = reader.getNumberOfPages() + 1;
	      for (int i = 1; i < total; i++) {
	        PdfContentByte under = stamper.getUnderContent(i);
	        under.addImage(img);
	      }
	      System.out.println(" stamper cloe ");
	      stamper.close();
	      blnGenerado = true;
	      System.out.println(" genrado " + blnGenerado );
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	System.out.println("Error en generacion de pdf con Bidimensional: " + e.getMessage());
	    }

	    return blnGenerado;
	  }
	
	
	
}
