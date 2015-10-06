package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the persona_escritura database table.
 * 
 */
@Entity
@Table(name="persona_escritura")
@NamedQuery(name="PersonaEscritura.findAll", query="SELECT p FROM PersonaEscritura p")
public class PersonaEscritura implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonaEscrituraPK id;

	private int estado;

	//bi-directional many-to-one association to Escritura
	@ManyToOne
	@JoinColumn(name="id_escritura",insertable=false, updatable=false)
	private Escritura escritura;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona",insertable=false, updatable=false)
	private Persona persona;

	//bi-directional many-to-one association to TipoRelacion
	@ManyToOne
	@JoinColumn(name="idTipoRelacion")
	private TipoRelacion tipoRelacion;

	public PersonaEscritura() {
	}

	public PersonaEscrituraPK getId() {
		return this.id;
	}

	public void setId(PersonaEscrituraPK id) {
		this.id = id;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Escritura getEscritura() {
		return this.escritura;
	}

	public void setEscritura(Escritura escritura) {
		this.escritura = escritura;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoRelacion getTipoRelacion() {
		return this.tipoRelacion;
	}

	public void setTipoRelacion(TipoRelacion tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

}