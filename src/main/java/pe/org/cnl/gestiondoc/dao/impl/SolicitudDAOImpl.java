package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.SolicitudDAO;
import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
public class SolicitudDAOImpl extends HibernateDaoSupport implements SolicitudDAO {

	@Autowired
	public SolicitudDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> buscarSolicitudes(Solicitud solicitud) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Solicitud.class);
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
			
			
			criteria.addOrder( Order.desc("fechaIngreso") );
		}				
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void registrarSolicitud(Solicitud solicitud) {
		this.getHibernateTemplate().merge(solicitud);
	}

	@Override
	public void eliminarSolicitud(Integer idSolicitud) {
		Query query = getSession().createQuery(" update Solicitud set estado=0 where idsolicitud =:id ")
        .setInteger("id", idSolicitud);
        query.executeUpdate();
	}

	@Override
	public Solicitud obtenerSolicitud(Integer idSolicitud) {
		Query query = getSession().createQuery(" from Solicitud where idsolicitud = :id ")
        .setInteger("id", idSolicitud);
        return (Solicitud) query.uniqueResult();
	}

	@Override
	public void registrarSolicitudTramite(SolicitudTramite sol) {
		this.getHibernateTemplate().saveOrUpdate( sol );
	}

}
