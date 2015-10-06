package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.org.cnl.gestiondoc.util.Utiles;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_persona")
	private int idPersona;

	@Column(name="ape_materno")
	private String apeMaterno;

	@Column(name="ape_paterno")
	private String apePaterno;

	private String cargo;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;

	private String grado;

	private String nombre;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	@Column(name="num_documento")
	private String numDocumento;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to DocumentoIdentidad
	@ManyToOne
	@JoinColumn(name="id_documento")
	private DocumentoIdentidad documentoIdentidad;

	//bi-directional many-to-one association to PersonaEscritura
	@OneToMany(mappedBy="persona")
	private List<PersonaEscritura> personaEscrituras;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="persona")
	private List<Usuario> secUsuarios;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="persona")
	private List<Solicitud> solicituds;

	public Persona() {
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getApeMaterno() {
		return this.apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getApePaterno() {
		return this.apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getGrado() {
		return this.grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNumDocumento() {
		return this.numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public DocumentoIdentidad getDocumentoIdentidad() {
		return this.documentoIdentidad;
	}

	public void setDocumentoIdentidad(DocumentoIdentidad documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public List<PersonaEscritura> getPersonaEscrituras() {
		return this.personaEscrituras;
	}

	public void setPersonaEscrituras(List<PersonaEscritura> personaEscrituras) {
		this.personaEscrituras = personaEscrituras;
	}

	public PersonaEscritura addPersonaEscritura(PersonaEscritura personaEscritura) {
		getPersonaEscrituras().add(personaEscritura);
		personaEscritura.setPersona(this);

		return personaEscritura;
	}

	public PersonaEscritura removePersonaEscritura(PersonaEscritura personaEscritura) {
		getPersonaEscrituras().remove(personaEscritura);
		personaEscritura.setPersona(null);

		return personaEscritura;
	}

	public List<Usuario> getSecUsuarios() {
		return this.secUsuarios;
	}

	public void setSecUsuarios(List<Usuario> secUsuarios) {
		this.secUsuarios = secUsuarios;
	}

	public Usuario addSecUsuario(Usuario secUsuario) {
		getSecUsuarios().add(secUsuario);
		secUsuario.setPersona(this);

		return secUsuario;
	}

	public Usuario removeSecUsuario(Usuario secUsuario) {
		getSecUsuarios().remove(secUsuario);
		secUsuario.setPersona(null);

		return secUsuario;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setPersona(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setPersona(null);

		return solicitud;
	}
	public String getNombreCompletoCargo(){
		return
		Utiles.nullToBlank( this.grado ) + " " +
		Utiles.nullToBlank( this.nombreCompleto) + " " +
		Utiles.nullToBlank( this.cargo );
	}
}