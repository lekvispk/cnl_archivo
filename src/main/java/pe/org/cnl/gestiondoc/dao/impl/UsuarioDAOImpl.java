package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.UsuarioDao;
import pe.org.cnl.gestiondoc.model.Authority;
import pe.org.cnl.gestiondoc.model.AuthorityPK;
import pe.org.cnl.gestiondoc.model.Usuario;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDao {

	private static final Logger logger = Logger.getLogger(UsuarioDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listaUsuarios() {
		return this.sessionFactory.getCurrentSession().createQuery("from Usuario c").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listaUsuarios(Usuario usuario) {
		 Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		 if(!Utiles.nullToBlank(usuario.getUsername()).equals("")){
             criteria.add( Restrictions.ilike("username",usuario.getUsername(),MatchMode.ANYWHERE) );                  
         }
		 
         if(usuario.getPersona() != null){
        	 if(!Utiles.nullToBlank( usuario.getPersona().getNombre() ).equals("")){
                 criteria.add( Restrictions.ilike("persona.nombre", usuario.getPersona().getNombre() ,MatchMode.ANYWHERE ) );                 
        	 }
         }
        
         return criteria.list();
	}

	@Override
	public Usuario obtenerUsuarioPorUsername(String username) {
		 Query query = this.sessionFactory.getCurrentSession()
				 	.createQuery("from Usuario u where username = :id  ")
				 	.setString("id", username);
		 Usuario h = (Usuario)query.uniqueResult();
		 return h;
			                
	}

	@Override
	public Usuario obtenerUsuario(String username) {
		 Query query = this.sessionFactory.getCurrentSession()
				 	.createQuery(" from Usuario u left join fetch u.secAuthorities where u.username = :id ")
				 	.setString("id", username);
		 return (Usuario) query.uniqueResult();
	}

	@Override
	public void registrarusuario(Usuario usuario) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(usuario);
	}

	@Override
	public void modificarusuario(Usuario usuario) {
		this.sessionFactory.getCurrentSession().merge(usuario);
	}

	@Override
	public void modificarPermisos(Usuario usuario) {
		 logger.info( usuario.getUsername() );
		 
		 this.sessionFactory.getCurrentSession()
		 .createQuery("delete from Authorities where username = :id  ")
         .setString("id", usuario.getUsername())
         .executeUpdate();
         
         for(Authority au : usuario.getSecAuthorities()){
             logger.debug( au.getId().getAuthorithy() +" ; " + au.getId().getUsername() );
             this.sessionFactory.getCurrentSession().saveOrUpdate(au);
         }
	}

	@Override
	public void agregaPermisoUser(Usuario usuario) {
		AuthorityPK pk = new AuthorityPK();
		pk.setAuthorithy(  "ROLE_USER" );
		pk.setUsername( usuario.getUsername() );
		 Authority au = new Authority(  );
		 au.setId( pk );
		 this.sessionFactory.getCurrentSession().saveOrUpdate(au);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listaUsuariosPorRole(String role) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Usuario.class);
    	if(!Utiles.nullToBlank(role ).equals("")){
    		 Criteria criteria2 = criteria.createAlias("secAuthorities", "p");
             criteria2.add( Restrictions.like("p.id.authorithy", role ) );                 
    	}
        return criteria.list();
	}

}
