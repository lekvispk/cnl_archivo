package pe.org.cnl.gestiondoc.test.firmador;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Formatter;

public class GeneradorHash
{
  public static String getSha1FromFilePath(String strFile)
  {
    String strHash = "";
    try {
      MessageDigest sha1 = MessageDigest.getInstance("SHA1");
      strHash = CalcularHash(sha1, strFile);
    } catch (Exception e) {
    }
    return strHash;
  }

  private static String CalcularHash(MessageDigest algoritmo, String fileName)
    throws Exception
  {
    FileInputStream fis = new FileInputStream(fileName);
    BufferedInputStream bis = new BufferedInputStream(fis);
    DigestInputStream dis = new DigestInputStream(bis, algoritmo);

    while (dis.read() != -1);
    byte[] hash = algoritmo.digest();

    return byteArray2Hex(hash);
  }

  private static String byteArray2Hex(byte[] hash) {
    Formatter formateador = new Formatter();
    for (byte b : hash) {
      formateador.format("%02X", new Object[] { Byte.valueOf(b) });
    }
    return formateador.toString();
  }
}