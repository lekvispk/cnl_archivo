package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.TipoActoDAO;
import pe.org.cnl.gestiondoc.model.TipoActo;

@Repository
public class TipoActoDAOImpl extends HibernateDaoSupport implements TipoActoDAO {

	@Autowired
	public TipoActoDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoActo> listarTiposActos() {
		return getHibernateTemplate().find(" from TipoActo ");
	}

}
