package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the solicitud_tramite database table.
 * 
 */
@Embeddable
public class SolicitudTramitePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idsolicitud;

	@Column(name="id_escritura", insertable=false, updatable=false)
	private int idEscritura;

	public SolicitudTramitePK() {
	}
	public int getIdsolicitud() {
		return this.idsolicitud;
	}
	public void setIdsolicitud(int idsolicitud) {
		this.idsolicitud = idsolicitud;
	}
	public int getIdEscritura() {
		return this.idEscritura;
	}
	public void setIdEscritura(int idEscritura) {
		this.idEscritura = idEscritura;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SolicitudTramitePK)) {
			return false;
		}
		SolicitudTramitePK castOther = (SolicitudTramitePK)other;
		return 
			(this.idsolicitud == castOther.idsolicitud)
			&& (this.idEscritura == castOther.idEscritura);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idsolicitud;
		hash = hash * prime + this.idEscritura;
		
		return hash;
	}
}