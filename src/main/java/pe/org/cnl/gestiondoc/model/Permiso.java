package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sec_permisos database table.
 * 
 */
@Entity
@Table(name="sec_permisos")
@NamedQuery(name="Permiso.findAll", query="SELECT p FROM Permiso p")
public class Permiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_permiso")
	private int idPermiso;

	@Column(name="desc_permiso")
	private String descPermiso;

	private int estado;

	public Permiso() {
	}

	public int getIdPermiso() {
		return this.idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public String getDescPermiso() {
		return this.descPermiso;
	}

	public void setDescPermiso(String descPermiso) {
		this.descPermiso = descPermiso;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}