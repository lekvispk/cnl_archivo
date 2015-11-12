package pe.org.cnl.gestiondoc.firmador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class Utiles
{
  public static final String RUTA_TEMPORAL = "/temp";
  public static String FORMATO_FECHA_LARGE = "dd/MM/yyyy hh:mm:ss";
  public static String FORMATO_FECHA_CORTA = "dd/MM/yyyy";
  public static String FORMATO_FECHA_CORTA_MYSQL = "dd-MM-yyyy";

  private static final String[] UNIDADES = { "", "UN ", "DOS ", "TRES ", "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ", "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE" };

  private static final String[] DECENAS = { "VENTI", "TREINTA ", "CUARENTA ", "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ", "CIEN " };

  private static final String[] CENTENAS = { "CIENTO ", "DOSCIENTOS ", "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS " };

  public static String nullToBlank(Object texto)
  {
    try
    {
      if (texto == null) {
        return "";
      }
      if (texto.toString().trim().equals("null")) {
        return "";
      }
      return texto.toString().trim(); } catch (Exception e) {
    }
    return "";
  }

  public static Integer nullToZero(Object texto)
  {
    try
    {
      if (texto == null)
        return Integer.valueOf(0);
    } catch (Exception e) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(Integer.parseInt(texto.toString()));
  }

  public static Long nullToZeroL(Object texto) {
    try {
      if (texto == null)
        return Long.valueOf(0L);
      return Long.valueOf(texto.toString()); } catch (Exception e) {
    }
    return Long.valueOf(0L);
  }

  public static GregorianCalendar stringToCalendar(String fecha, String formato)
    throws Exception
  {
    fecha = nullToBlank(fecha);
    GregorianCalendar gc = new GregorianCalendar();
    SimpleDateFormat df = new SimpleDateFormat(formato);
    gc.setTime(df.parse(fecha));
    return gc;
  }

  public static String CalendarToString(Calendar fecha, String formato) throws Exception {
    if (nullToBlank(fecha).equals("")) {
      return "";
    }
    SimpleDateFormat df = new SimpleDateFormat(formato);
    return df.format(fecha.getTime());
  }

  public static String DateToString(Date fecha, String formato) throws Exception {
    if (nullToBlank(fecha).equals("")) {
      return "";
    }
    SimpleDateFormat df = new SimpleDateFormat(formato);
    return df.format(fecha);
  }

  public static String ValidaDate(String fecha, String formato) throws Exception {
    fecha = nullToBlank(fecha);
    GregorianCalendar gc = new GregorianCalendar();
    SimpleDateFormat df = new SimpleDateFormat(formato);
    gc.setTime(df.parse(fecha));
    return df.format(gc.getTime());
  }

  public static Date stringToDate(String fecha, String formato) throws Exception {
    fecha = nullToBlank(fecha);
    GregorianCalendar gc = new GregorianCalendar();
    SimpleDateFormat df = new SimpleDateFormat(formato);
    try {
      gc.setTime(df.parse(fecha));
      return gc.getTime();
    } catch (ParseException e) {
      System.out.println(e.getMessage()); }
    return null;
  }

  public static void guardaImagenEnDisco(byte[] outBits, String extra, String path)
  {
    try
    {
      byte[] bytes = outBits;
      InputStream input = new ByteArrayInputStream(bytes);
      guardaImagenEnDisco(input, extra, path);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String guardaImagenEnDisco(InputStream imagenBuffer, String extra, String path)
    throws Exception
  {
    File fichero = null;
    String nombre = "";
    try {
      Calendar fe = new GregorianCalendar();
      nombre = extra + fe.getTimeInMillis() + ".jpg";
      fichero = new File(path + "\\temp\\" + nombre);

      BufferedInputStream in = new BufferedInputStream(imagenBuffer);
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fichero));

      byte[] bytes = new byte[8096];
      int len = 0;
      while ((len = in.read(bytes)) > 0) {
        out.write(bytes, 0, len);
      }
      out.flush();
      out.close();
      in.close();
      System.out.println("archivo grabado en : " + fichero.getAbsolutePath());
    }
    catch (Exception e) {
      System.out.println("Error al escribir en disco " + e.getMessage());
    }
    return "temp/" + nombre;
  }

  public static String guardaImagenEnDiscoTemp(InputStream imagenBuffer) throws Exception {
    File fichero = null;
    String nombre = "";
    try {
      Calendar fe = new GregorianCalendar();
      nombre = fe.getTimeInMillis() + ".pdf";
      fichero = new File(nombre);

      BufferedInputStream in = new BufferedInputStream(imagenBuffer);
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fichero));

      byte[] bytes = new byte[8096];
      int len = 0;
      while ((len = in.read(bytes)) > 0) {
        out.write(bytes, 0, len);
      }
      out.flush();
      out.close();
      in.close();
      System.out.println("archivo grabado en : " + fichero.getAbsolutePath());
    }
    catch (Exception e) {
      System.out.println("Error al escribir en disco " + e.getMessage());
    }
    return fichero.getAbsolutePath();
  }

  public static String copy(InputStream in, String ruta, String extension)
    throws Exception
  {
    GregorianCalendar now = new GregorianCalendar();
    String tiempo = "" + now.getTimeInMillis();
    String nombre = ruta + File.separator + "up_" + tiempo + "." + extension;
    OutputStream out = new FileOutputStream(nombre);
    byte[] buf = new byte[1024];
    int len;
    while ((len = in.read(buf)) > 0)
    { 
      out.write(buf, 0, len);
    }
    in.close();
    out.close();
    return "up_" + tiempo + "." + extension;
  }

  public static String descripcionSexo(String sexo) throws Exception
  {
    if (nullToBlank(sexo).equals("F")) return "Femenino";
    if (nullToBlank(sexo).equals("M")) return "Masculino";
    return "";
  }

  public static String formatoDecimalPunto(String numero)
  {
    if (!(nullToBlank(numero).equals(""))) {
      numero = numero.trim();
      String s = "#########.##";
      DecimalFormatSymbols dformater_rules = new DecimalFormatSymbols();
      dformater_rules.setDecimalSeparator('.');
      DecimalFormat decimalFormat = new DecimalFormat(s, dformater_rules);
      decimalFormat.setMaximumFractionDigits(2);
      decimalFormat.setMinimumFractionDigits(2);
      System.out.println(" 177 --> " + numero);
      Double num = Double.valueOf(Double.parseDouble(numero));
      return decimalFormat.format(num);
    }
    return "";
  }

  public static String hashMd5(String palabra)
  {
    MessageDigest md = null;
    StringBuffer h = null;
    try {
      md = MessageDigest.getInstance("MD5");
      byte[] b = md.digest(palabra.getBytes());
      int size = b.length;
      h = new StringBuffer(size);
      for (int i = 0; i < size; ++i) {
        int u = b[i] & 0xFF;
        if (u < 16)
          h.append("0" + Integer.toHexString(u));
        else
          h.append(Integer.toHexString(u));
      }
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return h.toString();
  }

  public static boolean validaNotNullenLista(List lista, int i)
  {
    try
    {
      return (lista.get(i) != null);
    }
    catch (IndexOutOfBoundsException e)
    {
      return false; } catch (Exception ez) {
    }
    return false;
  }

  public static Calendar dateToCalendar(Date fecha, String formato) throws Exception
  {
    if (nullToBlank(fecha).equals("")) {
      return null;
    }
    SimpleDateFormat df = new SimpleDateFormat(formato);
    String text = df.format(fecha);
    return stringToCalendar(text, formato);
  }

  public static int diasParaFecha(Date fechafin) {
    try {
      Calendar hoy = new GregorianCalendar();
      Calendar fin = dateToCalendar(fechafin, "dd/MM/yyyy");
      long diffMillis = fin.getTimeInMillis() - hoy.getTimeInMillis();
      Long diffDays = Long.valueOf(diffMillis / 86400000L);
      return diffDays.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public static String completaDigitos(Integer maximo, String original, String digito) {
    if (original.length() < maximo.intValue())
      return completaDigitos(maximo, digito + original, digito);
    return original;
  }

  public static String numberToText(BigDecimal numero, String moneda) {
    System.out.println(" BORRAR : " + numero.toString());
    return convertNumberToLetter(numero.toString(), moneda);
  }

  public static String convertNumberToLetter(String number, String moneda)
  {
    return convertNumberToLetter(Double.parseDouble(number), moneda);
  }

  public static String convertNumberToLetter(double number, String moneda)
    throws NumberFormatException
  {
    String converted = new String();

    double doubleNumber = number;

    if (doubleNumber > 999999999.0D) {
      throw new NumberFormatException("El numero es mayor de 999'999.999, no es posible convertirlo");
    }

    String[] splitNumber = String.valueOf(doubleNumber).replace('.', '#').split("#");

    int millon = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 8)) + String.valueOf(getDigitAt(splitNumber[0], 7)) + String.valueOf(getDigitAt(splitNumber[0], 6)));

    if (millon == 1)
      converted = "UN MILLON ";
    if (millon > 1) {
      converted = convertNumber(String.valueOf(millon)) + "MILLONES ";
    }

    int miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 5)) + String.valueOf(getDigitAt(splitNumber[0], 4)) + String.valueOf(getDigitAt(splitNumber[0], 3)));

    if (miles == 1)
      converted = converted + "MIL ";
    if (miles > 1) {
      converted = converted + convertNumber(String.valueOf(miles)) + "MIL ";
    }

    int cientos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 2)) + String.valueOf(getDigitAt(splitNumber[0], 1)) + String.valueOf(getDigitAt(splitNumber[0], 0)));

    if (cientos == 1) {
      converted = converted + "UN";
    }
    if (millon + miles + cientos == 0)
      converted = converted + "CERO";
    if (cientos > 1) {
      converted = converted + convertNumber(String.valueOf(cientos));
    }

    int centavos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[1], 2)) + String.valueOf(getDigitAt(splitNumber[1], 1)) + String.valueOf(getDigitAt(splitNumber[1], 0)));

    if (centavos == 1)
      converted = converted + " 01/100 ";
    if ((centavos > 1) && (centavos < 10))
      converted = converted + " CON 0" + centavos;
    if (centavos > 1)
      converted = converted + " CON " + centavos;
    converted = converted + "/100 ";

    converted = converted + moneda.toUpperCase();
    return converted;
  }

  private static String convertNumber(String number)
  {
    if (number.length() > 3) {
      throw new NumberFormatException("La longitud maxima debe ser 3 digitos");
    }

    String output = new String();
    if (getDigitAt(number, 2) != 0) {
      output = CENTENAS[(getDigitAt(number, 2) - 1)];
    }
    int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1)) + String.valueOf(getDigitAt(number, 0)));

    if (k <= 20) {
      output = output + UNIDADES[k];
    }
    else if ((k > 30) && (getDigitAt(number, 0) != 0)) {
      output = output + DECENAS[(getDigitAt(number, 1) - 2)] + "Y " + UNIDADES[getDigitAt(number, 0)];
    }
    else {
      output = output + DECENAS[(getDigitAt(number, 1) - 2)] + UNIDADES[getDigitAt(number, 0)];
    }

    if ((getDigitAt(number, 2) == 1) && (k == 0)) {
      output = "CIEN";
    }
    return output;
  }

  private static int getDigitAt(String origin, int position)
  {
    if ((origin.length() > position) && (position >= 0))
      return (origin.charAt(origin.length() - position - 1) - '0');
    return 0;
  }

  public static String obtenerIp() {
    try {
      InetAddress address = InetAddress.getLocalHost();
      return address.getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return "null";
  }

  public static String obtenerMacAddress() {
    String clave = "";
    try {
      InetAddress address = InetAddress.getLocalHost();
      NetworkInterface red = NetworkInterface.getByInetAddress(address);
      Formatter formatter = null;
      String format = "%02X%s";
      byte[] mac = red.getHardwareAddress();
      for (int i = 0; i < mac.length; ++i) {
        if ((formatter == null) || (formatter.locale() != Locale.getDefault())) {
          formatter = new Formatter();
        }
        formatter.format(Locale.getDefault(), format, new Object[] { Byte.valueOf(mac[i]), (i < mac.length - 1) ? "-" : "" });
        clave = formatter.toString();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return clave;
  }
}