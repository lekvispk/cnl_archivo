package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the documento_identidad database table.
 * 
 */
@Entity
@Table(name="documento_identidad")
@NamedQuery(name="DocumentoIdentidad.findAll", query="SELECT d FROM DocumentoIdentidad d")
public class DocumentoIdentidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_documento")
	private int idDocumento;

	private String abreviacion;

	private int estado;

	private String nombre;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="documentoIdentidad")
	private List<Persona> personas;

	public DocumentoIdentidad() {
	}

	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getAbreviacion() {
		return this.abreviacion;
	}

	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
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

	public List<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Persona addPersona(Persona persona) {
		getPersonas().add(persona);
		persona.setDocumentoIdentidad(this);

		return persona;
	}

	public Persona removePersona(Persona persona) {
		getPersonas().remove(persona);
		persona.setDocumentoIdentidad(null);

		return persona;
	}

}