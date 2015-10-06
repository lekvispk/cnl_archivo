package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sec_authorities database table.
 * 
 */
@Entity
@Table(name="sec_authorities")
@NamedQuery(name="Authority.findAll", query="SELECT a FROM Authority a")
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AuthorityPK id;
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="username",insertable=false, updatable=false)
	private Usuario secUsuario;
		
	public Authority() {
	}

	public AuthorityPK getId() {
		return this.id;
	}

	public void setId(AuthorityPK id) {
		this.id = id;
	}
	public Usuario getSecUsuario() {
		return this.secUsuario;
	}

	public void setSecUsuario(Usuario secUsuario) {
		this.secUsuario = secUsuario;
	}

}