package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.TipoSolicitudDAO;
import pe.org.cnl.gestiondoc.model.TipoSolicitud;

@Repository
public class TipoSolicitudDAOImpl extends HibernateDaoSupport implements TipoSolicitudDAO {

	@Autowired
	public TipoSolicitudDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoSolicitud> listarTipoSolicitud() {
		return getHibernateTemplate().find(" from TipoSolicitud ");
	}

}
