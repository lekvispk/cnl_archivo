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

import pe.org.cnl.gestiondoc.dao.SolicitudDAO;
import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
@Transactional
public class SolicitudDAOImpl implements SolicitudDAO {

	private static final Logger logger = Logger.getLogger(SolicitudDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> buscarSolicitudes(Solicitud solicitud) {
		logger.trace("buscarSolicitudes");
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Solicitud.class);
		if(solicitud !=null){
			if( solicitud.getTipoActo() != null && !Utiles.nullToBlank(solicitud.getTipoActo().getIdActo()).equals("-1")){
				criteria.add( Restrictions.eq("tipoActo.idActo", solicitud.getTipoActo().getIdActo() ) );
			}
			if( solicitud.getTipoSolicitud() != null && !Utiles.nullToBlank(solicitud.getTipoSolicitud().getIdTipoSolicitud()).equals("-1")){
				criteria.add( Restrictions.eq("tipoSolicitud.idTipoSolicitud", solicitud.getTipoSolicitud().getIdTipoSolicitud() ) );
			}
			if( !Utiles.nullToBlank(solicitud.getFechaIngreso()).equals("")){
				criteria.add( Restrictions.ge("fechaIngreso", solicitud.getFechaIngreso() ) );
			}
			if( !Utiles.nullToBlank(solicitud.getFechaIngreso2()).equals("")){
				criteria.add( Restrictions.le("fechaIngreso", solicitud.getFechaIngreso2() ) );
			}
			if(solicitud.getEstado()!=0){
				criteria.add( Restrictions.eq("estado", solicitud.getEstado() ) );	
			}
			criteria.addOrder( Order.desc("idsolicitud") );
		}				
		return criteria.list();
	}

	@Override
	public Solicitud registrarSolicitud(Solicitud solicitud) {
		return (Solicitud)this.sessionFactory.getCurrentSession().merge(solicitud);
	}

	@Override
	public void eliminarSolicitud(Integer idSolicitud) {
		Query query = this.sessionFactory.getCurrentSession()
					.createQuery(" update Solicitud set estado=0 where idsolicitud =:id ")
					.setInteger("id", idSolicitud);
        query.executeUpdate();
	}

	@Override
	public Solicitud obtenerSolicitud(Integer idSolicitud) {
		Query query = this.sessionFactory.getCurrentSession()
					.createQuery(" from Solicitud where idsolicitud = :id ")
					.setInteger("id", idSolicitud);
        return (Solicitud) query.uniqueResult();
	}

	@Override
	public void registrarSolicitudTramite(SolicitudTramite sol) {
		this.sessionFactory.getCurrentSession().saveOrUpdate( sol );
	}

	@Override
	public void actualizarEstado(int idsolicitud, int estado) {
		Query query = this.sessionFactory.getCurrentSession()
					.createQuery(" update Solicitud set estado=:est where idsolicitud =:id ")
					.setInteger("id", idsolicitud)
					.setInteger("est", estado);
			query.executeUpdate();
	}

}
