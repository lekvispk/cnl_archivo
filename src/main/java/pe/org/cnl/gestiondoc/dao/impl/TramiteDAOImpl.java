package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.TramiteDAO;
import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteAdjunto;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
@Transactional
public class TramiteDAOImpl implements TramiteDAO {

	private static final Logger logger = Logger.getLogger( TramiteDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tramite> buscar(Tramite tramite) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Tramite.class);
		if(tramite !=null){
			
			if( !Utiles.nullToBlank(tramite.getFechaCreacion()).equals("")){
				criteria.add( Restrictions.ge("fechaCreacion", tramite.getFechaCreacion() ) );
				logger.debug( "fechaCreacion"+ tramite.getFechaCreacion() );
			}
			
			if( tramite.getTramiteUsuarios() != null && !tramite.getTramiteUsuarios().isEmpty() ) {
				TramiteUsuario tu = tramite.getTramiteUsuarios().get(0); 
				Criteria tramUser = criteria.createAlias("tramiteUsuarios", "tu");
				if( tu.getEstado() > 0 ){
					logger.debug(" tr_usr.estado="+ tu.getEstado());
					tramUser.add( Restrictions.eq("tu.estado", tu.getEstado()) );	
				}
				if( tu.getEstadoTramiteFinal() > 0 ){
					logger.debug(" tr_usr.estadoTramiteFinal="+ tu.getEstadoTramiteFinal());
					tramUser.add( Restrictions.eq("tu.estadoTramiteFinal", tu.getEstadoTramiteFinal()) );	
				}
				if( tu.getSecUsuario1() != null ){
					logger.debug(" emisor="+ tu.getSecUsuario1().getUsername() );
					tramUser.add( Restrictions.eq("tu.secUsuario1.username", tu.getSecUsuario1().getUsername() ) );	
				}
			}
			if( tramite.getEstado() > 0){
				logger.debug("estado="+  tramite.getEstado() );
				criteria.add( Restrictions.eq("estado", tramite.getEstado() ) );	
			}
			criteria.addOrder( Order.desc("fechaCreacion") );
		}				
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return  criteria.list();
	}

	@Override
	public void registrar(Tramite tramite) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(tramite);
	}

	@Override
	public void registrarMovimiento(TramiteUsuario tramiteUsuario) {
		
		Query query = this.sessionFactory.getCurrentSession().createQuery(" update TramiteUsuario e set estado=0 where e.tramite.idTramite =:id ")
		        .setInteger("id", tramiteUsuario.getTramite().getIdTramite() );
		        query.executeUpdate();
		        
		this.sessionFactory.getCurrentSession().saveOrUpdate(tramiteUsuario);
	}
	
	@Override
	public void eliminar(Integer idTramite) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(" update Tramite e set estado=0 where e.idTramite =:id ")
		        .setInteger("id", idTramite);
		        query.executeUpdate();
	}

	@Override
	public Tramite obtener(Integer idTramite) {
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery(" from Tramite d LEFT JOIN FETCH d.tramiteAdjuntos where d.idTramite = :id ")
				.setInteger("id", idTramite);
        return (Tramite) query.uniqueResult();
	}

	@Override
	public void eliminarMovimientosPrevios(Integer idTramite) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(" update TramiteUsuario e set estado=0 where e.tramite.idTramite =:id ")
		        .setInteger("id", idTramite);
		        query.executeUpdate();
	}

	@Override
	public TramiteAdjunto obtenerAdjunto(Integer idAdjunto) {
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery(" from TramiteAdjunto d where d.idAdjunto = :id ")
		        .setInteger("id", idAdjunto);
		        return (TramiteAdjunto) query.uniqueResult();
	}

	@Override
	public void registrarAdjunto(TramiteAdjunto archivo) {
		this.sessionFactory.getCurrentSession().saveOrUpdate( archivo );
	}

	@Override
	public void derivar(Tramite tr) {
		this.sessionFactory.getCurrentSession().saveOrUpdate( tr );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tramite> buscarParaSecretaria(Tramite tramite) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Tramite.class);
		if(tramite !=null){
			
			if( !Utiles.nullToBlank(tramite.getFechaCreacion()).equals("")){
				criteria.add( Restrictions.ge("fechaCreacion", tramite.getFechaCreacion() ) );
				logger.debug( "fechaCreacion"+ tramite.getFechaCreacion() );
			}
			
			if( tramite.getTramiteUsuarios() != null && !tramite.getTramiteUsuarios().isEmpty() ) {
				TramiteUsuario tu = tramite.getTramiteUsuarios().get(0); 
				Criteria tramUser = criteria.createAlias("tramiteUsuarios", "tu");
				if( tu.getEstado() > 0 ){
					logger.debug(" tr_usr.estado="+ tu.getEstado());
					tramUser.add( Restrictions.eq("tu.estado", tu.getEstado()) );	
				}
				if( tu.getEstadoTramiteFinal() > 0 ){
					logger.debug(" tr_usr.estadoTramiteFinal="+ tu.getEstadoTramiteFinal());
					tramUser.add( Restrictions.eq("tu.estadoTramiteFinal", tu.getEstadoTramiteFinal()) );	
				}
				if( tu.getSecUsuario1() != null ){
					logger.debug(" emisor="+ tu.getSecUsuario1().getUsername() );
					tramUser.add( Restrictions.eq("tu.secUsuario1.username", tu.getSecUsuario1().getUsername() ) );	
				}
			}
			if( tramite.getEstado() > 0){
				logger.debug("estado="+  tramite.getEstado() );
				criteria.add( Restrictions.le("estado", tramite.getEstado() ) );	
			}
			criteria.addOrder( Order.desc("fechaCreacion") );
		}				
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return  criteria.list();
	}

	@Override
	public void eliminarAdjunto(Integer idAdjunto) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(" delete from TramiteAdjunto where  idAdjunto=:id ")
		        .setInteger("id", idAdjunto);
		        query.executeUpdate();
	}

}
