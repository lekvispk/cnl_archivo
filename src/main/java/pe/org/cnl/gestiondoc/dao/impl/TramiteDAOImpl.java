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

import pe.org.cnl.gestiondoc.dao.TramiteDAO;
import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.util.Utiles;
@Repository
public class TramiteDAOImpl extends HibernateDaoSupport implements TramiteDAO {

	@Autowired
	public TramiteDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tramite> buscar(Tramite tramite) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Tramite.class);
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
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void registrar(Tramite tramite) {
		this.getHibernateTemplate().saveOrUpdate(tramite);
	}

	@Override
	public void eliminar(Integer idTramite) {
		Query query = getSession().createQuery(" update Tramite e set estado=0 where e.idTramite =:id ")
		        .setInteger("id", idTramite);
		        query.executeUpdate();
	}

	@Override
	public Tramite obtener(Integer idTramite) {
		Query query = getSession().createQuery(" from Tramite d where d.idTramite = :id ")
        .setInteger("id", idTramite);
        return (Tramite) query.uniqueResult();
	}

}
