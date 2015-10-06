package pe.org.cnl.gestiondoc.test.firmador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

public class KeyStoreGenerator
{
  public static final int WINDOWS_MY = 0;
  public static final int WINDOWS_ROOT = 1;
  public static final int JCEKS = 2;
  public static final int JKS = 3;
  public static final int PKCS12 = 4;

  private String escogerTipo(int tipo)
  {
    String tipoS = "";
    switch (tipo)
    {
    case 0:
      tipoS = "WINDOWS-MY"; break;
    case 1:
      tipoS = "WINDOWS-ROOT"; break;
    case 2:
      tipoS = "JCEKS"; break;
    case 3:
      tipoS = "JKS"; break;
    case 4:
      tipoS = "PKCS12";
    }
    return tipoS;
  }

  public KeyStore crearKeyStore() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException
  {
    KeyStore ks = null;
    ks = KeyStore.getInstance(KeyStore.getDefaultType());
    ks.load(null, null);
    return ks;
  }

  public KeyStore crearKeyStore(int tipo) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException
  {
    KeyStore ks = null;
    ks = KeyStore.getInstance(escogerTipo(tipo));
    ks.load(null, null);
    return ks;
  }

  public KeyStore crearKeyStore(int tipo, String fileStore, char[] password) throws KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException
  {
    KeyStore ks = null;
    ks = KeyStore.getInstance(escogerTipo(tipo));
    ks.load(new FileInputStream(fileStore), password);
    return ks;
  }

  public KeyStore crearKeyStore(int tipo, InputStream storeStream, char[] password) throws KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException
  {
    KeyStore ks = null;
    ks = KeyStore.getInstance(escogerTipo(tipo));
    ks.load(storeStream, password);
    return ks;
  }

  public KeyStore crearKeyStore(int tipo, String provider, InputStream storeStream, char[] pass) throws KeyStoreException, NoSuchProviderException, IOException, NoSuchAlgorithmException, CertificateException
  {
    KeyStore ks = null;
    ks = KeyStore.getInstance(escogerTipo(tipo), provider);
    ks.load(storeStream, pass);
    return ks;
  }
}