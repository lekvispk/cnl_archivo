package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.TipoRelacionDAO;
import pe.org.cnl.gestiondoc.model.TipoRelacion;

@Repository
public class TipoRelacionDAOImpl extends HibernateDaoSupport implements TipoRelacionDAO {

	@Autowired
	public TipoRelacionDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoRelacion> listarTipos() {
		return getHibernateTemplate().find(" from TipoRelacion ");
	}

}
