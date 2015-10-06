package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tipo_solicitud database table.
 * 
 */
@Entity
@Table(name="tipo_solicitud")
@NamedQuery(name="TipoSolicitud.findAll", query="SELECT t FROM TipoSolicitud t")
public class TipoSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_tipo_solicitud")
	private int idTipoSolicitud;

	@Column(name="costo_servicio")
	private BigDecimal costoServicio;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="nombre_tipo_solicitud")
	private String nombreTipoSolicitud;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="tipoSolicitud")
	private List<Solicitud> solicituds;

	public TipoSolicitud() {
	}

	public int getIdTipoSolicitud() {
		return this.idTipoSolicitud;
	}

	public void setIdTipoSolicitud(int idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}

	public BigDecimal getCostoServicio() {
		return this.costoServicio;
	}

	public void setCostoServicio(BigDecimal costoServicio) {
		this.costoServicio = costoServicio;
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

	public String getNombreTipoSolicitud() {
		return this.nombreTipoSolicitud;
	}

	public void setNombreTipoSolicitud(String nombreTipoSolicitud) {
		this.nombreTipoSolicitud = nombreTipoSolicitud;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setTipoSolicitud(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setTipoSolicitud(null);

		return solicitud;
	}

}