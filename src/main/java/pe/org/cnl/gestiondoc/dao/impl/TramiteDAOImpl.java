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
import pe.org.cnl.gestiondoc.model.TramiteUsuario;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
@Transactional
public class TramiteDAOImpl implements TramiteDAO {

	private static final Logger logger = Logger.getLogger( TramiteDAOImpl.class );
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tramite> buscar(Tramite tramite) {
		//DetachedCriteria criteria = DetachedCriteria.forClass(Tramite.class);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Tramite.class);
		if(tramite !=null){
			
			if( !Utiles.nullToBlank(tramite.getFechaCreacion()).equals("")){
				criteria.add( Restrictions.ge("fechaCreacion", tramite.getFechaCreacion() ) );
				logger.debug( "fechaCreacion"+ tramite.getFechaCreacion() );
			}
			
			if( tramite.getTramiteUsuarios() != null && !tramite.getTramiteUsuarios().isEmpty() ) {
				logger.debug("tramiteUsuarios.estado="+tramite.getTramiteUsuarios().get(0).getEstado());
				criteria.createAlias("tramiteUsuarios", "tu")
				//.add( Restrictions.eq("tu.estado", tramite.getTramiteUsuarios().get(0).getEstado() ) );
				.add( Restrictions.eq("tu.estado", 1 ) );
			}
			logger.debug("estado="+  tramite.getEstado() );
			criteria.add( Restrictions.eq("estado", tramite.getEstado() ) );
			criteria.addOrder( Order.desc("fechaCreacion") );
		}				
		return  criteria.list();
	}

	@Override
	public void registrar(Tramite tramite) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(tramite);
	}

	@Override
	public void registrarMovimiento(TramiteUsuario tramiteUsuario) {
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
		Query query = this.sessionFactory.getCurrentSession().createQuery(" from Tramite d where d.idTramite = :id ")
        .setInteger("id", idTramite);
        return (Tramite) query.uniqueResult();
	}

	@Override
	public void eliminarMovimientosPrevios(Integer idTramite) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(" update TramiteUsuario e set estado=0 where e.tramite.idTramite =:id ")
		        .setInteger("id", idTramite);
		        query.executeUpdate();
	}

}
