package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.SolicitudTramiteDAO;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

@Repository
@Transactional
public class SolicitudTramiteDAOImpl implements SolicitudTramiteDAO {

	private static final Logger logger = Logger.getLogger(SolicitudTramiteDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//estado 1 = pendiente
	//estado 2 = firmado 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudTramite> buscarSolicitudes(Integer estado) {
		logger.info("buscarSolicitudes");
		Query query = this.sessionFactory.getCurrentSession()
					.createQuery(" from SolicitudTramite s where s.estado= :estado ");
					query.setInteger("estado", estado );
        return query.list();
	}

	@Override
	public void registrarSolicitudTramite(SolicitudTramite solicitudTramite) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(solicitudTramite);
	}

	@Override
	public SolicitudTramite obtenerSolicitudTramite(Integer idSol, Integer idEscri) {
		Query query = this.sessionFactory.getCurrentSession()
					.createQuery(" from SolicitudTramite s where s.id.idsolicitud= :id  and s.id.idEscritura =:id2")
					.setInteger("id", idSol)
					.setInteger("id2", idEscri);
        return (SolicitudTramite) query.uniqueResult();
	}

	@Override
	public void eliminarSolicitudTramite(Integer idSol, Integer idEscri) {
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery(" delete from SolicitudTramite s where s.id.idsolicitud= :id  and s.id.idEscritura =:id2")
				.setInteger("id", idSol)
				.setInteger("id2", idEscri);
	    query.executeUpdate();
	}

}
