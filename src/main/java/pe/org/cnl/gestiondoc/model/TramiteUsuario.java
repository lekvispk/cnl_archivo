package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tramite_usuario database table.
 * 
 */
@Entity
@Table(name="tramite_usuario")
@NamedQuery(name="TramiteUsuario.findAll", query="SELECT t FROM TramiteUsuario t")
public class TramiteUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_registro")
	private int idRegistro;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="username_emisor")
	private Usuario secUsuario1;

	//bi-directional many-to-one association to Tramite
	@ManyToOne
	@JoinColumn(name="id_tramite")
	private Tramite tramite;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="username_receptor")
	private Usuario secUsuario2;

	public TramiteUsuario() {
	}

	public int getIdRegistro() {
		return this.idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Usuario getSecUsuario1() {
		return this.secUsuario1;
	}

	public void setSecUsuario1(Usuario secUsuario1) {
		this.secUsuario1 = secUsuario1;
	}

	public Tramite getTramite() {
		return this.tramite;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}

	public Usuario getSecUsuario2() {
		return this.secUsuario2;
	}

	public void setSecUsuario2(Usuario secUsuario2) {
		this.secUsuario2 = secUsuario2;
	}

}