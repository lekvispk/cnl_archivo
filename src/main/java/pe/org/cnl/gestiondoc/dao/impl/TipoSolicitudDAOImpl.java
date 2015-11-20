package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.TipoSolicitudDAO;
import pe.org.cnl.gestiondoc.model.TipoSolicitud;

@Repository
@Transactional
public class TipoSolicitudDAOImpl implements TipoSolicitudDAO {

	private static final Logger logger = Logger.getLogger(TipoActoDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoSolicitud> listarTipoSolicitud() {
		logger.debug("listarTipoSolicitud");
		return this.sessionFactory.getCurrentSession().createQuery(" from TipoSolicitud ").list();
	}

}
