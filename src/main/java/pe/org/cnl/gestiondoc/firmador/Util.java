package pe.org.cnl.gestiondoc.firmador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.net.ssl.HttpsURLConnection;

public class Util
{
  public static void validarCertificado(KeyStore keyStore, String alias)
    throws Exception
  {
    try
    {
      Certificate cert = keyStore.getCertificate(alias);

      X509Certificate cX509 = (X509Certificate)cert;

      cX509.checkValidity(new Date());
    } catch (KeyStoreException ex) {
      ex.printStackTrace();
      throw new Exception("No se pudo recuperar certificado");
    } catch (CertificateExpiredException ex) {
      ex.printStackTrace();
      throw new Exception("No se pudo realizar la firma, certificado ha expirado");
    } catch (CertificateNotYetValidException ex) {
      ex.printStackTrace();
      throw new Exception("No se pudo realizar la firma, certificado aun no es v�lido");
    }
  }

  public static Object sendRequest(String path, Object obj)
    throws MalformedURLException, IOException, ProtocolException, ClassNotFoundException
  {
    Object objResp = null;

    URL urlServlet = new URL(path);
    URLConnection conServlet = urlServlet.openConnection();

    objResp = (conServlet instanceof HttpURLConnection) ? sendHttp((HttpURLConnection)conServlet, obj) : sendHttps((HttpsURLConnection)conServlet, obj);

    return objResp;
  }

  private static Object sendHttp(HttpURLConnection con, Object obj) throws ProtocolException, IOException, ClassNotFoundException
  {
    Object objResp = null;
    con.setRequestMethod("POST");

    con.setDoOutput(true);
    con.setDoInput(true);

    con.setUseCaches(false);

    con.setRequestProperty("Content-Type", "application/octet-stream");

    ObjectOutputStream outputToServlet = new ObjectOutputStream(con.getOutputStream());

    outputToServlet.writeObject(obj);
    outputToServlet.flush();
    outputToServlet.close();

    System.out.println("Http Applet to servlet completed ...");

    ObjectInputStream is = new ObjectInputStream(con.getInputStream());

    objResp = is.readObject();

    con.disconnect();

    return objResp;
  }

  private static Object sendHttps(HttpsURLConnection con, Object obj) throws ProtocolException, IOException, ClassNotFoundException {
    Object objResp = null;
    con.setRequestMethod("POST");

    con.setDoOutput(true);
    con.setDoInput(true);

    con.setUseCaches(false);

    con.setRequestProperty("Content-Type", "application/octet-stream");

    ObjectOutputStream outputToServlet = new ObjectOutputStream(con.getOutputStream());
    outputToServlet.writeObject(obj);
    outputToServlet.flush();
    outputToServlet.close();

    System.out.println("Https Applet to servlet completed ...");

    ObjectInputStream is = new ObjectInputStream(con.getInputStream());

    objResp = is.readObject();
    con.disconnect();

    return objResp;
  }
}