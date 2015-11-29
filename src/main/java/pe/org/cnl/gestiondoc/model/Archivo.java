package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the archivo database table.
 * 
 */
@Entity
@NamedQuery(name="Archivo.findAll", query="SELECT a FROM Archivo a")
public class Archivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_archivo")
	private int idArchivo;

	@Lob
	private byte[] archivo;

	private String nombre;
	
	private String mimetype;
	
	//bi-directional many-to-one association to Escritura
	@ManyToOne
	@JoinColumn(name="id_escritura")
	private Escritura escritura;

	public Archivo() {
	}

	public int getIdArchivo() {
		return this.idArchivo;
	}

	public void setIdArchivo(int idArchivo) {
		this.idArchivo = idArchivo;
	}

	public byte[] getArchivo() {
		return this.archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Escritura getEscritura() {
		return this.escritura;
	}

	public void setEscritura(Escritura escritura) {
		this.escritura = escritura;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

}