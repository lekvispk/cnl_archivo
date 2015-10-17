package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the actos_escritura database table.
 * 
 */
@Entity
@Table(name="actos_escritura")
@NamedQuery(name="ActosEscritura.findAll", query="SELECT a FROM ActosEscritura a")
public class ActosEscritura implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActosEscrituraPK id;

	private int estado;

	//bi-directional many-to-one association to Escritura
	@ManyToOne
	@JoinColumn(name="id_escritura",insertable=false, updatable=false)
	private Escritura escritura;

	//bi-directional many-to-one association to TipoActo
	@ManyToOne
	@JoinColumn(name="id_acto",insertable=false, updatable=false)
	private TipoActo tipoActo;

	public ActosEscritura() {
	}

	public ActosEscrituraPK getId() {
		return this.id;
	}

	public void setId(ActosEscrituraPK id) {
		this.id = id;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Escritura getEscritura() {
		return this.escritura;
	}

	public void setEscritura(Escritura escritura) {
		this.escritura = escritura;
	}

	public TipoActo getTipoActo() {
		return this.tipoActo;
	}

	public void setTipoActo(TipoActo tipoActo) {
		this.tipoActo = tipoActo;
	}

}