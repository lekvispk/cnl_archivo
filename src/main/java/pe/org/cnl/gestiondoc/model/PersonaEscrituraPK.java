package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the persona_escritura database table.
 * 
 */
@Embeddable
public class PersonaEscrituraPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_escritura", insertable=false, updatable=false)
	private int idEscritura;

	@Column(name="id_persona", insertable=false, updatable=false)
	private int idPersona;

	public PersonaEscrituraPK() {
	}
	public int getIdEscritura() {
		return this.idEscritura;
	}
	public void setIdEscritura(int idEscritura) {
		this.idEscritura = idEscritura;
	}
	public int getIdPersona() {
		return this.idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonaEscrituraPK)) {
			return false;
		}
		PersonaEscrituraPK castOther = (PersonaEscrituraPK)other;
		return 
			(this.idEscritura == castOther.idEscritura)
			&& (this.idPersona == castOther.idPersona);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEscritura;
		hash = hash * prime + this.idPersona;
		
		return hash;
	}
}