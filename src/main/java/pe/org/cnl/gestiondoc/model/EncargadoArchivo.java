package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the encargado_archivo database table.
 * 
 */
@Entity
@Table(name="encargado_archivo")
@NamedQuery(name="EncargadoArchivo.findAll", query="SELECT e FROM EncargadoArchivo e")
public class EncargadoArchivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_encargado_archivo")
	private int idEncargadoArchivo;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	//bi-directional many-to-one association to Notaria
	@ManyToOne
	@JoinColumn(name="id_notaria")
	private Notaria notaria;

	public EncargadoArchivo() {
	}

	public int getIdEncargadoArchivo() {
		return this.idEncargadoArchivo;
	}

	public void setIdEncargadoArchivo(int idEncargadoArchivo) {
		this.idEncargadoArchivo = idEncargadoArchivo;
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

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Notaria getNotaria() {
		return this.notaria;
	}

	public void setNotaria(Notaria notaria) {
		this.notaria = notaria;
	}

}