package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.TipoRelacionDAO;
import pe.org.cnl.gestiondoc.model.TipoRelacion;

@Repository
@Transactional
public class TipoRelacionDAOImpl implements TipoRelacionDAO {

	private static final Logger logger = Logger.getLogger(TipoRelacionDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoRelacion> listarTipos() {
		logger.debug("listarTipos");
		return this.sessionFactory.getCurrentSession().createQuery(" from TipoRelacion ").list();
	}

}
