package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.SolicitudTramiteDAO;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

@Repository
public class SolicitudTramiteDAOImpl extends HibernateDaoSupport implements SolicitudTramiteDAO {

	@Autowired
	public SolicitudTramiteDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	
	//estado 1 = pendiente
	//estado 2 = firmado 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudTramite> buscarSolicitudes(Integer estado) {
		Query query = getSession().createQuery(" from SolicitudTramite s where s.estado= :estado ");
		query.setInteger("estado", estado );
        return query.list();
	}

	@Override
	public void registrarSolicitudTramite(SolicitudTramite solicitudTramite) {
		this.getHibernateTemplate().saveOrUpdate(solicitudTramite);
	}

	@Override
	public SolicitudTramite obtenerSolicitudTramite(Integer idSol, Integer idEscri) {
		Query query = getSession().createQuery(" from SolicitudTramite s where s.id.idsolicitud= :id  and s.id.idEscritura =:id2")
        .setInteger("id", idSol)
        .setInteger("id2", idEscri);
        return (SolicitudTramite) query.uniqueResult();
	}

	@Override
	public void eliminarSolicitudTramite(Integer idSol, Integer idEscri) {
		Query query = getSession().createQuery(" delete from SolicitudTramite s where s.id.idsolicitud= :id  and s.id.idEscritura =:id2")
           .setInteger("id", idSol)
        .setInteger("id2", idEscri);
	    query.executeUpdate();
	}

}
