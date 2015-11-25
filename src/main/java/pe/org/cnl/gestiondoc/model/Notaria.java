package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the notaria database table.
 * 
 */
@Entity
@NamedQuery(name="Notaria.findAll", query="SELECT n FROM Notaria n")
public class Notaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_notaria")
	private int idNotaria;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_creacion")
	private Date fecCreacion;

	private String nombre;
	
	@Column(name="encargado_archivo")
	private Integer encargadoArchivo;
	
	private String email;
	
	//bi-directional many-to-one association to Escritura
	@OneToMany(mappedBy="notaria")
	private List<Escritura> escrituras;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="notaria")
	private List<Solicitud> solicituds;

	public Notaria() {
	}

	public int getIdNotaria() {
		return this.idNotaria;
	}

	public void setIdNotaria(int idNotaria) {
		this.idNotaria = idNotaria;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecCreacion() {
		return this.fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Escritura> getEscrituras() {
		return this.escrituras;
	}

	public void setEscrituras(List<Escritura> escrituras) {
		this.escrituras = escrituras;
	}

	public Escritura addEscritura(Escritura escritura) {
		getEscrituras().add(escritura);
		escritura.setNotaria(this);

		return escritura;
	}

	public Escritura removeEscritura(Escritura escritura) {
		getEscrituras().remove(escritura);
		escritura.setNotaria(null);

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
		solicitud.setNotaria(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setNotaria(null);

		return solicitud;
	}

	public Integer getEncargadoArchivo() {
		return encargadoArchivo;
	}

	public void setEncargadoArchivo(Integer encargadoArchivo) {
		this.encargadoArchivo = encargadoArchivo;
	}

}