package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the solicitud_tramite database table.
 * 
 */
@Entity
@Table(name="solicitud_tramite")
@NamedQuery(name="SolicitudTramite.findAll", query="SELECT s FROM SolicitudTramite s")
public class SolicitudTramite implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SolicitudTramitePK id;

	@Lob
	private byte[] archivo;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_solicitud")
	private Date fecSolicitud;

	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="idsolicitud",insertable=false, updatable=false)
	private Solicitud solicitud;

	//bi-directional many-to-one association to Escritura
	@ManyToOne
	@JoinColumn(name="id_escritura",insertable=false, updatable=false)
	private Escritura escritura;

	public SolicitudTramite() {
	}

	public SolicitudTramitePK getId() {
		return this.id;
	}

	public void setId(SolicitudTramitePK id) {
		this.id = id;
	}

	public byte[] getArchivo() {
		return this.archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecSolicitud() {
		return this.fecSolicitud;
	}

	public void setFecSolicitud(Date fecSolicitud) {
		this.fecSolicitud = fecSolicitud;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Escritura getEscritura() {
		return this.escritura;
	}

	public void setEscritura(Escritura escritura) {
		this.escritura = escritura;
	}

}