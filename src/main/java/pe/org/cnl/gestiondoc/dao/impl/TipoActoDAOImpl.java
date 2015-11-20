package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.TipoActoDAO;
import pe.org.cnl.gestiondoc.model.TipoActo;

@Repository
@Transactional
public class TipoActoDAOImpl implements TipoActoDAO {

	private static final Logger logger = Logger.getLogger(TipoActoDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoActo> listarTiposActos() {
		logger.debug("listarTiposActos");
		return this.sessionFactory.getCurrentSession().createQuery(" from TipoActo ").list();
	}

}
