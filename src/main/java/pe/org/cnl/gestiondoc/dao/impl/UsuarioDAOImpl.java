package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.UsuarioDao;
import pe.org.cnl.gestiondoc.model.Authority;
import pe.org.cnl.gestiondoc.model.AuthorityPK;
import pe.org.cnl.gestiondoc.model.Usuario;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDao {

	 @Autowired
     public UsuarioDAOImpl(SessionFactory sessionFactory){
           setHibernateTemplate( new HibernateTemplate(sessionFactory));
     }
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listaUsuarios() {
		return this.getHibernateTemplate().find("from Usuario c");
	}

	@Override
	public List<Usuario> listaUsuarios(Usuario usuario) {
		 DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
         
		 if(!Utiles.nullToBlank(usuario.getUsername()).equals("")){
             criteria.add( Restrictions.ilike("username",usuario.getUsername(),MatchMode.ANYWHERE) );                  
         }
		 
         if(usuario.getPersona() != null){
        	 if(!Utiles.nullToBlank( usuario.getPersona().getNombre() ).equals("")){
                 criteria.add( Restrictions.ilike("persona.nombre", usuario.getPersona().getNombre() ,MatchMode.ANYWHERE ) );                 
        	 }
         }
        
         return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Usuario obtenerUsuarioPorUsername(String username) {
		 Query query = getSession().createQuery("from Usuario u where username = :id  ")
		 .setString("id", username);
		 Usuario h = (Usuario)query.uniqueResult();
		 return h;
			                
	}

	@Override
	public Usuario obtenerUsuario(String username) {
		 Query query = getSession().createQuery(" from Usuario u left join fetch  u.authoritiesList where u.username = :id ")
		 .setString("id", username);
		 return (Usuario) query.uniqueResult();
	}

	@Override
	public void registrarusuario(Usuario usuario) {
		   this.getHibernateTemplate().saveOrUpdate(usuario);
	}

	@Override
	public void modificarusuario(Usuario usuario) {
		  this.getHibernateTemplate().merge(usuario);
	}

	@Override
	public void modificarPermisos(Usuario usuario) {
		 logger.debug( usuario.getUsername() );
         getSession().createQuery("delete from Authorities where username = :id  ")
         .setString("id", usuario.getUsername()).executeUpdate();
         
         for(Authority au : usuario.getSecAuthorities()){
             logger.debug( au.getId().getAuthorithy() +" ; " + au.getId().getUsername() );
             this.getHibernateTemplate().saveOrUpdate(au);
         }
	}

	@Override
	public void agregaPermisoUser(Usuario usuario) {
		AuthorityPK pk = new AuthorityPK();
		pk.setAuthorithy(  "ROLE_USER" );
		pk.setUsername( usuario.getUsername() );
		 Authority au = new Authority(  );
		 au.setId( pk );
         this.getHibernateTemplate().saveOrUpdate(au);
	}

}
