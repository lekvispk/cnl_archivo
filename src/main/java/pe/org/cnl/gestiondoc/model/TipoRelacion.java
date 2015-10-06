package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_relacion database table.
 * 
 */
@Entity
@Table(name="tipo_relacion")
@NamedQuery(name="TipoRelacion.findAll", query="SELECT t FROM TipoRelacion t")
public class TipoRelacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTipoRelacion;

	private int estado;

	private String relDescripcion;

	//bi-directional many-to-one association to PersonaEscritura
	@OneToMany(mappedBy="tipoRelacion")
	private List<PersonaEscritura> personaEscrituras;

	public TipoRelacion() {
	}

	public int getIdTipoRelacion() {
		return this.idTipoRelacion;
	}

	public void setIdTipoRelacion(int idTipoRelacion) {
		this.idTipoRelacion = idTipoRelacion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getRelDescripcion() {
		return this.relDescripcion;
	}

	public void setRelDescripcion(String relDescripcion) {
		this.relDescripcion = relDescripcion;
	}

	public List<PersonaEscritura> getPersonaEscrituras() {
		return this.personaEscrituras;
	}

	public void setPersonaEscrituras(List<PersonaEscritura> personaEscrituras) {
		this.personaEscrituras = personaEscrituras;
	}

	public PersonaEscritura addPersonaEscritura(PersonaEscritura personaEscritura) {
		getPersonaEscrituras().add(personaEscritura);
		personaEscritura.setTipoRelacion(this);

		return personaEscritura;
	}

	public PersonaEscritura removePersonaEscritura(PersonaEscritura personaEscritura) {
		getPersonaEscrituras().remove(personaEscritura);
		personaEscritura.setTipoRelacion(null);

		return personaEscritura;
	}

}