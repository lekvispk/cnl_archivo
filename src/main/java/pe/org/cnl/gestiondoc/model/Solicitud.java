package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the solicitud database table.
 * 
 */
@Entity
@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idsolicitud;

	@Column(name="cod_solicitud")
	private String codSolicitud;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;
	
	@Transient
	private Date fechaIngreso2;
	
	public Date getFechaIngreso2() {
		return fechaIngreso2;
	}

	public void setFechaIngreso2(Date fechaIngreso2) {
		this.fechaIngreso2 = fechaIngreso2;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_solucion")
	private Date fechaSolucion;

	@Column(name="numero_ruc")
	private String numeroRuc;

	@Column(name="tipo_comprobante")
	private int tipoComprobante;

	@Column(name="tram_apod_direccion")
	private String tramApodDireccion;

	@Column(name="tram_apod_documento")
	private String tramApodDocumento;

	@Column(name="tram_apod_telefono")
	private String tramApodTelefono;

	@Column(name="tram_apoderado")
	private String tramApoderado;

	@Column(name="tram_comprador")
	private String tramComprador;

	@Column(name="tram_escritura")
	private String tramEscritura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="tram_fecha_escritura")
	private Date tramFechaEscritura;

	@Column(name="tram_fojas")
	private String tramFojas;

	@Column(name="tram_folios")
	private String tramFolios;
	
	@Column(name="tram_instrumento_num")
	private String tramInstrumentoNum;

	@Column(name="tram_kardex")
	private String tramKardex;

	@Column(name="tram_minuta_num")
	private String tramMinutaNum;

	@Column(name="tram_solicitado")
	private String tramSolicitado;

	@Column(name="tram_vendedor")
	private String tramVendedor;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to TipoSolicitud
	@ManyToOne
	@JoinColumn(name="id_tipo_solicitud")
	private TipoSolicitud tipoSolicitud;

	//bi-directional many-to-one association to TipoActo
	@ManyToOne
	@JoinColumn(name="id_acto")
	private TipoActo tipoActo;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona")
	private Persona persona;

	//bi-directional many-to-one association to Notaria
	@ManyToOne
	@JoinColumn(name="id_notaria")
	private Notaria notaria;

	//bi-directional many-to-one association to SolicitudTramite
	@OneToMany(mappedBy="solicitud")
	private List<SolicitudTramite> solicitudTramites;

	//bi-directional many-to-one association to Tramite
	@OneToMany(mappedBy="solicitud")
	private List<Tramite> tramites;

	public Solicitud() {
	}

	public int getIdsolicitud() {
		return this.idsolicitud;
	}

	public void setIdsolicitud(int idsolicitud) {
		this.idsolicitud = idsolicitud;
	}

	public String getCodSolicitud() {
		return this.codSolicitud;
	}

	public void setCodSolicitud(String codSolicitud) {
		this.codSolicitud = codSolicitud;
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

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getFechaSolucion() {
		return this.fechaSolucion;
	}

	public void setFechaSolucion(Date fechaSolucion) {
		this.fechaSolucion = fechaSolucion;
	}

	public String getNumeroRuc() {
		return this.numeroRuc;
	}

	public void setNumeroRuc(String numeroRuc) {
		this.numeroRuc = numeroRuc;
	}

	public int getTipoComprobante() {
		return this.tipoComprobante;
	}

	public void setTipoComprobante(int tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getTramApodDireccion() {
		return this.tramApodDireccion;
	}

	public void setTramApodDireccion(String tramApodDireccion) {
		this.tramApodDireccion = tramApodDireccion;
	}

	public String getTramApodDocumento() {
		return this.tramApodDocumento;
	}

	public void setTramApodDocumento(String tramApodDocumento) {
		this.tramApodDocumento = tramApodDocumento;
	}

	public String getTramApodTelefono() {
		return this.tramApodTelefono;
	}

	public void setTramApodTelefono(String tramApodTelefono) {
		this.tramApodTelefono = tramApodTelefono;
	}

	public String getTramApoderado() {
		return this.tramApoderado;
	}

	public void setTramApoderado(String tramApoderado) {
		this.tramApoderado = tramApoderado;
	}

	public String getTramComprador() {
		return this.tramComprador;
	}

	public void setTramComprador(String tramComprador) {
		this.tramComprador = tramComprador;
	}

	public String getTramEscritura() {
		return this.tramEscritura;
	}

	public void setTramEscritura(String tramEscritura) {
		this.tramEscritura = tramEscritura;
	}

	public Date getTramFechaEscritura() {
		return this.tramFechaEscritura;
	}

	public void setTramFechaEscritura(Date tramFechaEscritura) {
		this.tramFechaEscritura = tramFechaEscritura;
	}

	public String getTramFojas() {
		return this.tramFojas;
	}

	public void setTramFojas(String tramFojas) {
		this.tramFojas = tramFojas;
	}

	public String getTramInstrumentoNum() {
		return this.tramInstrumentoNum;
	}

	public void setTramInstrumentoNum(String tramInstrumentoNum) {
		this.tramInstrumentoNum = tramInstrumentoNum;
	}

	public String getTramKardex() {
		return this.tramKardex;
	}

	public void setTramKardex(String tramKardex) {
		this.tramKardex = tramKardex;
	}

	public String getTramMinutaNum() {
		return this.tramMinutaNum;
	}

	public void setTramMinutaNum(String tramMinutaNum) {
		this.tramMinutaNum = tramMinutaNum;
	}

	public String getTramSolicitado() {
		return this.tramSolicitado;
	}

	public void setTramSolicitado(String tramSolicitado) {
		this.tramSolicitado = tramSolicitado;
	}

	public String getTramVendedor() {
		return this.tramVendedor;
	}

	public void setTramVendedor(String tramVendedor) {
		this.tramVendedor = tramVendedor;
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

	public TipoSolicitud getTipoSolicitud() {
		return this.tipoSolicitud;
	}

	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public TipoActo getTipoActo() {
		return this.tipoActo;
	}

	public void setTipoActo(TipoActo tipoActo) {
		this.tipoActo = tipoActo;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Notaria getNotaria() {
		return this.notaria;
	}

	public void setNotaria(Notaria notaria) {
		this.notaria = notaria;
	}

	public List<SolicitudTramite> getSolicitudTramites() {
		return this.solicitudTramites;
	}

	public void setSolicitudTramites(List<SolicitudTramite> solicitudTramites) {
		this.solicitudTramites = solicitudTramites;
	}

	public SolicitudTramite addSolicitudTramite(SolicitudTramite solicitudTramite) {
		getSolicitudTramites().add(solicitudTramite);
		solicitudTramite.setSolicitud(this);

		return solicitudTramite;
	}

	public SolicitudTramite removeSolicitudTramite(SolicitudTramite solicitudTramite) {
		getSolicitudTramites().remove(solicitudTramite);
		solicitudTramite.setSolicitud(null);

		return solicitudTramite;
	}

	public List<Tramite> getTramites() {
		return this.tramites;
	}

	public void setTramites(List<Tramite> tramites) {
		this.tramites = tramites;
	}

	public Tramite addTramite(Tramite tramite) {
		getTramites().add(tramite);
		tramite.setSolicitud(this);

		return tramite;
	}

	public Tramite removeTramite(Tramite tramite) {
		getTramites().remove(tramite);
		tramite.setSolicitud(null);

		return tramite;
	}

	public String getTramFolios() {
		return tramFolios;
	}

	public void setTramFolios(String tramFolios) {
		this.tramFolios = tramFolios;
	}

}