package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tramite database table.
 * 
 */
@Entity
@NamedQuery(name="Tramite.findAll", query="SELECT t FROM Tramite t")
public class Tramite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_tramite")
	private int idTramite;

	@Column(name="cant_hojas")
	private int cantHojas;

	@Column(name="costo_hoja")
	private BigDecimal costoHoja;

	@Column(name="costo_total")
	private BigDecimal costoTotal;

	@Lob
	@Column(name="detalle_notificacion")
	private String detalleNotificacion;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_conclusion")
	private Date fechaConclusion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Lob
	@Column(name="informe_solicitud")
	private String informeSolicitud;

	@Lob
	@Column(name="observaciones_notario")
	private String observacionesNotario;

	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="idsolicitud")
	private Solicitud solicitud;

	//bi-directional many-to-one association to TramiteAdjunto
	@OneToMany(mappedBy="tramite")
	private List<TramiteAdjunto> tramiteAdjuntos;

	public Tramite() {
	}

	public int getIdTramite() {
		return this.idTramite;
	}

	public void setIdTramite(int idTramite) {
		this.idTramite = idTramite;
	}

	public int getCantHojas() {
		return this.cantHojas;
	}

	public void setCantHojas(int cantHojas) {
		this.cantHojas = cantHojas;
	}

	public BigDecimal getCostoHoja() {
		return this.costoHoja;
	}

	public void setCostoHoja(BigDecimal costoHoja) {
		this.costoHoja = costoHoja;
	}

	public BigDecimal getCostoTotal() {
		return this.costoTotal;
	}

	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}

	public String getDetalleNotificacion() {
		return this.detalleNotificacion;
	}

	public void setDetalleNotificacion(String detalleNotificacion) {
		this.detalleNotificacion = detalleNotificacion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaConclusion() {
		return this.fechaConclusion;
	}

	public void setFechaConclusion(Date fechaConclusion) {
		this.fechaConclusion = fechaConclusion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getInformeSolicitud() {
		return this.informeSolicitud;
	}

	public void setInformeSolicitud(String informeSolicitud) {
		this.informeSolicitud = informeSolicitud;
	}

	public String getObservacionesNotario() {
		return this.observacionesNotario;
	}

	public void setObservacionesNotario(String observacionesNotario) {
		this.observacionesNotario = observacionesNotario;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public List<TramiteAdjunto> getTramiteAdjuntos() {
		return this.tramiteAdjuntos;
	}

	public void setTramiteAdjuntos(List<TramiteAdjunto> tramiteAdjuntos) {
		this.tramiteAdjuntos = tramiteAdjuntos;
	}

	public TramiteAdjunto addTramiteAdjunto(TramiteAdjunto tramiteAdjunto) {
		getTramiteAdjuntos().add(tramiteAdjunto);
		tramiteAdjunto.setTramite(this);

		return tramiteAdjunto;
	}

	public TramiteAdjunto removeTramiteAdjunto(TramiteAdjunto tramiteAdjunto) {
		getTramiteAdjuntos().remove(tramiteAdjunto);
		tramiteAdjunto.setTramite(null);

		return tramiteAdjunto;
	}

}