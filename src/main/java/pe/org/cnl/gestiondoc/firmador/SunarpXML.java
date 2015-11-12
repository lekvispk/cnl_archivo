package pe.org.cnl.gestiondoc.firmador;

public class SunarpXML
{
  private String Cuo;
  private String CodigoActo;
  private String Kardex;
  private String CodigoNotaria;
  private String notaria;
  private String HashId;
  private String Fecha;
  private String AnioTramite;
  private String NumeroTramite;

  public String getCuo()
  {
    return this.Cuo;
  }

  public void setCuo(String cuo) {
    this.Cuo = cuo;
  }

  public String getCodigoActo() {
    return this.CodigoActo;
  }

  public void setCodigoActo(String codigoActo) {
    this.CodigoActo = codigoActo;
  }

  public String getKardex() {
    return this.Kardex;
  }

  public void setKardex(String kardex) {
    this.Kardex = kardex;
  }

  public String getCodigoNotaria() {
    return this.CodigoNotaria;
  }

  public void setCodigoNotaria(String codigoNotaria) {
    this.CodigoNotaria = codigoNotaria;
  }

  public String getHashId() {
    return this.HashId;
  }

  public void setHashId(String hashId) {
    this.HashId = hashId;
  }

  public String getFecha() {
    return this.Fecha;
  }

  public void setFecha(String fecha) {
    this.Fecha = fecha;
  }

  public String getAnioTramite() {
    return this.AnioTramite;
  }

  public void setAnioTramite(String anioTramite) {
    this.AnioTramite = anioTramite;
  }

  public String getNumeroTramite() {
    return this.NumeroTramite;
  }

  public void setNumeroTramite(String numeroTramite) {
    this.NumeroTramite = numeroTramite;
  }

  public String getTextoBidimensional() {
    String strTexto = this.Cuo + "," + this.CodigoActo + "," + this.CodigoNotaria + "," + this.Kardex + "," + this.Fecha + "," + this.notaria + "," + this.HashId;

    return strTexto;
  }

  public String getNotaria() {
    return this.notaria;
  }

  public void setNotaria(String notaria) {
    this.notaria = notaria;
  }
}