package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tipo_acto database table.
 * 
 */
@Entity
@Table(name="tipo_acto")
@NamedQuery(name="TipoActo.findAll", query="SELECT t FROM TipoActo t")
public class TipoActo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_acto")
	private int idActo;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="nombre_acto")
	private String nombreActo;

	//bi-directional many-to-one association to Escritura
	@OneToMany(mappedBy="tipoActo")
	private List<Escritura> escrituras;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="tipoActo")
	private List<Solicitud> solicituds;

	public TipoActo() {
	}

	public int getIdActo() {
		return this.idActo;
	}

	public void setIdActo(int idActo) {
		this.idActo = idActo;
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

	public String getNombreActo() {
		return this.nombreActo;
	}

	public void setNombreActo(String nombreActo) {
		this.nombreActo = nombreActo;
	}

	public List<Escritura> getEscrituras() {
		return this.escrituras;
	}

	public void setEscrituras(List<Escritura> escrituras) {
		this.escrituras = escrituras;
	}

	public Escritura addEscritura(Escritura escritura) {
		getEscrituras().add(escritura);
		escritura.setTipoActo(this);

		return escritura;
	}

	public Escritura removeEscritura(Escritura escritura) {
		getEscrituras().remove(escritura);
		escritura.setTipoActo(null);

		return escritura;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setTipoActo(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setTipoActo(null);

		return solicitud;
	}

}