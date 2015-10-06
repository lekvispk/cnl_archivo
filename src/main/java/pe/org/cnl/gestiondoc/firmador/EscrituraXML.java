package pe.org.cnl.gestiondoc.firmador;

import java.io.Serializable;

public class EscrituraXML implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Integer getIdEscritura() {
		return idEscritura;
	}

	public void setIdEscritura(Integer idEscritura) {
		this.idEscritura = idEscritura;
	}

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	private Integer idEscritura;
	private Integer idSolicitud;
	private String Cuo;
	private String CodigoActo;
	private String Kardex;
	private String CodigoNotaria;
	private String notaria;
	private String HashId;
	private String Fecha;
	private String AnioTramite;
	private String NumeroTramite;
	private byte[] archivo;
	private byte[] archivoFirmado;

	
	public byte[] getArchivoFirmado() {
		return archivoFirmado;
	}

	public void setArchivoFirmado(byte[] archivoFirmado) {
		this.archivoFirmado = archivoFirmado;
	}

	public String getCuo() {
		return Cuo;
	}

	public void setCuo(String cuo) {
		Cuo = cuo;
	}

	public String getCodigoActo() {
		return CodigoActo;
	}

	public void setCodigoActo(String codigoActo) {
		CodigoActo = codigoActo;
	}

	public String getKardex() {
		return Kardex;
	}

	public void setKardex(String kardex) {
		Kardex = kardex;
	}

	public String getCodigoNotaria() {
		return CodigoNotaria;
	}

	public void setCodigoNotaria(String codigoNotaria) {
		CodigoNotaria = codigoNotaria;
	}

	public String getHashId() {
		return HashId;
	}

	public void setHashId(String hashId) {
		HashId = hashId;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getAnioTramite() {
		return AnioTramite;
	}

	public void setAnioTramite(String anioTramite) {
		AnioTramite = anioTramite;
	}

	public String getNumeroTramite() {
		return NumeroTramite;
	}

	public void setNumeroTramite(String numeroTramite) {
		NumeroTramite = numeroTramite;
	}

	  public String getTextoBidimensional() {
	    String strTexto = this.Cuo + "," + this.CodigoActo + "," + this.CodigoNotaria + "," + this.Kardex + "," + this.Fecha + "," + this.notaria  + ","  + this.HashId;

	    return strTexto;
	  }

	public String getNotaria() {
		return notaria;
	}

	public void setNotaria(String notaria) {
		this.notaria = notaria;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	  
	  
}