package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the actos_escritura database table.
 * 
 */
@Embeddable
public class ActosEscrituraPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_acto", insertable=false, updatable=false)
	private int idActo;

	@Column(name="id_escritura", insertable=false, updatable=false)
	private int idEscritura;

	public ActosEscrituraPK() {
	}
	public int getIdActo() {
		return this.idActo;
	}
	public void setIdActo(int idActo) {
		this.idActo = idActo;
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
		if (!(other instanceof ActosEscrituraPK)) {
			return false;
		}
		ActosEscrituraPK castOther = (ActosEscrituraPK)other;
		return 
			(this.idActo == castOther.idActo)
			&& (this.idEscritura == castOther.idEscritura);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idActo;
		hash = hash * prime + this.idEscritura;
		
		return hash;
	}
}