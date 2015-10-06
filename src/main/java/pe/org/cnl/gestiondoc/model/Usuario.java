package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


/**
 * The persistent class for the sec_usuarios database table.
 * 
 */
@Entity
@Table(name="sec_usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String username;

	private String clave;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona")
	private Persona persona;

	//bi-directional many-to-one association to Authority
	@OneToMany(mappedBy="secUsuario")
	private List<Authority> secAuthorities;
		
	@Transient
	private List<String> permisos;
	 
	public Usuario() {
		  super("default", "default", true, true, true, true , uno() );           
	}
	  public static List<GrantedAuthority> uno(){
          List<GrantedAuthority> oo = new ArrayList<GrantedAuthority>(); 
          oo.add(new GrantedAuthorityImpl("IS_AUTHENTICATED_ANONYMOUSLY") );
          return oo;
	  }
	  
	  public Usuario(String username, String password, boolean enabled,Collection<GrantedAuthority> authorities) {
	          super(username, password, enabled, true, true, true, authorities);
	          this.estado = enabled==true ? 1 : 0;
	          this.username = username;
	          this.clave = password;
	  }
	  
	  public Usuario(String username, String password, boolean enabled,List<GrantedAuthority> authorities,
	                  String usrnombrevh,String usrapepaternovh, String usrapematernovh,
	                  Date usrfechanacimientodt,Integer estado) {
          super(username, password, enabled, true, true, true, authorities);
          this.username = username;
          this.clave = password;
          this.estado = enabled==true ? 1 : 0;
          
	  }
	
	  public static Usuario getUsuarioBean() {
		  Usuario nu = (Usuario)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	      if(nu != null) {
	          return nu;
	      }
	      else return null;
	  }
	  
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public List<Authority> getSecAuthorities() {
		return this.secAuthorities;
	}

	public void setSecAuthorities(List<Authority> secAuthorities) {
		this.secAuthorities = secAuthorities;
	}

	public Authority addSecAuthority(Authority secAuthority) {
		getSecAuthorities().add(secAuthority);
		secAuthority.setSecUsuario(this);

		return secAuthority;
	}

	public Authority removeSecAuthority(Authority secAuthority) {
		getSecAuthorities().remove(secAuthority);
		secAuthority.setSecUsuario(null);

		return secAuthority;
	}
	 public static boolean tienePermiso(String permiso) {
         Usuario us = getUsuarioBean();
         for(GrantedAuthority ga : us.getAuthorities()){
                 if(ga.getAuthority().equals(permiso)){
                         return true;
                 }
         }
         return false;
 }
}