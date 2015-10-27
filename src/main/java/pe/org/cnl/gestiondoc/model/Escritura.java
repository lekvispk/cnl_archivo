package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the escritura database table.
 * 
 */
@Entity
@NamedQuery(name="Escritura.findAll", query="SELECT e FROM Escritura e")
public class Escritura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_escritura")
	private int idEscritura;

	@Column(name="anio_tomo")
	private int anioTomo;

	@Column(name="cantidad_fojas")
	private int cantidadFojas;

	private int estado;

	@Column(name="estado_escritura")
	private int estadoEscritura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_escritura")
	private Date fechaEscritura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;

	@Column(name="firmas_restantes")
	private int firmasRestantes;

	private String kardex;

	@Column(name="numero_doc")
	private String numeroDoc;

	@Column(name="numero_factura")
	private String numeroFactura;
	
	@Column(name="numero_fojas")
	private int numeroFojas;

	@Column(name="numero_folios")
	private String numeroFolios;

	@Column(name="numero_instrumento")
	private String numeroInstrumento;

	@Column(name="numero_minuta")
	private String numeroMinuta;
	
	@Column(name="numero_tomo")
	private int numeroTomo;

	@Column(name="tipo_fojas")
	private int tipoFojas;
	
	@Column(name="ubicacion_digital")
	private String ubicacionDigital;

	@Column(name="ubicacion_fisica")
	private String ubicacionFisica;
	
	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to ActosEscritura
	@OneToMany(mappedBy="escritura")
	private List<ActosEscritura> actosEscrituras;
	
	//bi-directional many-to-one association to Archivo
	@OneToMany(mappedBy="escritura" , fetch=FetchType.EAGER)
	private List<Archivo> archivos;

	//bi-directional many-to-one association to Notaria
	@ManyToOne
	@JoinColumn(name="id_notaria")
	private Notaria notaria;
	
	//bi-directional many-to-many association to TipoActo
	@ManyToMany
	@JoinTable(
		name="actos_escritura"
		, joinColumns={
			@JoinColumn(name="id_escritura")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_acto")
			}
		)
	private List<TipoActo> tipoActos;

	//bi-directional many-to-one association to PersonaEscritura
	@OneToMany(mappedBy="escritura")
	private List<PersonaEscritura> personaEscrituras;

	//bi-directional many-to-one association to SolicitudTramite
	@OneToMany(mappedBy="escritura")
	private List<SolicitudTramite> solicitudTramites;

	//bi-directional many-to-one association to Tramite
	@OneToMany(mappedBy="escritura")
	private List<Tramite> tramites;

	@Transient
	private Date tramFechaRegistro2;
	@Transient
	private int idPersona;
	@Transient
	private int idPersona2;
	@Transient
	private String comprador;
	@Transient
	private String vendedor;
	
	public Escritura() {
	}

	public Escritura( Integer idEscritura) {
		this.idEscritura=idEscritura;
	}

	public int getIdEscritura() {
		return this.idEscritura;
	}

	public void setIdEscritura(int idEscritura) {
		this.idEscritura = idEscritura;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getEstadoEscritura() {
		return this.estadoEscritura;
	}

	public void setEstadoEscritura(int estadoEscritura) {
		this.estadoEscritura = estadoEscritura;
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

	public int getFirmasRestantes() {
		return this.firmasRestantes;
	}

	public void setFirmasRestantes(int firmasRestantes) {
		this.firmasRestantes = firmasRestantes;
	}

	public String getKardex() {
		return this.kardex;
	}

	public void setKardex(String kardex) {
		this.kardex = kardex;
	}

	public String getNumeroDoc() {
		return this.numeroDoc;
	}

	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public String getNumeroFolios() {
		return this.numeroFolios;
	}

	public void setNumeroFolios(String numeroFolios) {
		this.numeroFolios = numeroFolios;
	}

	public String getNumeroInstrumento() {
		return this.numeroInstrumento;
	}

	public void setNumeroInstrumento(String numeroInstrumento) {
		this.numeroInstrumento = numeroInstrumento;
	}

	public String getNumeroMinuta() {
		return this.numeroMinuta;
	}

	public void setNumeroMinuta(String numeroMinuta) {
		this.numeroMinuta = numeroMinuta;
	}

	public int getTipoFojas() {
		return this.tipoFojas;
	}

	public void setTipoFojas(int tipoFojas) {
		this.tipoFojas = tipoFojas;
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

	public List<Archivo> getArchivos() {
		return this.archivos;
	}

	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}

	public Archivo addArchivo(Archivo archivo) {
		getArchivos().add(archivo);
		archivo.setEscritura(this);

		return archivo;
	}

	public Archivo removeArchivo(Archivo archivo) {
		getArchivos().remove(archivo);
		archivo.setEscritura(null);

		return archivo;
	}

	public Notaria getNotaria() {
		return this.notaria;
	}

	public void setNotaria(Notaria notaria) {
		this.notaria = notaria;
	}

	public List<PersonaEscritura> getPersonaEscrituras() {
		return this.personaEscrituras;
	}

	public void setPersonaEscrituras(List<PersonaEscritura> personaEscrituras) {
		this.personaEscrituras = personaEscrituras;
	}

	public PersonaEscritura addPersonaEscritura(PersonaEscritura personaEscritura) {
		getPersonaEscrituras().add(personaEscritura);
		personaEscritura.setEscritura(this);

		return personaEscritura;
	}

	public PersonaEscritura removePersonaEscritura(PersonaEscritura personaEscritura) {
		getPersonaEscrituras().remove(personaEscritura);
		personaEscritura.setEscritura(null);

		return personaEscritura;
	}

	public List<SolicitudTramite> getSolicitudTramites() {
		return this.solicitudTramites;
	}

	public void setSolicitudTramites(List<SolicitudTramite> solicitudTramites) {
		this.solicitudTramites = solicitudTramites;
	}

	public SolicitudTramite addSolicitudTramite(SolicitudTramite solicitudTramite) {
		getSolicitudTramites().add(solicitudTramite);
		solicitudTramite.setEscritura(this);

		return solicitudTramite;
	}

	public SolicitudTramite removeSolicitudTramite(SolicitudTramite solicitudTramite) {
		getSolicitudTramites().remove(solicitudTramite);
		solicitudTramite.setEscritura(null);

		return solicitudTramite;
	}

	public Date getTramFechaRegistro2() {
		return tramFechaRegistro2;
	}

	public void setTramFechaRegistro2(Date tramFechaRegistro2) {
		this.tramFechaRegistro2 = tramFechaRegistro2;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdPersona2() {
		return idPersona2;
	}

	public void setIdPersona2(int idPersona2) {
		this.idPersona2 = idPersona2;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public int getAnioTomo() {
		return anioTomo;
	}

	public void setAnioTomo(int anioTomo) {
		this.anioTomo = anioTomo;
	}

	public int getCantidadFojas() {
		return cantidadFojas;
	}

	public void setCantidadFojas(int cantidadFojas) {
		this.cantidadFojas = cantidadFojas;
	}

	public Date getFechaEscritura() {
		return fechaEscritura;
	}

	public void setFechaEscritura(Date fechaEscritura) {
		this.fechaEscritura = fechaEscritura;
	}

	public int getNumeroFojas() {
		return numeroFojas;
	}

	public void setNumeroFojas(int numeroFojas) {
		this.numeroFojas = numeroFojas;
	}

	public int getNumeroTomo() {
		return numeroTomo;
	}

	public void setNumeroTomo(int numeroTomo) {
		this.numeroTomo = numeroTomo;
	}

	public String getUbicacionDigital() {
		return ubicacionDigital;
	}

	public void setUbicacionDigital(String ubicacionDigital) {
		this.ubicacionDigital = ubicacionDigital;
	}

	public String getUbicacionFisica() {
		return ubicacionFisica;
	}

	public void setUbicacionFisica(String ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}

	public List<Tramite> getTramites() {
		return tramites;
	}

	public void setTramites(List<Tramite> tramites) {
		this.tramites = tramites;
	}

	public List<ActosEscritura> getActosEscrituras() {
		return actosEscrituras;
	}

	public void setActosEscrituras(List<ActosEscritura> actosEscrituras) {
		this.actosEscrituras = actosEscrituras;
	}

	public List<TipoActo> getTipoActos() {
		return tipoActos;
	}

	public void setTipoActos(List<TipoActo> tipoActos) {
		this.tipoActos = tipoActos;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

}