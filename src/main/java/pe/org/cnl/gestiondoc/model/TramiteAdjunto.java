package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tramite_adjuntos database table.
 * 
 */
@Entity
@Table(name="tramite_adjuntos")
@NamedQuery(name="TramiteAdjunto.findAll", query="SELECT t FROM TramiteAdjunto t")
public class TramiteAdjunto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_adjunto")
	private int idAdjunto;

	private int estado;

	private String nombre;

	private String mimetype;
	
	//bi-directional many-to-one association to Tramite
	@ManyToOne
	@JoinColumn(name="id_tramite")
	private Tramite tramite;

	@Transient
	private byte[] archivo;
	
	public TramiteAdjunto() {
	}

	public int getIdAdjunto() {
		return this.idAdjunto;
	}

	public void setIdAdjunto(int idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tramite getTramite() {
		return this.tramite;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

}